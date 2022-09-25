package org.example.lexing.tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.lexing.SourceCodePosition;

@Data
@AllArgsConstructor
public class Token {
    SourceCodePosition position;
    String value;
    TokenType type;
}
