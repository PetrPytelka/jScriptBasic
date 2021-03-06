package com.scriptbasic.lexer.elements;

import com.scriptbasic.exceptions.LexicalException;
import com.scriptbasic.exceptions.UnterminatedStringException;
import com.scriptbasic.interfaces.LexicalElement;
import com.scriptbasic.lexer.BasicLexialElementFactory;
import com.scriptbasic.lexer.BasicLexicalElement;
import com.scriptbasic.readers.SourceReader;

public class BasicString extends AbstractElementAnalyzer {

    private static final int SINGLE_LINE_STRINGBUILDER_INITIAL_CAPACITY = 100;
    private static final int MULTI_LINE_STRINGBUILDER_INITIAL_CAPACITY = 1000;

    public BasicString(final SourceReader reader) {
        super(reader);
    }

    /**
     * Convert the character stored in {@code ch} to the character that this
     * character means in a string when there is a \ before it.
     * <p>
     * Very simple n to \n, t to \t and r to \r. Anything else remains itself.
     * Also " and ' and \.
     * <p>
     * Later versions should handle UNICODE escapes reading on from the
     * {@code reader}. //TODO handle unicode escapes in strings
     *
     * @param inputCharacter the character that was following the backslash.
     * @return the converted character.
     */
    private static Integer convertEscapedChar(final Integer inputCharacter) {
        Integer outputCharacter = inputCharacter;
        if (inputCharacter != null) {
            switch (inputCharacter) {
                case (int) 'n':
                    outputCharacter = (int) '\n';
                    break;
                case (int) 't':
                    outputCharacter = (int) '\t';
                    break;
                case (int) 'r':
                    outputCharacter = (int) '\r';
                    break;
                default:
                    break;
            }
        }
        return outputCharacter;
    }

    private static void appendSeparator(final StringBuilder stringBuilder,
                                        final boolean multiLine) {
        stringBuilder.append(multiLine ? "\"\"\"" : "\"");
    }

    @Override
    public LexicalElement read() throws LexicalException {
        BasicLexicalElement lexicalElement = null;
        final var character = getReader().get();
        if (character != null && character.equals((int) '"')) {
            if (isTripleQuote()) {
                lexicalElement = readMultiLineString();
            } else {
                lexicalElement = readSingleLineString();
            }
        } else {
            getReader().unget(character);
        }
        return lexicalElement;
    }

    private boolean isTripleQuote() {
        final var second = getReader().get();
        final var third = getReader().get();
        final var itIs = ((Integer) (int) '"').equals(second)
                && ((Integer) (int) '"').equals(third);
        if (!itIs) {
            getReader().unget(third);
            getReader().unget(second);
        }
        return itIs;
    }

    private boolean isStringTerminated(final Integer character,
                                       final boolean multiLine) throws UnterminatedStringException {
        boolean terminated = false;
        if (character == null) {
            throw new UnterminatedStringException(getReader());
        }
        if (character.equals((int) '"')) {
            if (multiLine) {
                terminated = isTripleQuote();
            } else {
                terminated = true;
            }
        }
        return terminated;
    }

    /**
     * Read a string and put it into a lexical element
     *
     * @param stringBufferInitialSize parameter
     * @param multiLine               parameter
     * @return return value
     */
    private BasicLexicalElement readString(final int stringBufferInitialSize,
                                           final boolean multiLine) throws UnterminatedStringException {

        final var string = new StringBuilder(stringBufferInitialSize);
        final var lexeme = new StringBuilder(stringBufferInitialSize);
        appendSeparator(lexeme, multiLine);

        final var lexicalElement = BasicLexialElementFactory
                .create(getReader(), LexicalElement.TYPE_STRING);

        Integer character = getReader().get();
        while (!isStringTerminated(character, multiLine)) {
            lexeme.appendCodePoint(character);
            if (character.equals((int) '\\')) {
                character = getReader().get();
                lexeme.appendCodePoint(character);
                character = convertEscapedChar(character);
            }
            string.appendCodePoint(character);
            character = getReader().get();
        }
        appendSeparator(lexeme, multiLine);
        lexicalElement.setLexeme(lexeme.toString());
        lexicalElement.setStringValue(string.toString());
        lexicalElement.setType(LexicalElement.TYPE_STRING);
        return lexicalElement;
    }

    private BasicLexicalElement readSingleLineString()
            throws UnterminatedStringException {
        return readString(SINGLE_LINE_STRINGBUILDER_INITIAL_CAPACITY, false);
    }

    private BasicLexicalElement readMultiLineString()
            throws UnterminatedStringException {
        return readString(MULTI_LINE_STRINGBUILDER_INITIAL_CAPACITY, true);
    }
}
