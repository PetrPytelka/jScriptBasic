package com.scriptbasic.lexer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.scriptbasic.interfaces.KeywordRecognizer;
import com.scriptbasic.interfaces.ScriptBasicKeyWords;

/**
 * This class recognizes a reserved keyword.
 *
 * @author verhasp
 *
 */
public class BasicKeywordRecognizer implements KeywordRecognizer,
        ScriptBasicKeyWords {
    private Set<String> keywords = new HashSet<>();

    public BasicKeywordRecognizer() {
        Collections.addAll(keywords, BASIC_KEYWORDS);
    }

    @Override
    public boolean isRecognized(final String identifier) {
        return keywords.contains(identifier.toLowerCase());
    }

}
