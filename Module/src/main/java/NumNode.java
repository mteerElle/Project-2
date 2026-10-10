public record NumNode(double n) implements AST {

    public double eval(){
        return n;
    }
}
