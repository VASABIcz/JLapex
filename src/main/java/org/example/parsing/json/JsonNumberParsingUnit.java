package org.example.parsing.json;

import org.example.lexing.tokens.TokenType;
import org.example.parsing.Operation;
import org.example.parsing.Parser;
import org.example.parsing.ParsingUnit;

public class JsonNumberParsingUnit extends ParsingUnit {
    @Override
    public Boolean canParse(Parser parser) {
        return parser.tokenProvider.peekToken().getType() == TokenType.Numeric;
    }

    @Override
    public Operation parse(Parser parser, Operation previous) {
        return new JsonNumber(Integer.parseInt(parser.tokenProvider.getToken().getValue()));
    }

    @Override
    public Integer getNext(Parser parser, Integer offset) {
        return null;
    }
}
