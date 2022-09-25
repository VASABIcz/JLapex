package org.example.parsing;

import org.example.lexing.tokens.Token;

public final class NumericOp extends Operation {
    Token value;

    public NumericOp(Token value) {
        this.value = value;
        this.name = value.getValue();
    }
}
