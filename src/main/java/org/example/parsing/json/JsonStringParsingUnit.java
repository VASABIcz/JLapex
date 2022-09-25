package org.example.parsing.json;

import org.example.lexing.tokens.TokenType;
import org.example.parsing.Operation;
import org.example.parsing.Parser;
import org.example.parsing.ParsingUnit;

public class JsonStringParsingUnit extends ParsingUnit {
    @Override
    public Boolean canParse(Parser parser) {
        return parser.tokenProvider.peekToken().getType() == TokenType.String;
    }

    @Override
    public Operation parse(Parser parser, Operation previous) {
        return new JsonString(parser.tokenProvider.getToken().getValue());
    }

    @Override
    public Integer getNext(Parser parser, Integer offset) {
        return null;
    }
}
