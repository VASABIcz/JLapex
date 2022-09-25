package org.example.lexing;

import lombok.extern.log4j.Log4j2;
import org.example.lexing.tokens.Token;
import org.example.lexing.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;


@Log4j2
public class Lexer {
    List<LexingUnit> lexingUnits = new ArrayList<>();
    public final TokenProvider tokenProvider;
    public final SourceProvider sourceProvider;

    public Lexer(TokenProvider tokenProvider, SourceProvider sourceProvider) {
        this.tokenProvider = tokenProvider;
        this.sourceProvider = sourceProvider;
    }

    public void setLexingUnits(List<LexingUnit> lexingUnits) {
        this.lexingUnits = lexingUnits;
    }

    public List<Token> parse() {
        while (!sourceProvider.isDone()) {
            for (var lexingUnit:lexingUnits) {
                if (lexingUnit.canParse(this)) {
                    lexingUnit.parse(this);
                    break;
                }
            }
        }
        return tokenProvider.tokenList;
    }
}
