package org.example.parsing.json;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class JsonObj extends JsonObject {
    Map<String, JsonObject> value;

    @Override
    public JsonObject getObject(Integer index) {
        return null;
    }

    @Override
    public JsonObject getObject(String key) {
        return value.get(key);
    }

    @Override
    public Integer getInt() {
        return null;
    }

    @Override
    public String getString() {
        return null;
    }
}
