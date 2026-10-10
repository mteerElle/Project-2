public class Parser {
     public AST parsePostfix(String s){
         //maybe create a helper function to check for errors
        if(s.equals(" ")){
            throw new IllegalArgumentException("empty input");
        }
        String[] stringArr = s.split(" ");
        //check validitiy first
        int numCount = 0;
        int operCount = 0;
        for(int i=0;i<stringArr.length;i++){
            //should it be if, if, else if?
            //need to check if it's a number
            if(stringArr[i]!="/" && stringArr[i]!="+" && stringArr[i]!="^"
                    && stringArr[i]!="*" || stringArr[i]!=("-")){
                throw new IllegalArgumentException("invalid token");
            }
            if(Integer.parseInt(stringArr[i]) < Integer.MAX_VALUE){
                numCount++;
            }
            else if(stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                    || stringArr[i].equals("*") || stringArr[i].equals("-")){
                operCount++;
            }
        }
        if(numCount==operCount){
            throw new IllegalArgumentException("insufficient operants");
        }
        else if(!(numCount-operCount==1)){
            throw new IllegalArgumentException("too many operands");
        }
        else {
            //only when it's not error, do the actual operations
            ArrayStack a = new ArrayStack();
            for (int i = 0; i < stringArr.length; i++) {
                if (Integer.parseInt(stringArr[i]) < Integer.MAX_VALUE) {
                    a.push(new NumNode(i));
                }
                //where does BinopNode take in an operator?
                //need to appy the operation somehow?
                else if (stringArr[i].equals("/") || stringArr[i].equals("+") || stringArr[i].equals("^")
                        || stringArr[i].equals("*") || stringArr[i].equals("-")){
                    BinopNode expr = new BinopNode(stringArr[i], a.pop(), a.pop());
                    a.push(expr);
                }
            }
            return (AST) a;
        }
    }

    public AST parseInfix(String s){
         ArrayStack operations = new ArrayStack();
         ArrayStack value = new ArrayStack();
         //check for errors
        String[] stringArr = s.split(" ");
        for(int i=0;i<stringArr.length;i++){
            if(/*it is a number*/){
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
