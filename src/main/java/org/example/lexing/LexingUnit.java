package org.example.lexing;

public interface LexingUnit {
    Boolean canParse(Lexer lexer);

    void parse(Lexer lexer);
}
