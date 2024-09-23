package jottson.ast;

import jottson.lexer.Token;

import java.util.List;
import java.util.ArrayList;

public final class JsonNode implements ASTNode {
    private List<ASTNode> children;

    public JsonNode() {
        children = new ArrayList<>();
    }

    public void addChild(ASTNode node) {
        children.add(node);
    }

	public List<ASTNode> getChildren() {
		return children;
	}
     
}
