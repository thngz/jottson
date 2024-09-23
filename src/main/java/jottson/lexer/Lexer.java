package jottson.lexer;

import java.util.List;
import java.util.ArrayList;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class Lexer {
    private String data;
    private int curr = 0;

    public Lexer(String jsonString) {
        this.data = jsonString;
    }

    public List<Token> getTokens() {
        var tokens = new ArrayList<Token>();
        while (!eof()) {
            switch (peek()) {
                case ' ':
                    consume();
                    break;
                case '{':
                    consume();
                    tokens.add(new Token(TokenType.LBRACE));
                    break;
                case ':':
                    consume();
                    tokens.add(new Token(TokenType.COLON));
                    break;
                case '}':
                    consume();
                    tokens.add(new Token(TokenType.RBRACE));
                    break;
                case '"':
                    consume();
                    tokens.add(new Token(TokenType.STRING, readString()));
                    break;
                case ',':
                    consume();
                    tokens.add(new Token(TokenType.COMMA));
                    break;
                case '[':
                    consume();
                    tokens.add(new Token(TokenType.ARRAY, readArray()));
                    break;
                default:
                    if (isDigit(peek())) {
                        tokens.add(new Token(TokenType.NUMBER, readNumber()));
                    } else {
                        throw new RuntimeException("unexpected character: " + peek());
                    }
            }
        }
        return tokens;
    }

    private boolean eof() {
        return curr == data.length();
    }

    private String readArray() {
        StringBuilder sb = new StringBuilder();

        while (!eof() && peek() != ']') {
            sb.append(consume());
        }

        consume();

        return sb.toString();
    }
    
    private String readString() {
        StringBuilder sb = new StringBuilder();
        while (!eof() && peek() != '"') {
            sb.append(consume());
        }
        // skip the ending "
        consume();
        return sb.toString();
    }

    private Integer readNumber() {
        StringBuilder sb = new StringBuilder();
        while (!eof() && isDigit(peek())) {
            sb.append(consume());
        }
        return Integer.parseInt(sb.toString());
    }

    private char consume() {
        return data.charAt(curr++);
    }

    private char peek() {
        return data.charAt(curr);
    }
}
