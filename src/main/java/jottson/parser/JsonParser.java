package jottson.parser;

import java.util.List;
import jottson.lexer.Token;
import jottson.lexer.TokenType;

import jottson.lexer.Lexer;
import jottson.ast.*;

public class JsonParser {
    private List<Token> tokens;
    private AST ast;
    private int curr = 0;

    public JsonParser(Lexer lexer) {
        this.tokens = lexer.getTokens();
        this.ast = new AST();
    }

    public AST parse() {
        match(TokenType.LBRACE);
        pairs();
        match(TokenType.RBRACE);
        this.ast.endBlock();
        return this.ast;
    }

    private void pairs() {
        match(TokenType.STRING);
        match(TokenType.COLON);
        value();
        while (peek().getType() == TokenType.COMMA) {
            match(TokenType.COMMA);
            pairs();
        }
    }

    // private void array() {
    //     match(TokenType.ARRAY);
    //     elements();
    //     match(TokenType.RBRACKET);
    // }

    private void elements() {
        value();
        while (peek().getType() == TokenType.COMMA) {
            match(TokenType.COMMA);
            value();
        }
    }

    private void value() {
        switch (peek().getType()) {
            case TokenType.STRING:
                this.ast.addChild(new StringNode(peek()));
                match(TokenType.STRING);
                break;
            case TokenType.NUMBER:
                this.ast.addChild(new NumberNode(peek()));
                match(TokenType.NUMBER);
                break;
            case TokenType.LBRACE:
                this.ast.startNewBlock(new JsonNode());
                parse();
                break;
            case TokenType.ARRAY:
                this.ast.addChild(new ArrayNode(peek()));
                match(TokenType.ARRAY);
                // array();
                break;
            default:
                throw new RuntimeException("Unrecognized value " + peek() + " at " + curr);
        }
    }

    private Token match(TokenType type) {
        if (peek().getType() == type) {
            return consume();
        } else {
            throw new RuntimeException("Unexpected token at %s at position %s".formatted(peek(), curr));
        }
    }

    private Token consume() {
        return tokens.get(curr++);
    }

    private Token peek() {
        return tokens.get(curr);
    }
}
