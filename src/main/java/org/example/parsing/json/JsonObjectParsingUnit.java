package org.example.parsing.json;

import org.example.lexing.tokens.TokenType;
import org.example.parsing.Operation;
import org.example.parsing.Parser;
import org.example.parsing.ParsingUnit;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonObjectParsingUnit extends ParsingUnit {
    @Override
    public Boolean canParse(Parser parser) {
        return parser.tokenProvider.peekToken().getType() == TokenType.OCB;
    }

    @Override
    public Operation parse(Parser parser, Operation previous) {
        var map = new HashMap<String, JsonObject>();

        parser.tokenProvider.consumeToken();

        while (parser.tokenProvider.peekToken().getType() != TokenType.CCB) {
            var key = parser.tokenProvider.getToken(TokenType.String).getValue();
            parser.tokenProvider.getToken(TokenType.Colon);
            var pu = parser.getParsingUnit();
            var value = pu.parse(parser, null);
            map.put(key, (JsonObject) value);
            if (parser.tokenProvider.peekToken().getType() == TokenType.Comma) parser.tokenProvider.consumeToken();
        }
        parser.tokenProvider.consumeToken();
        return new JsonObj(map);
    }

    @Override
    public Integer getNext(Parser parser, Integer offset) {
        return null;
    }
}



/*
{
    "xd": "lol",
    "sus": ["lol"]
}

 */