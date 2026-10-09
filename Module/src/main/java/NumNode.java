public record NumNode(int n) implements AST {

    public record numx(int num){}

    public double eval(){
        if(this instanceof NumNode){
            return this.dval;
        }
        else{
            return this.branch1.eval() + this.branch2.eval();
        }
    }
}
