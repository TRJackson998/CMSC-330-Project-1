/*
 * Jackson, Terrence
 * CMSC 330 Project 1
 * 09.09.2023
 * Summary:
 * lexical analyzer, tokenize input from a source file
 * StreamTokenizer to process the input, then ID and return various tokens
 * methods to retrieve the lexeme, numeric value (for numeric tokens), and line number for current token
 * Additions:
 * some formatting, recognizing a string token in getNextToken()
 */

/*
* CMSC 330 Advanced Programming Languages
* Project 1 Skeleton
* UMGC CITE
* August 2021 
 */

import java.io.*;

class Lexer {

    private StreamTokenizer tokenizer;
    private String punctuation = ",;.()";
    private Token[] punctuationTokens = { Token.COMMA, Token.SEMICOLON, Token.PERIOD, Token.LEFT_PAREN,
            Token.RIGHT_PAREN };

    /*
     * Constructor that creates a lexical analyzer object given the source file
     */
    public Lexer(File file) throws FileNotFoundException {
        tokenizer = new StreamTokenizer(new FileReader(file));
        tokenizer.ordinaryChar('.');
        tokenizer.quoteChar('"');
    }

    /*
     * Returns the next token in the input stream
     */
    public Token getNextToken() throws LexicalError, IOException {
        int token = tokenizer.nextToken();
        switch (token) {
            case StreamTokenizer.TT_NUMBER:
                return Token.NUMBER;
            case StreamTokenizer.TT_WORD:
                for (Token aToken : Token.values())
                    if (aToken.name().replace("_", "").equals(tokenizer.sval.toUpperCase()))
                        return aToken;
                return Token.IDENTIFIER;
            case StreamTokenizer.TT_EOF:
                return Token.EOF;
            case (int) '"': // if token is equal to int value of quote char
                return Token.STRING; // current token is a string, return
            default:
                for (int i = 0; i < punctuation.length(); i++)
                    if (token == punctuation.charAt(i))
                        return punctuationTokens[i];
        }
        return Token.EOF;
    }

    /*
     * Returns the lexeme associated with the current token
     */
    public String getLexeme() {
        return tokenizer.sval;
    }

    /*
     * Returns the numeric value of the current token for numeric tokens
     */
    public int getNumber() {
        return (int) tokenizer.nval;
    }

    /*
     * Returns the current line of the input file
     */
    public int getLineNo() {
        return tokenizer.lineno();
    }
}
