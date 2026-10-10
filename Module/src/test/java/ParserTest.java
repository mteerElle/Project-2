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
        assertEquals(true, Parser.precedence("^"));
        assertEquals(false, Parser.precedence("6"));
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
        assertEquals("empty input", error);
    }

    @Test
    void postfixInvalidInput(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parsePostfix("& 3 4"));
        assertEquals("invalid token", error);
    }

    @Test
    void postfixInsufficientOperands(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parsePostfix("3 4 + - *"));
        assertEquals("insufficient operands", error);
    }

    @Test
    void postfixTooManyOperands(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () ->
                Parser.parsePostfix("3 6 8 -"));
        assertEquals("too many operands", error);
    }

    




}
