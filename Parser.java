/*
 * Jackson, Terrence
 * CMSC 330 Project 1
 * 09.09.2023
 * Summary:
 * methods to parse specific grammar productions
 * ensures input adheres to the defined syntax rules
 * error handling for LexicalError, SyntaxError, and IOException
 * extracts information about shapes/text from the input file
 * Additions:
 * remove unused imports/variable/method
 * add parsing for Isosceles, Parallelogram, Regular Polygon, Text
 */
/*
* CMSC 330 Advanced Programming Languages
* Project 1 Skeleton
* UMGC CITE
* August 2021 
 */

import java.awt.*;
import java.io.*;
// removed unused imports

class Parser {
    private Token token;
    private Lexer lexer;
    // removed unused variable

    /*
     * Constructor to create new lexical analyzer from input file
     */
    public Parser(File file) throws IOException {
        lexer = new Lexer(file);
    }

    /*
     * Parses the production
     * scene -> SCENE IDENTIFIER number_list images END '.'
     */
    public Scene parseScene() throws LexicalError, SyntaxError, IOException {
        verifyNextToken(Token.SCENE);
        verifyNextToken(Token.IDENTIFIER);
        String window = lexer.getLexeme(); // gets IDENTIFIER that was verified
        int[] dimensions = getNumberList(2);
        Scene scene = new Scene(window, dimensions[0], dimensions[1]);
        parseImages(scene, lexer.getNextToken());
        verifyNextToken(Token.PERIOD);
        return scene;
    }

    /*
     * Parses the following productions
     * images → image images | image
     * image → right_triangle | rectangle | parallelogram | regular_polygon |
     * isosceles | text
     */
    private void parseImages(Scene scene, Token imageToken) throws LexicalError, SyntaxError, IOException {
        // init variables
        int height, width, offset, radius, sides;

        // get color
        verifyNextToken(Token.COLOR);
        int[] colors = getNumberList(3);
        Color color = new Color(colors[0], colors[1], colors[2]);

        // get location point
        verifyNextToken(Token.AT);
        int[] location = getNumberList(2);
        Point point = new Point(location[0], location[1]);

        // if-else block to parse each kind of token shape/text
        if (imageToken == Token.RIGHT_TRIANGLE) {
            // right_triangle -> RIGHT_TRIANGLE COLOR number_list AT number_list HEIGHT
            // NUMBER WIDTH NUMBER ';'
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            RightTriangle triangle = new RightTriangle(color, point, height, width);
            scene.addImage(triangle);
        } else if (imageToken == Token.RECTANGLE) {
            // rectangle -> RECTANGLE_ COLOR number_list AT number_list HEIGHT NUMBER WIDTH
            // NUMBER ';'
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            Rectangle rectangle = new Rectangle(color, point, height, width);
            scene.addImage(rectangle);
        } else if (imageToken == Token.ISOSCELES) {
            // isosceles → ISOSCELES COLOR number_list AT number_list HEIGHT NUMBER WIDTH
            // NUMBER ';'
            verifyNextToken(Token.HEIGHT);
            verifyNextToken(Token.NUMBER);
            height = lexer.getNumber();
            verifyNextToken(Token.WIDTH);
            verifyNextToken(Token.NUMBER);
            width = lexer.getNumber();
            IsoscelesTriangle isoscelesTriangle = new IsoscelesTriangle(color, point, height, width);
            scene.addImage(isoscelesTriangle);
        } else if (imageToken == Token.PARALLELOGRAM) {
            // parallelogram → PARALLELOGRAM COLOR number_list AT number_list number_list
            // OFFSET NUMBER ';'
            int[] location2 = getNumberList(2);
            Point point2 = new Point(location2[0], location2[1]);
            verifyNextToken(Token.OFFSET);
            verifyNextToken(Token.NUMBER);
            offset = lexer.getNumber();
            Parallelogram parallelogram = new Parallelogram(color, point, point2, offset);
            scene.addImage(parallelogram);
        } else if (imageToken == Token.REGULAR_POLYGON) {
            // regular_polygon → REGULAR_POLYGON COLOR number_list AT number_list SIDES
            // NUMBER RADIUS NUMBER ';'
            verifyNextToken(Token.SIDES);
            verifyNextToken(Token.NUMBER);
            sides = lexer.getNumber();
            verifyNextToken(Token.RADIUS);
            verifyNextToken(Token.NUMBER);
            radius = lexer.getNumber();
            RegularPolygon regularPolygon = new RegularPolygon(color, point, sides, radius);
            scene.addImage(regularPolygon);
        } else if (imageToken == Token.TEXT) {
            // text → TEXT COLOR number_list AT number_list STRING ';'
            verifyNextToken(Token.STRING);
            String string = lexer.getLexeme();
            Text_ text = new Text_(color, point, string);
            scene.addImage(text);
        } else {
            throw new SyntaxError(lexer.getLineNo(), "Unexpected image name " + imageToken);
        }
        verifyNextToken(Token.SEMICOLON);
        token = lexer.getNextToken();
        if (token != Token.END) // base case for recursion, stops when END token found
            parseImages(scene, token); // recursive call to keep parsing the next shape/text input
    }

    /*
     * Parses the following productions
     * number_list -> '(' numbers ')'
     * numbers -> NUMBER | NUMBER ',' numbers
     * 
     * Returns an int[] array of the numbers in the number list
     */
    private int[] getNumberList(int count) throws LexicalError, SyntaxError, IOException {
        int[] list = new int[count];
        verifyNextToken(Token.LEFT_PAREN);
        for (int i = 0; i < count; i++) {
            verifyNextToken(Token.NUMBER);
            list[i] = lexer.getNumber();
            token = lexer.getNextToken();
            if (i < count - 1)
                verifyCurrentToken(Token.COMMA);
            else
                verifyCurrentToken(Token.RIGHT_PAREN);
        }
        return list;
    }

    /*
     * Verifies that the next token is the expected token
     */
    private void verifyNextToken(Token expectedToken) throws LexicalError, SyntaxError, IOException {
        token = lexer.getNextToken();
        verifyCurrentToken(expectedToken);
    }

    /*
     * Verifies that the current token is the expected token
     */
    private void verifyCurrentToken(Token expectedToken) throws SyntaxError {
        if (token != expectedToken)
            throw new SyntaxError(lexer.getLineNo(), "Expecting token " + expectedToken + " not " + token);
    }
}