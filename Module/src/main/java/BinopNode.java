public class BinopNode implements AST{

    public record multx(AST n1, AST n2){}
    public record divx(AST n1, AST n2){}
    public record minx(AST n1, AST n2){}
    public record plusx(AST n1, AST n2){}
    public record powx(AST n1, AST n2){}
        AST branch1;
        AST branch2;
        String operator;

        public BinopNode(String op, AST node1, AST node2) {
            operator = op;
            branch1 = node1;
            branch2 = node2;

        }
}
