public class BinopNode implements AST{

        AST branch1;
        AST branch2;
        String operator;

        public BinopNode(String op, AST node1, AST node2) {
            operator = op;
            branch1 = node1;
            branch2 = node2;

        }
}
