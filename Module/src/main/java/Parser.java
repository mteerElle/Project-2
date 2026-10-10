public class Parser {
     public static boolean isNumber(String s){
         try{
             Double.parseDouble(s);
             return true;
         } catch (Exception e) {
             return false;
         }
     }

     public static int precedence(String s){
         /*has greater precedence || same precendence, stringArr[i] is left associatiive*/
         if(s.equals("^")){
             return 3;
         }
         else if(s.equals("/") || s.equals("*")){
             return 2;
         }
         else if(s.equals("+") || s.equals("-")){
             return 1;
         }
         return 0;
     }

    public static boolean isRightAssociative(String s) {
        return s.equals("^");
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
        ArrayStack<AST> a = new ArrayStack<>();
        for (int i = 0; i < stringArr.length; i++) {
            if(isNumber(stringArr[i])) {
                a.push(new NumNode(Double.parseDouble(stringArr[i])));
            }
            else if (stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                    || stringArr[i].equals("*") || stringArr[i].equals("-")){
                if(!(a.size()>=2)){
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

    public static AST parseInfix(String s){
        if (s.trim().isEmpty()) {
            throw new IllegalArgumentException("empty input");
        }
        String[] stringArr = s.trim().split("\\s+");
        //check validitiy first
        for(int i=0;i<stringArr.length;i++){
            //need to check if it's a number or operator
            if(!(isNumber(stringArr[i])) && !(stringArr[i].equals("/")) && !(stringArr[i].equals("+")) && !(stringArr[i].equals("^"))
                    && !(stringArr[i].equals("*")) && !(stringArr[i].equals("-")) && !(stringArr[i].equals("(")) &&
            !(stringArr[i].equals(")"))){
                throw new IllegalArgumentException("invalid token");
            }
        }
         ArrayStack<String> operations = new ArrayStack<>();
         ArrayStack<AST> value = new ArrayStack<>();
         for(int i=0;i<stringArr.length;i++){
            if(isNumber(stringArr[i])){
                value.push(new NumNode(Double.parseDouble(stringArr[i])));
            }
            else if(stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                    || stringArr[i].equals("*") || stringArr[i].equals("-")){
                while(!operations.isEmpty() && !operations.peek().equals("(") && precedence(operations.peek()) > precedence(stringArr[i]) ||
                        precedence(operations.peek()) == precedence(stringArr[i]) &&
                        !(isRightAssociative(stringArr[i]))){
                    if(value.size() < 2){
                        throw new IllegalArgumentException("insufficient operands");
                    }
                    AST right = value.pop();
                    AST left = value.pop();
                    BinopNode expr = new BinopNode(operations.pop(), left,right);
                    value.push(expr);
                }
                operations.push(stringArr[i]);
            }
            else if(stringArr[i].equals("(")){
                operations.push(stringArr[i]);
            }
            else if(stringArr[i].equals(")")){
                while(!operations.isEmpty() && !(operations.peek().equals("(")) ){
                    if(value.size() < 2){
                        throw new IllegalArgumentException("insufficient operands");
                    }
                    AST right = value.pop();
                    AST left = value.pop();
                    BinopNode expr = new BinopNode(operations.pop(), left,right);
                    value.push(expr);
                }
                if (operations.isEmpty()) {
                    throw new IllegalArgumentException("mismatched close paren");
                }
                operations.pop();
            }
        }
         while(!operations.isEmpty()) {
             if(operations.peek().equals("(")){
                 throw new IllegalArgumentException("mismatched open paren");
             }
             if(value.size() < 2){
                 throw new IllegalArgumentException("insufficient operands");
             }
             AST right = value.pop();
             AST left = value.pop();
             BinopNode expr = new BinopNode(operations.pop(), left,right);
             value.push(expr);
         }
        if(value.size()>1){
            throw new IllegalArgumentException("too many operands");
        }
        if(value.size()==0){
            throw new IllegalArgumentException("insufficient operands");
        }
        return value.pop();
    }
}
