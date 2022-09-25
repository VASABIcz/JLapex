package org.example.lexing;

import lombok.extern.log4j.Log4j2;
import org.example.lexing.tokens.Token;
import org.example.lexing.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TokenProvider {
    Integer index = -1;
    List<Token> tokenList = new ArrayList<>();

    public void addToken(Token token) {
        log.debug("added token "+token);
        tokenList.add(token);
    }

    public Boolean isDone() {
        return tokenList.size()-1 <= index;
    }

    public Token peekToken() {
        try {
            return tokenList.get(index+1);
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void consumeToken() {
        log.debug("consumed token "+peekToken());
        index++;
    }

    public void consumeToken(Integer amount) {
        index += amount;
    }

    public Token peekPrevious(Token token) {
        return tokenList.get(tokenList.size()-1);
    }

    public Token getToken() {
        var t = peekToken();
        consumeToken();
        return t;
    }

    public Token getToken(TokenType type) {
        var t = peekToken();
        consumeToken();
        assert t.getType() == type;
        return t;
    }
}
