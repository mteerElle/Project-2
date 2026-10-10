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
        String[] stringArr = s.split(" ");
        //check validitiy first
        int numCount = 0;
        int operCount = 0;
        for(int i=0;i<stringArr.length;i++){
            //should it be if, if, else if?
            //need to check if it's a number
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
        else if(!(numCount-operCount==1)){
            throw new IllegalArgumentException("too many operands");
        }
        else {
            //only when it's not error, do the actual operations
            ArrayStack<AST> a = new ArrayStack();
            for (int i = 0; i < stringArr.length; i++) {
                if (isNumber(stringArr[i])) {
                    a.push(new NumNode(Double.parseDouble(stringArr[i])));
                }
                //where does BinopNode take in an operator?
                //need to appy the operation somehow?
                else if (stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                        || stringArr[i].equals("*") || stringArr[i].equals("-")){
                    BinopNode expr = new BinopNode(stringArr[i], a.pop(), a.pop());
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
                    || stringArr[i].equals("*")){
                if(value.size()==2){
                    BinopNode expr = new BinopNode(stringArr[i], value.pop(), value.pop());
                }
                operations.push(expr);
            }
        }















    }
}
