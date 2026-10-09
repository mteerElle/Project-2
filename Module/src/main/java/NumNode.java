public class NumNode implements AST {

    public record numx(int num){}
    double dval;

    NumNode(double dval){
        this.dval = dval;
    }
}
