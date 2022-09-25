package org.example.lexing.lexingUnits;

import lombok.AllArgsConstructor;
import org.example.lexing.Lexer;
import org.example.lexing.LexingUnit;
import org.example.lexing.tokens.Token;
import org.example.lexing.tokens.TokenType;

@AllArgsConstructor
public class IdentifierLexingUnit implements LexingUnit {
    TokenType type;
    @Override
    public Boolean canParse(Lexer lexer) {
        var peek = lexer.sourceProvider.peekSource();
        return Character.isAlphabetic(peek) || peek == '_';
    }

    @Override
    public void parse(Lexer lexer) {
        StringBuilder buf = new StringBuilder();
        var pos = lexer.sourceProvider.getCurrentPosition();
        while (Character.isAlphabetic(lexer.sourceProvider.peekSource()) || lexer.sourceProvider.peekSource() == '_') {
            buf.append(lexer.sourceProvider.peekSource());
            lexer.sourceProvider.consumeSource();
        }
        pos.setLength(buf.length());
        var token = new Token(pos, buf.toString(), type);
        lexer.tokenProvider.addToken(token);
    }
}
