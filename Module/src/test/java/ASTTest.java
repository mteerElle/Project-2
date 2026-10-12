import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ASTTest {
    @Test
    void checksEval(){
        AST p =  Parser.parseInfix(" 6 + 12 ");
        assertEquals(18, p.eval());

        AST m =  Parser.parseInfix(" 13 - 3 ");
        assertEquals(10, m.eval());

        AST mu = Parser.parseInfix(" 2 * 3 ");
        assertEquals(6, mu.eval());

        AST div = Parser.parseInfix( "8 / 4 ");
        assertEquals(2, div.eval());

        AST pow = Parser.parseInfix("2 ^ 3");
        assertEquals(8, pow.eval());

        AST rand = Parser.parseInfix(( "3 * 4 - 2 + 5 ^ 2"));
        assertEquals(35, rand.eval());
    }
}
