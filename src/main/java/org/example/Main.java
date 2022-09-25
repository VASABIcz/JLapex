package org.example;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.example.lexing.*;
import org.example.lexing.lexingUnits.IdentifierLexingUnit;
import org.example.lexing.lexingUnits.NumericLexingUnit;
import org.example.lexing.lexingUnits.keywords.KeywordLexingUnit;
import org.example.lexing.lexingUnits.utils.RangeLexingUnit;
import org.example.lexing.lexingUnits.utils.WhiteSpaceLexingUnit;
import org.example.lexing.tokens.TokenType;
import org.example.parsing.*;
import org.example.parsing.json.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Configurator.setRootLevel(Level.ALL);
        //var sc = new SourceCode("test.vipl", "5-5-25");
        var sc = new SourceCode("test.json", "[{\n" +
                "  \"id\": 1,\n" +
                "  \"first_name\": \"Jeanette\",\n" +
                "  \"last_name\": \"Penddreth\",\n" +
                "  \"email\": \"jpenddreth0@census.gov\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"ip_address\": \"26.58.193.2\"\n" +
                "}, {\n" +
                "  \"id\": 2,\n" +
                "  \"first_name\": \"Giavani\",\n" +
                "  \"last_name\": \"Frediani\",\n" +
                "  \"email\": \"gfrediani1@senate.gov\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"ip_address\": \"229.179.4.212\"\n" +
                "}, {\n" +
                "  \"id\": 3,\n" +
                "  \"first_name\": \"Noell\",\n" +
                "  \"last_name\": \"Bea\",\n" +
                "  \"email\": \"nbea2@imageshack.us\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"ip_address\": \"180.66.162.255\"\n" +
                "}, {\n" +
                "  \"id\": 4,\n" +
                "  \"first_name\": \"Willard\",\n" +
                "  \"last_name\": \"Valek\",\n" +
                "  \"email\": \"wvalek3@vk.com\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"ip_address\": \"67.76.188.26\"\n" +
                "}]\n");
        var scProvider = new SourceProvider(sc);
        var tokenProvider = new TokenProvider();

        var lexingUnits = new ArrayList<LexingUnit>();
        lexingUnits.add(new WhiteSpaceLexingUnit());
        lexingUnits.add(new RangeLexingUnit("/*", "*/"));

        lexingUnits.add(new RangeLexingUnit("\"", "\"", TokenType.String));
        lexingUnits.add(new RangeLexingUnit("'", "'", TokenType.Char));
        lexingUnits.add(new RangeLexingUnit("//", "\n"));

        lexingUnits.add(new NumericLexingUnit());
        // keywords
        lexingUnits.add(new KeywordLexingUnit("break", TokenType.Break));
        lexingUnits.add(new KeywordLexingUnit("loop", TokenType.Loop));
        lexingUnits.add(new KeywordLexingUnit("return", TokenType.Return));
        lexingUnits.add(new KeywordLexingUnit("continue", TokenType.Continue));
        lexingUnits.add(new KeywordLexingUnit("fn", TokenType.FunctionDef));
        lexingUnits.add(new KeywordLexingUnit("var", TokenType.VariableDef));

        lexingUnits.add(new KeywordLexingUnit("==", TokenType.OperatorEq));
        lexingUnits.add(new KeywordLexingUnit("!=", TokenType.OperatorEq));
        lexingUnits.add(new KeywordLexingUnit("<=", TokenType.VariableDef));
        lexingUnits.add(new KeywordLexingUnit(">=", TokenType.VariableDef));


        lexingUnits.add(new KeywordLexingUnit("*=", TokenType.MultAssign));
        lexingUnits.add(new KeywordLexingUnit("+=", TokenType.AddAssign));
        lexingUnits.add(new KeywordLexingUnit("/=", TokenType.DivAssign));
        lexingUnits.add(new KeywordLexingUnit("-=", TokenType.SubAssign));

        lexingUnits.add(new KeywordLexingUnit("++", TokenType.Ind));
        lexingUnits.add(new KeywordLexingUnit("--", TokenType.Dec));



        lexingUnits.add(new KeywordLexingUnit(";", TokenType.Semicolon));
        lexingUnits.add(new KeywordLexingUnit(".", TokenType.Dot));
        lexingUnits.add(new KeywordLexingUnit(":", TokenType.Colon));
        lexingUnits.add(new KeywordLexingUnit(",", TokenType.Comma));
        lexingUnits.add(new KeywordLexingUnit("=", TokenType.Eq));
        lexingUnits.add(new KeywordLexingUnit("+", TokenType.Plus));
        lexingUnits.add(new KeywordLexingUnit("-", TokenType.Minus));
        lexingUnits.add(new KeywordLexingUnit("/", TokenType.Slash));
        lexingUnits.add(new KeywordLexingUnit("*", TokenType.Star));

        lexingUnits.add(new KeywordLexingUnit("(", TokenType.ORB));
        lexingUnits.add(new KeywordLexingUnit(")", TokenType.CRB));
        lexingUnits.add(new KeywordLexingUnit("[", TokenType.OSB));
        lexingUnits.add(new KeywordLexingUnit("]", TokenType.CSB));
        lexingUnits.add(new KeywordLexingUnit("{", TokenType.OCB));
        lexingUnits.add(new KeywordLexingUnit("}", TokenType.CCB));
        lexingUnits.add(new KeywordLexingUnit("<", TokenType.OTB));
        lexingUnits.add(new KeywordLexingUnit(">", TokenType.CTB));


        lexingUnits.add(new IdentifierLexingUnit(TokenType.Identifier));

        var lexer = new Lexer(tokenProvider, scProvider);
        lexer.setLexingUnits(lexingUnits);
        var tokens = lexer.parse();
        Utils.printCleanTokens(tokens);

        var parser = new Parser(tokenProvider);
        /*
        parser.addParsingUnit(new MultiplyParsingUnit());
        parser.addParsingUnit(new AddParsingUnit());
        parser.addParsingUnit(new SubParsingUnit());
        parser.addParsingUnit(new NumericParsingUnt());
        parser.addParsingUnit(new BracketParsingUnit());

         */
        parser.addParsingUnit(new JsonArrayParsingUnit());
        parser.addParsingUnit(new JsonObjectParsingUnit());
        parser.addParsingUnit(new JsonStringParsingUnit());
        parser.addParsingUnit(new JsonNumberParsingUnit());
        var ast = parser.parse();
        System.out.println(ast);
        Utils.printAst(ast, 0);

        /*
        var json = (JsonObj) ast;
        var array = (JsonList) json.getObject("hello");
        var index0 = (JsonString) array.getObject(0);
        var index1 = (JsonString) array.getObject(1);
        System.out.println(index0.getString()+" "+index1.getString());

         */
        var json = (JsonArray) ast;
        var index1 = (JsonObj) json.getObject(1);
        var id = (JsonNumber) index1.getObject("id");
        System.out.println(id.getInt());
    }
}