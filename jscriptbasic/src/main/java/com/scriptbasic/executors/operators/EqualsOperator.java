package com.scriptbasic.executors.operators;

import com.scriptbasic.executors.BasicBooleanValue;
import com.scriptbasic.executors.BasicDoubleValue;
import com.scriptbasic.executors.BasicJavaObjectValue;
import com.scriptbasic.executors.BasicLongValue;
import com.scriptbasic.executors.BasicStringValue;
import com.scriptbasic.interfaces.BasicRuntimeException;
import com.scriptbasic.interfaces.RightValue;

public class EqualsOperator extends AbstractCompareOperator {

    @Override
    protected Boolean compareTo(final BasicDoubleValue f, final RightValue op)
            throws BasicRuntimeException {
        return f.getValue().equals(BasicDoubleValue.convert(op));
    }

    @Override
    protected Boolean compareTo(final BasicLongValue f, final RightValue op)
            throws BasicRuntimeException {
        return f.getValue().equals(BasicLongValue.convert(op));
    }

    @Override
    protected Boolean compareTo(final BasicStringValue f, final RightValue op)
            throws BasicRuntimeException {
        return f.getValue().equals(BasicStringValue.convert(op));
    }

    @Override
    protected Boolean compareTo(final BasicJavaObjectValue f, final RightValue op)
            throws BasicRuntimeException {
        return f.getValue().equals(BasicJavaObjectValue.convert(op));
    }

    @Override
    protected Boolean compareTo(final BasicBooleanValue f, final RightValue op)
            throws BasicRuntimeException {
        return f.getValue().equals(BasicBooleanValue.convert(op));
    }

}
