package com.scriptbasic.executors.commands;

import com.scriptbasic.api.ScriptBasicException;
import com.scriptbasic.executors.leftvalues.BasicLeftValue;
import com.scriptbasic.executors.rightvalues.BasicDoubleValue;
import com.scriptbasic.executors.rightvalues.BasicLongValue;
import com.scriptbasic.interfaces.BasicRuntimeException;
import com.scriptbasic.interfaces.Expression;
import com.scriptbasic.spi.Interpreter;
import com.scriptbasic.spi.LeftValue;
import com.scriptbasic.spi.RightValue;
import com.scriptbasic.utility.NumberUtility;

/**
 * @author Peter Verhas
 * date July 20, 2012
 */
public class CommandFor extends AbstractCommand {
    private static final BasicLongValue ONE = new BasicLongValue(1L);
    private LeftValue loopVariable;
    private Expression loopStartValue, loopEndValue, loopStepValue;
    private CommandNext loopEndNode;
    private RightValue loopStart, loopEnd, loopStep;

    public void setLoopEndNode(final CommandNext loopEndNode) {
        this.loopEndNode = loopEndNode;
    }

    public LeftValue getLoopVariable() {
        return loopVariable;
    }

    public void setLoopVariable(final LeftValue loopVariable) {
        this.loopVariable = loopVariable;
    }

    public void setLoopStartValue(final Expression loopStartValue) {
        this.loopStartValue = loopStartValue;
    }

    public void setLoopEndValue(final Expression loopEndValue) {
        this.loopEndValue = loopEndValue;
    }

    public void setLoopStepValue(final Expression loopStepValue) {
        this.loopStepValue = loopStepValue;
    }

    private void startLoopWithLong(final Interpreter interpreter)
            throws ScriptBasicException {
        final var start = BasicLongValue.asLong(loopStart);
        loopVariable.setValue(new BasicLongValue(start), interpreter);
        setNextCommand(interpreter, BasicLongValue.asLong(loopStep), start,
                BasicLongValue.asLong(loopEnd));
    }

    private void startLoopWithDouble(final Interpreter interpreter)
            throws ScriptBasicException {
        final var start = BasicDoubleValue.asDouble(loopStart);
        loopVariable.setValue(new BasicDoubleValue(start), interpreter);
        setNextCommand(interpreter, BasicDoubleValue.asDouble(loopStep), start,
                BasicDoubleValue.asDouble(loopEnd));
    }

    private RightValue getLoopVariableAsRightValue(
            final Interpreter interpreter) throws ScriptBasicException {
        return interpreter.getVariables().getVariableValue(
                ((BasicLeftValue) loopVariable).getIdentifier());
    }

    private void finishTheLoop(final Interpreter interpreter) {
        interpreter.setNextCommand(loopEndNode.getNextCommand());
    }

    private <T extends Number> void setNextCommand(
            final Interpreter interpreter, final T step,
            final T newLoopValue, final T loopEndDouble) {
        if (NumberUtility.isPositive(step)) {
            if (NumberUtility.compare(newLoopValue, loopEndDouble) <= 0) {
                interpreter.setNextCommand(getNextCommand());
            } else {
                finishTheLoop(interpreter);
            }
        } else {
            if (NumberUtility.compare(newLoopValue, loopEndDouble) >= 0) {
                interpreter.setNextCommand(getNextCommand());
            } else {
                finishTheLoop(interpreter);
            }
        }
    }

    private void stepLoopVariable(final Interpreter interpreter,
                                  final Long step) throws ScriptBasicException {
        final var loopEndValue = BasicLongValue.asLong(loopEnd);
        final var rv = getLoopVariableAsRightValue(interpreter);
        final var oldLoopValue = BasicLongValue.asLong(rv);
        if (oldLoopValue == null) {
            throw new ScriptBasicException("FOR loop variable became undef in the loop (integer)");
        }
        final var newLoopValue = oldLoopValue + step;
        loopVariable.setValue(new BasicLongValue(newLoopValue), interpreter);
        setNextCommand(interpreter, step, newLoopValue, loopEndValue);
    }

    private void stepLoopVariable(final Interpreter interpreter,
                                  final Double step) throws ScriptBasicException {
        final var loopEndValue = BasicDoubleValue.asDouble(loopEnd);
        final var rv = getLoopVariableAsRightValue(interpreter);
        final var oldLoopValue = BasicDoubleValue.asDouble(rv);
        if (oldLoopValue == null) {
            throw new ScriptBasicException("FOR loop variable became undef in the loop (float)");
        }
        final var newLoopValue = oldLoopValue + step;
        loopVariable.setValue(new BasicDoubleValue(newLoopValue), interpreter);
        setNextCommand(interpreter, step, newLoopValue, loopEndValue);
    }

    void stepLoopVariable(final Interpreter interpreter)
            throws ScriptBasicException {
        if (loopVariable instanceof BasicLeftValue) {
            if (loopStep instanceof BasicLongValue) {
                stepLoopVariable(interpreter, BasicLongValue.asLong(loopStep));
            } else if (loopStep instanceof BasicDoubleValue) {
                stepLoopVariable(interpreter,
                        BasicDoubleValue.asDouble(loopStep));
            } else {
                throw new BasicRuntimeException(
                        "Loop step value can be long or double");
            }
        } else {
            throw new BasicRuntimeException(
                    "Loop variable is not BasicLeftValue, this is probably internal error");
        }
    }

    void noStepLoopVariable(final Interpreter interpreter)
            throws ScriptBasicException {
        if (loopStep instanceof BasicLongValue) {
            final var step = BasicLongValue.asLong(loopStep);
            final var loopEndValue = BasicLongValue.asLong(loopEnd);
            final var newLoopValue = BasicLongValue
                    .asLong(getLoopVariableAsRightValue(interpreter));
            setNextCommand(interpreter, step, newLoopValue, loopEndValue);
        } else if (loopStep instanceof BasicDoubleValue) {
            final var step = BasicDoubleValue.asDouble(loopStep);
            final var loopEndValue = BasicDoubleValue.asDouble(loopEnd);
            final var newLoopValue = BasicDoubleValue
                    .asDouble(getLoopVariableAsRightValue(interpreter));
            setNextCommand(interpreter, step, newLoopValue, loopEndValue);
        } else {
            throw new BasicRuntimeException(
                    "Loop step value can be long or double");
        }
    }

    private void setLoopStart(final Interpreter interpreter)
            throws ScriptBasicException {
        if (loopStep instanceof BasicDoubleValue) {
            if (loopStart instanceof BasicDoubleValue) {
                loopVariable.setValue(loopStart, interpreter);
            } else {
                startLoopWithDouble(interpreter);
            }
        } else if (loopStep instanceof BasicLongValue) {
            if (loopStart instanceof BasicLongValue) {
                loopVariable.setValue(loopStart, interpreter);
            } else {
                startLoopWithLong(interpreter);
            }
        } else {
            throw new BasicRuntimeException(
                    "Step expression is not long or double");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.scriptbasic.executors.commands.AbstractCommand#execute(com.scriptbasic
     * .interfaces.Interpreter)
     */
    @Override
    public void execute(final Interpreter interpreter)
            throws ScriptBasicException {
        loopStart = loopStartValue.evaluate(interpreter);
        loopEnd = loopEndValue.evaluate(interpreter);
        if (loopStepValue != null) {
            loopStep = loopStepValue.evaluate(interpreter);
        } else {
            loopStep = ONE;
        }
        setLoopStart(interpreter);
        noStepLoopVariable(interpreter);
    }

}
