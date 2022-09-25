package org.example.parsing;

import org.example.lexing.tokens.TokenType;

public class AddParsingUnit extends ParsingUnit {

    public AddParsingUnit() {
        type = ParsingUnitType.LookAround;
    }

    @Override
    public Boolean canParse(Parser parser) {
        return parser.tokenProvider.peekToken().getType() == TokenType.Plus;
    }

    @Override
    public Operation parse(Parser parser, Operation previous) {
        if (previous == null) throw new RuntimeException("FUCK!");
        parser.tokenProvider.consumeToken();

        var next = parser.parseLookAhead();
        var unit = parser.getParsingUnit();

        if (unit == null) {
            parser.buffer = new AddOp(previous, next);
            return parser.buffer;
        } else if (this.isPrioritised(unit)) {
            parser.buffer = new AddOp(previous, next);
            return unit.parse(parser, parser.buffer);
        } else {
            parser.buffer = next;
            return new AddOp(previous, unit.parse(parser, next));
        }
    }

    @Override
    public Integer getNext(Parser parser, Integer offset) {
        return null;
    }
}
