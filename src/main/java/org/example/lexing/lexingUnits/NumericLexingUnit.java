package org.example.lexing.lexingUnits;

import org.example.lexing.Lexer;
import org.example.lexing.LexingUnit;
import org.example.lexing.tokens.Token;
import org.example.lexing.tokens.TokenType;

public class NumericLexingUnit implements LexingUnit {

    @Override
    public Boolean canParse(Lexer lexer) {
        return Character.isDigit(lexer.sourceProvider.peekSource());
    }

    @Override
    public void parse(Lexer lexer) {
        StringBuilder buf = new StringBuilder();
        var pos = lexer.sourceProvider.getCurrentPosition();
        while (Character.isDigit(lexer.sourceProvider.peekSource())) {
            buf.append(lexer.sourceProvider.peekSource());
            lexer.sourceProvider.consumeSource();
        }
        pos.setLength(buf.length());
        var token = new Token(pos, buf.toString(), TokenType.Numeric);
        lexer.tokenProvider.addToken(token);
    }
}
