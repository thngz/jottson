package main;

import jottson.ast.AST;
import jottson.lexer.Lexer;
import jottson.parser.JsonParser;

public class Main {
    public static void main(String[] args) {
        var json2 = "{\"foo\":\"bar\",\"baz\":{\"key\": \"hello\" }, \"arr\": [1,2, {\"foobar\": \"value2\"}] }";
        var lexer = new Lexer(json2);
        AST jsonAst = new JsonParser(lexer).parse();

        System.out.println(jsonAst);
    }
}
