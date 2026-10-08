public class BinopNode implements AST{

        AST branch1;
        AST branch2;

        public BinopNode(AST node1, AST node2) {
            branch1 = node1;

            branch2 = node2;

        }
}
