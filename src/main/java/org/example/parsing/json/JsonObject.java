package org.example.parsing.json;

import lombok.AllArgsConstructor;
import org.example.parsing.Operation;

import java.util.List;
import java.util.Map;

public abstract class JsonObject extends Operation {
    public abstract JsonObject getObject(Integer index);
    public abstract JsonObject getObject(String key);

    public abstract Integer getInt();
    public abstract String getString();
}

