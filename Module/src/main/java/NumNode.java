public record NumNode(int n) implements AST {

    public double eval(){
        return n;
    }
}
