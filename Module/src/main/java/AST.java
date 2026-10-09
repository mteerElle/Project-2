public interface AST {


/*
 public class ASTStack {
     AST branch1;
     AST branch2;

     public ASTStack(AST node1, AST node2) {
         branch1 = node1;

         branch2 = node2;

     }
     }
 */

    public double eval(){
        if(this instanceof NumNode){
            return this.dval;
        }
        else{

        }
    }
}
