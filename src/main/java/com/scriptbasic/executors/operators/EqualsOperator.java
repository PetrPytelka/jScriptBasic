package com.scriptbasic.executors.operators;

public class EqualsOperator extends AbstractCompareOperator {

    @Override
    protected boolean decide(int comparisonResult) {
        return comparisonResult == 0;
    }

    @Override
    protected Boolean compareEmptyValues() {
        return true;
    }

}
