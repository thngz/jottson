package jottson.ast;

import jottson.lexer.Token;

public record ArrayNode(Token tokenLiteral) implements ASTNode { }
