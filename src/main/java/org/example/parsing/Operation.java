package org.example.parsing;

import lombok.Data;
import org.example.lexing.tokens.Token;
import org.example.parsing.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Operation {
    public List<Operation> children = new ArrayList<>();
    public List<Token> tokens = new ArrayList<>();
    public String name = "undefined";
}
