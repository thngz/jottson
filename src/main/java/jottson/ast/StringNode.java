package jottson.ast;
import jottson.lexer.Token;

public record StringNode(Token tokenLiteral) implements ASTNode {}
