import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    @Test
    void parsePostfix(){
        AST a = Parser.parsePostfix("4 5 +");
        assertEquals(new BinopNode("+", new NumNode(4), new NumNode(5)), a);
    }

    @Test
    void parsePostfix2(){
        AST a = Parser.parsePostfix("5 1 2 + 4 * + 3 -");
        assertEquals(new BinopNode("-", new BinopNode("+", new BinopNode),
                new NumNode(3)), a);
    }
}
