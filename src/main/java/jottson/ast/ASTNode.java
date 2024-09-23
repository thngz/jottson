package jottson.ast;

public sealed interface ASTNode permits NumberNode, StringNode, JsonNode, ArrayNode {
}
