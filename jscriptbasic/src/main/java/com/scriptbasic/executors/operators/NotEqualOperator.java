package com.scriptbasic.executors.operators;

import com.scriptbasic.executors.BasicBooleanValue;
import com.scriptbasic.executors.BasicDoubleValue;
import com.scriptbasic.executors.BasicJavaObjectValue;
import com.scriptbasic.executors.BasicLongValue;
import com.scriptbasic.executors.BasicStringValue;
import com.scriptbasic.interfaces.BasicRuntimeException;
import com.scriptbasic.interfaces.RightValue;

public class NotEqualOperator extends AbstractCompareOperator {

    @Override
    protected Boolean compareTo(final BasicDoubleValue f, final RightValue op)
            throws BasicRuntimeException {
        return f.getValue() != BasicDoubleValue.convert(op);
    }

    @Override
    protected Boolean compareTo(final BasicLongValue f, final RightValue op)
            throws BasicRuntimeException {
        return f.getValue() != BasicLongValue.convert(op);
    }

    @Override
    protected Boolean compareTo(final BasicStringValue f, final RightValue op)
            throws BasicRuntimeException {
        return f.getValue().compareTo(BasicStringValue.convert(op)) != 0;
    }

    @Override
    protected Boolean compareTo(final BasicJavaObjectValue f, final RightValue op)
            throws BasicRuntimeException {
        return compareJavaObjectTo(f, op) != 0;
    }

    @Override
    protected Boolean compareTo(final BasicBooleanValue f, final RightValue op)
            throws BasicRuntimeException {
        final int a = f.getValue() ? 1 : 0;
        final int b = BasicBooleanValue.convert(op) ? 1 : 0;
        return a != b;
    }

}
