package jottson.lexer;

public class Token {
    private TokenType type;
    private Object value;

    public Token(TokenType type) {
        this.type = type;
    }

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    
    @Override
    public String toString() {
        return type.toString() + (value != null ? "(%s)".formatted(value) : "");
    }

	public TokenType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
