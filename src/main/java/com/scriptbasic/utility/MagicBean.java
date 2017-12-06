package com.scriptbasic.utility;

import com.scriptbasic.api.BasicRuntimeException;
import com.scriptbasic.interfaces.Magic;

import java.util.HashMap;
import java.util.Map;

public class MagicBean implements Magic.Bean {

    private final Map<String, Object> store = new HashMap<>();

    @Override
    public Object get(final String fieldName) throws BasicRuntimeException {
        if (!store.containsKey(fieldName)) {
            store.put(fieldName, new MagicBean());
        }
        return store.get(fieldName);
    }

    @Override
    public void set(final String fieldName, final Object value) throws BasicRuntimeException {
        store.put(fieldName, value);
    }
}
