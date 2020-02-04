package com.scriptbasic.executors.rightvalues;

public class BasicEmptyValue extends AbstractNumericRightValue<Long> {

    public static final BasicEmptyValue EMPTY_VALUE = new BasicEmptyValue();

    private BasicEmptyValue() {
        setValue(0L);
    }

}
