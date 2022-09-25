package org.example.lexing.lexingUnits.keywords;

import lombok.extern.log4j.Log4j2;
import org.example.lexing.Lexer;
import org.example.lexing.LexingUnit;
import org.example.lexing.tokens.Token;
import org.example.lexing.tokens.TokenType;

import java.util.Objects;


@Log4j2
public class KeywordLexingUnit implements LexingUnit {
    String keyword;
    TokenType tokenType;

    // TODO token
    public KeywordLexingUnit(String keyword) {
        this.keyword = keyword;
    }

    public KeywordLexingUnit(String keyword, TokenType type) {
        this.keyword = keyword;
        this.tokenType = type;
    }

    @Override
    public Boolean canParse(Lexer lexer) {
        System.out.println("canParse: "+lexer.sourceProvider.peekSource(keyword.length()));
        return Objects.equals(lexer.sourceProvider.peekSource(keyword.length()), keyword);
    }

    @Override
    public void parse(Lexer lexer) {
        lexer.sourceProvider.consumeSource(keyword.length());
        log.info("created "+tokenType+" "+keyword);
        if (tokenType != null) {
            var pos = lexer.sourceProvider.getCurrentPosition(keyword.length());
            var token = new Token(pos, keyword, tokenType);
            lexer.tokenProvider.addToken(token);
        }
    }
}
