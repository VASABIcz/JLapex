package org.example.parsing.json;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class JsonArray extends JsonObject {
    List<JsonObject> value;

    @Override
    public JsonObject getObject(Integer index) {
        return value.get(index);
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
        return null;
    }
}
