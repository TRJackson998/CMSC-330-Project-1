/*
 * Jackson, Terrence
 * CMSC 330 Project 1
 * 09.09.2023
 * Summary:
 * enum variable Token used to pass Token.type from the Lexer to the Parser
 * used in the parser to verify correct token has been found
 * Additions:
 * ISOSCELES, OFFSET, PARALLELOGRAM, RADIUS, REGULAR_POLYGON, SIDES, STRING, TEXT
 */

/*
* CMSC 330 Advanced Programming Languages
* Project 1 Skeleton
* UMGC CITE
* August 2021 
 */

// Enumerated type that defines the list of tokens

enum Token {
    AT, COLOR, COMMA, END, EOF, HEIGHT, IDENTIFIER, ISOSCELES, LEFT_PAREN, NUMBER, OFFSET, PARALLELOGRAM, PERIOD,
    RADIUS, RECTANGLE, REGULAR_POLYGON, RIGHT_PAREN, RIGHT_TRIANGLE, SCENE, SEMICOLON, SIDES, STRING, TEXT, WIDTH
}