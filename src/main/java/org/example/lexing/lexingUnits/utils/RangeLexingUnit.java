package org.example.lexing.lexingUnits.utils;

import org.example.lexing.Lexer;
import org.example.lexing.LexingUnit;
import org.example.lexing.tokens.Token;
import org.example.lexing.tokens.TokenType;

import java.util.Objects;

public class RangeLexingUnit implements LexingUnit {
    String start;
    String end;
    TokenType type;

    public RangeLexingUnit(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public RangeLexingUnit(String start, String end, TokenType type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }

    @Override
    public Boolean canParse(Lexer lexer) {
        System.out.println("can parse: "+lexer.sourceProvider.peekSource(this.start.length()));
        return Objects.equals(lexer.sourceProvider.peekSource(this.start.length()), start);
    }

    @Override
    public void parse(Lexer lexer) {
        var pos = lexer.sourceProvider.getCurrentPosition();
        StringBuilder buf = new StringBuilder();

        lexer.sourceProvider.consumeSource(this.start.length());
        while (!Objects.equals(lexer.sourceProvider.peekSource(this.end.length()), end)) {
            buf.append(lexer.sourceProvider.peekSource());
            lexer.sourceProvider.consumeSource();
        }
        lexer.sourceProvider.consumeSource(this.end.length());
        if (type != null) {
            var token = new Token(pos, buf.toString(), type);
            lexer.tokenProvider.addToken(token);
        }
    }
}
