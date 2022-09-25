package org.example.parsing.json;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonString extends JsonObject {
    String value;

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
        return null;
    }

    @Override
    public String getString() {
        return value;
    }
}
