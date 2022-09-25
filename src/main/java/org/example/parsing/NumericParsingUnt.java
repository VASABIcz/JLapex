package org.example.parsing;

import org.example.lexing.tokens.TokenType;

public class NumericParsingUnt extends ParsingUnit {
    public NumericParsingUnt() {
        type = ParsingUnitType.LookAhead;
    }

    @Override
    public Boolean canParse(Parser parser) {
        return parser.tokenProvider.peekToken().getType() == TokenType.Numeric;
    }

    @Override
    public Operation parse(Parser parser, Operation previous) {
        var n = parser.tokenProvider.peekToken();
        parser.tokenProvider.consumeToken();
        return new NumericOp(n);
    }

    @Override
    public Integer getNext(Parser parser, Integer offset) {
        return null;
    }
}
