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
        if(s.equals("")){
            throw new IllegalArgumentException("empty input");
        }
        String[] stringArr = s.split("\\s+");
        //check validitiy first
        int numCount = 0;
        int operCount = 0;
        /*if(!(isNumber(stringArr[0]))){
            throw new IllegalArgumentException("invalid expression");
        }*/
        for(int i=0;i<stringArr.length;i++){
            //need to check if it's a number or operator
            if(!(isNumber(stringArr[i])) && !(stringArr[i].equals("/")) && !(stringArr[i].equals("+")) && !(stringArr[i].equals("^"))
                    && !(stringArr[i].equals("*")) && !(stringArr[i].equals("-"))){
                throw new IllegalArgumentException("invalid token");
            }
            if(isNumber(stringArr[i])){
                numCount++;
            }
            else if(stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                    || stringArr[i].equals("*") || stringArr[i].equals("-")){
                operCount++;
            }
        }
        if(numCount==operCount){
            throw new IllegalArgumentException("insufficient operands");
        }
        else if(!(numCount-operCount==1) && numCount-operCount>0){
            throw new IllegalArgumentException("too many operands");
        }
        else {
            //only when it's not error, do the actual operations
            ArrayStack<AST> a = new ArrayStack();
            for (int i = 0; i < stringArr.length; i++) {
                if (isNumber(stringArr[i])) {
                    a.push(new NumNode(Double.parseDouble(stringArr[i])));
                }
                //need to appy the operation somehow?
                else if (stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                        || stringArr[i].equals("*") || stringArr[i].equals("-")){
                    AST right = a.pop();
                    AST left = a.pop();
                    BinopNode expr = new BinopNode(stringArr[i], left,right);
                    a.push(expr);
                }
            }
            return a.pop();
        }
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
