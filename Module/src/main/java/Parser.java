public class Parser {

    static AST parsePostfix(String s){
        if(s.equals(" ")){
            throw new IllegalArgumentException("empty input");
        }
        String[] stringArr = s.split(" ");
        //check validitiy first
        int numCount = 0;
        int operCount = 0;
        for(int i=0;i<stringArr.length;i++){
            //should it be if, if, else if?
            if(!(Integer.parseInt(stringArr[i]) < Integer.MAX_VALUE) || stringArr[i]!="/"
                    .....){
                throw new IllegalArgumentException("invalid token");
            }
            if(Integer.parseInt(stringArr[i]) < Integer.MAX_VALUE){
                numCount++;
            }
            //where does BinopNode take in an operator?
            else if(stringArr[i].equals("/") || stringArr[i].equals("+").....){
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
            for (int i = 0; i < stringArr.length; i++) {
                if (Integer.parseInt(stringArr[i]) < Integer.MAX_VALUE) {
                    ArrayStack.push(new NumNode(i));
                }
                //where does BinopNode take in an operator?
                else if (stringArr[i].equals("/") || stringArr[i].equals("+").....){
                    BinopNode expr = new BinopNode(i, ASTStack.pop(), ASTStack.pop());
                    ASTStack.push(expr);
                }
            }
            //would it be ASTStack.pop()?
            return ASTStack;
        }
    }
}
