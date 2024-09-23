package jottson.ast;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Deque;

public class AST {
    private JsonNode rootNode;
    private Deque<JsonNode> history;

    public AST() {
        rootNode = new JsonNode();
        history = new ArrayDeque<>();
        history.push(rootNode);
    }

    public void addChild(ASTNode child) {
        rootNode.addChild(child);
    }

    public void startNewBlock(JsonNode block) {
        rootNode.addChild(block);
        history.push(rootNode);
        rootNode = block;
    }

    public void endBlock() {
        rootNode = history.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Queue<ASTNode> frontier = new ArrayDeque<>();
        frontier.add(rootNode);
        while (!frontier.isEmpty()) {
            var current = frontier.remove();

            if (current instanceof NumberNode || current instanceof StringNode || current instanceof ArrayNode) {
                sb.append(current);
                sb.append("\n");
            } else if (current instanceof JsonNode block) {
                for (ASTNode child : block.getChildren()) {
                    frontier.add(child);
                }
            }
        }
        return sb.toString();
    }

}
