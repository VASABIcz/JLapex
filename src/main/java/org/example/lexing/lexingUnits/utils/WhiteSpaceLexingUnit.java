package org.example.lexing.lexingUnits.utils;

import org.example.lexing.Lexer;
import org.example.lexing.LexingUnit;

public class WhiteSpaceLexingUnit implements LexingUnit {
    @Override
    public Boolean canParse(Lexer lexer) {
        return Character.isWhitespace(lexer.sourceProvider.peekSource());
    }

    @Override
    public void parse(Lexer lexer) {
        while (Character.isWhitespace(lexer.sourceProvider.peekSource())) {
            System.out.println(lexer.sourceProvider.peekSource());
            lexer.sourceProvider.consumeSource();
        }
    }
}
