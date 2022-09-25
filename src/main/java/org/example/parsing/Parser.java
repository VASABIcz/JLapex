package org.example.parsing;

import org.example.lexing.TokenProvider;
import org.example.lexing.tokens.Token;
import org.example.lexing.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    List<ParsingUnit> parsingUnits = new ArrayList<>();
    List<ParsingUnit> lookBack = new ArrayList<>();
    List<ParsingUnit> lookAhead = new ArrayList<>();
    public TokenProvider tokenProvider;

    Operation buffer = null;

    public Parser(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public void addParsingUnit(ParsingUnit parsingUnit) {
        parsingUnit.priority = parsingUnits.size();
        parsingUnits.add(parsingUnit);
        switch (parsingUnit.type) {
            case LookAround, LookBack -> lookBack.add(parsingUnit);
            case LookAhead -> lookAhead.add(parsingUnit);
        }
    }

    public void setParsingUnits(List<ParsingUnit> parsingUnits) {
        for (var parsingUnit:parsingUnits) {
            addParsingUnit(parsingUnit);
        }
    }

    public ParsingUnit getParsingUnit() {
        if (tokenProvider.isDone()) return null;
        for (var parsingUnit:parsingUnits) {
            if (parsingUnit.canParse(this)) {
                return parsingUnit;
            }
        }
        return null;
    }

    public Operation parseLookAhead() {
        buffer = null;
        for (var parsingUnit:lookAhead) {
            if (parsingUnit.canParse(this)) {
                buffer = parsingUnit.parse(this, buffer);
                break;
            }
        }
        if (buffer == null) throw new RuntimeException("FUCK2!");
        return buffer;
    }

    public Operation parseLookBack(Operation inBuffer) {
        buffer = null;
        for (var parsingUnit:lookBack) {
            if (parsingUnit.canParse(this)) {
                buffer = parsingUnit.parse(this, inBuffer);
                break;
            }
        }
        if (buffer == null) throw new RuntimeException("FUCK2!");
        return buffer;
    }

    public Operation parse() {
        while (!tokenProvider.isDone()) {
            for (var parsingUnit:parsingUnits) {
                if (parsingUnit.canParse(this)) {
                    buffer = parsingUnit.parse(this, buffer);
                    break;
                }
            }
            if (!tokenProvider.isDone()) {
                throw new RuntimeException("it looks like you either forgoer to implement a parsing unit for "+tokenProvider.peekToken()+"\nor you used this inside parsing unit :) use getParsingUnit");
            }
        }
        return buffer;
    }
}


// 3 minus posibilities
// 5 - 4
// 5--5 negative number (INLINED negate op for numerics)
// negate operator


// two sidied, prvios parsing
// next parsing

