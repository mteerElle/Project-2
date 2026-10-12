public record BinopNode(String op, AST n1, AST n2) implements AST{

    /*public record multx(AST n1, AST n2){}
    public record divx(AST n1, AST n2){}
    public record minx(AST n1, AST n2){}
    public record plusx(AST n1, AST n2){}
    public record powx(AST n1, AST n2){}*/

    public double eval(){
        //I think you can just use op, like say is op.equals("*"), then multiply n1 and n2.
        if(op.equals("^")){
            return Math.pow(n1.eval(), n2.eval());
        }
        if(op.equals("*")){
            return n1.eval() * n2.eval();
        }
        if(op.equals("/")){
            return n1.eval()/n2.eval();
        }
        if(op.equals("+")){
            return n1.eval() + n2.eval();
        }
        if(op.equals("-")){
            return n1.eval() - n2.eval();
        }
        throw new IllegalArgumentException("thats not an op?");
    }
}
