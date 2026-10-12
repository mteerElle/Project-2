public record NumNode(double n) implements AST {
    //returns num
    public double eval(){
        return n;
    }
}
