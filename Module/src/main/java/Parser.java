public class Parser {
     public static boolean isNumber(String s){
         try{
             Double.parseDouble(s);
             return true;
         } catch (Exception e) {
             return false;
         }
     }

    public static AST parsePostfix(String s){
         //maybe create a helper function to check for errors
        if (s.trim().isEmpty()) {
            throw new IllegalArgumentException("empty input");
        }
        String[] stringArr = s.trim().split("\\s+");
        //check validitiy first
        for(int i=0;i<stringArr.length;i++){
            //need to check if it's a number or operator
            if(!(isNumber(stringArr[i])) && !(stringArr[i].equals("/")) && !(stringArr[i].equals("+")) && !(stringArr[i].equals("^"))
                    && !(stringArr[i].equals("*")) && !(stringArr[i].equals("-"))){
                throw new IllegalArgumentException("invalid token");
            }
        }
        //only when it's not error, do the actual operations
        ArrayStack<AST> a = new ArrayStack();
        for (int i = 0; i < stringArr.length; i++) {
            if (isNumber(stringArr[i])) {
                a.push(new NumNode(Double.parseDouble(stringArr[i])));
            }
            //need to appy the operation somehow?
            else if (stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                    || stringArr[i].equals("*") || stringArr[i].equals("-")){
                if(!(a.size() >=2)){
                    throw new IllegalArgumentException("insufficient operands");
                }
                AST right = a.pop();
                AST left = a.pop();
                BinopNode expr = new BinopNode(stringArr[i], left,right);
                a.push(expr);
            }
        }
        if(!(a.size()==1)){
            throw new IllegalArgumentException("too many operands");
        }
        return a.pop();

    }

    public AST parseInfix(String s){
         ArrayStack operations = new ArrayStack();
         ArrayStack value = new ArrayStack();
         //check for errors
         String[] stringArr = s.split(" ");
         for(int i=0;i<stringArr.length;i++){
            if(isNumber(stringArr[i])){
                value.push(new NumNode(Integer.parseInt(stringArr[i])));
            }
            else if(stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                    || stringArr[i].equals("*") || stringArr[i].equals("(")){
                //check precedence here
                if(value.size()==2){
                    BinopNode expr = new BinopNode(stringArr[i], value.pop(), value.pop());
                    operations.push(expr);
                }
            }
            else if(stringArr[i].equals(")")){
                //do we check precendence here?
                while(!(operations.pop().equals("("))){
                    operations.pop();
                    if(value.size()==2){
                        BinopNode expr = new BinopNode(stringArr[i], value.pop(), value.pop());
                        operations.push(expr);
                    }
                }
            }
        }















    }
}
