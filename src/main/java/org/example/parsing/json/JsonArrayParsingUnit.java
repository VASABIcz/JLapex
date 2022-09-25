package org.example.parsing.json;

import org.example.lexing.tokens.TokenType;
import org.example.parsing.Operation;
import org.example.parsing.Parser;
import org.example.parsing.ParsingUnit;

import java.util.ArrayList;

public class JsonArrayParsingUnit extends ParsingUnit {
    @Override
    public Boolean canParse(Parser parser) {
        return parser.tokenProvider.peekToken().getType() == TokenType.OSB;
    }

    @Override
    public Operation parse(Parser parser, Operation previous) {
        var array = new ArrayList<JsonObject>();

        parser.tokenProvider.consumeToken();

        while (parser.tokenProvider.peekToken().getType() != TokenType.CSB) {
            var pu = parser.getParsingUnit();
            var value = pu.parse(parser, null);
            array.add((JsonObject) value);
            if (parser.tokenProvider.peekToken().getType() == TokenType.Comma) parser.tokenProvider.consumeToken();
        }
        parser.tokenProvider.consumeToken();
        return new JsonArray(array);
    }

    @Override
    public Integer getNext(Parser parser, Integer offset) {
        return null;
    }
}
