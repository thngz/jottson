package jottson.ast;
import jottson.lexer.Token;

public record NumberNode(Token tokenLiteral) implements ASTNode {}
