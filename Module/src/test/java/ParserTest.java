import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    //helper method tests
    @Test
    void checksNumbers(){
        assertEquals(true, Parser.isNumber("3"));
        assertEquals(true, Parser.isNumber("3.5"));
        assertEquals(false, Parser.isNumber("v"));
        assertEquals(false, Parser.isNumber("{"));
    }

    @Test
    void checkPrecedence(){
        assertEquals(3, Parser.precedence("^"));
        assertEquals(2, Parser.precedence("*"));
        assertEquals(2, Parser.precedence("/"));
        assertEquals(1, Parser.precedence("+"));
        assertEquals(1, Parser.precedence("-"));
        assertEquals(0, Parser.precedence("("));
    }

    @Test
    void checkRightAssociative(){
        assertEquals(true, Parser.isRightAssociative("^"));
        assertEquals(false, Parser.isRightAssociative("6"));
    }

    //check valid expressions for postfix
    @Test
    void parsePostfix(){
        AST a = Parser.parsePostfix("4 5 +");
        assertEquals(new BinopNode("+", new NumNode(4), new NumNode(5)), a);
    }

    @Test
    void parsePostfix2(){
        AST a = Parser.parsePostfix("2 3 + 4 *");
        assertEquals(new BinopNode("*", new BinopNode("+", new NumNode(2), new NumNode(3)),
                new NumNode(4)), a);
    }

    //check errors for postfix
    @Test
    void postfixEmptyInput(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
            Parser.parsePostfix(""));
        assertEquals("empty input", error.getMessage());
    }

    @Test
    void postfixInvalidInput(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parsePostfix("& 3 4"));
        assertEquals("invalid token", error.getMessage());
    }

    @Test
    void postfixInsufficientOperands(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parsePostfix("3 4 + - *"));
        assertEquals("insufficient operands", error.getMessage());
    }

    @Test
    void postfixTooManyOperands(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parsePostfix("3 6 8 -"));
        assertEquals("too many operands", error.getMessage());
    }

    //check valid expressions for infix
    @Test
    void parseInfix(){
        AST a = Parser.parseInfix("4 + 5");
        assertEquals(new BinopNode("+", new NumNode(4), new NumNode(5)), a);
    }

    //checks higher precedence on left
    @Test
    void parseInfix2(){
        AST a = Parser.parseInfix("2 * 7 + 4");
        assertEquals(new BinopNode("+", new BinopNode("*", new NumNode(2), new NumNode(7)),
                new NumNode(4)), a);
    }

    //checks parenthesis
    @Test
    void parseInfix3(){
        AST a = Parser.parseInfix("2 * ( 7 + 4 )");
        assertEquals(new BinopNode("*", new NumNode(2), new BinopNode("+", new NumNode(7),
                new NumNode(4))), a);
    }

    //checks associativity
    @Test
    void infixLeft() {
        AST a = Parser.parseInfix("2 - 3 - 4");
        assertEquals(new BinopNode("-", new BinopNode("-", new NumNode(2),
                new NumNode(3)), new NumNode(4)), a);
    }

    @Test
    void infixRight(){
        AST a = Parser.parseInfix("4 ^ 2 ^ 3");
        assertEquals(new BinopNode("^", new NumNode(4), new BinopNode("^", new NumNode(2),
                new NumNode(3))), a);
    }

    //checks errors
    @Test
    void infixEmptyInput() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix(""));
        assertEquals("empty input", error.getMessage());
    }

    @Test
    void infixInvalidInput() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix("& 3 4"));
        assertEquals("invalid token", error.getMessage());
    }

    @Test
    void infixInsufficientOperands() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix("4 + 5 +"));
        assertEquals("insufficient operands", error.getMessage());
    }

    @Test
    void infixInsufficientOperandsForOperator() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix("+ + 2"));
        assertEquals("insufficient operands", error.getMessage());
    }

    @Test
    void infixInsufficientOperandsForParenthesis() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix("( - )"));
        assertEquals("insufficient operands", error.getMessage());
    }

    @Test
    void infixTooManyOperands() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix("3 + 2 + 5 7"));
        assertEquals("too many operands", error.getMessage());
    }

    @Test
    void infixNoOperands() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix("( )"));
        assertEquals("insufficient operands", error.getMessage());
    }

    @Test
    void infixOpenParen() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix("( 1 6"));
        assertEquals("mismatched open paren", error.getMessage());
    }

    @Test
    void infixCloseParen() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parseInfix("1 3 )"));
        assertEquals("mismatched close paren", error.getMessage());
    }


}
