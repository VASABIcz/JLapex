package org.example.parsing.json;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonNumber extends JsonObject {
    Integer value;

    @Override
    public JsonObject getObject(Integer index) {
        return null;
    }

    @Override
    public JsonObject getObject(String key) {
        return null;
    }

    @Override
    public Integer getInt() {
        return value;
    }

    @Override
    public String getString() {
        return null;
    }
}
