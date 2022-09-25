package org.example;

import org.example.lexing.tokens.Token;
import org.example.parsing.Operation;

import java.util.List;

public class Utils {
    public static void printCleanTokens(List<Token> tokens) {
        for (var t : tokens) {
            System.out.println(t.getType() + " " + t.getValue());
        }
    }

    public static void printAst(Operation operation, Integer offset) {
        var x = " ".repeat(offset);
        System.out.println(x+operation.name);
        for (var ch:operation.children) {
            var c = offset;
            Utils.printAst(ch, ++c);
        }
    }
}
