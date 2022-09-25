package org.example.parsing;

import org.example.lexing.tokens.TokenType;

public class BracketParsingUnit extends ParsingUnit {
    public BracketParsingUnit() {
        type = ParsingUnitType.LookAhead;
    }

    @Override
    public Boolean canParse(Parser parser) {
        return parser.tokenProvider.peekToken().getType() == TokenType.ORB;
    }

    @Override
    public Operation parse(Parser parser, Operation previous) {
        if (previous != null) throw new RuntimeException("FUCK4!");
        parser.tokenProvider.consumeToken(); // (

        // ( 5 * 5 + 4 )

        var next = parser.parseLookAhead();
        if (next == null) throw new RuntimeException("expected look ahead operation");
        var res = parser.parseLookBack(next);
        parser.tokenProvider.consumeToken(); // )
        return res;
    }

    @Override
    public Integer getNext(Parser parser, Integer offset) {
        return null;
    }
}
