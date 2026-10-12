import java.util.Objects;

public class ArrayStack<T> {

    //fields
    public AST[] arr;
    public int alive;

    //Constructor
    public ArrayStack(AST[] arr){
        this.arr = arr;
        alive= 0;
    }

    public static ArrayStack emptyStack(){
        return new ArrayStack();
    }

    public void push( AST t){
        arr[alive] = t;
        alive++;
    }

    public AST pop(){
        if(alive ==0){
            throw new IndexOutOfBoundsException();
        }
        AST o = arr[alive];
        arr[alive]=null;
        alive--;
        return o;
    }

    public AST peek(){
        if(alive==0){
            throw new IndexOutOfBoundsException();
        }
        return arr[alive];
    }

    public boolean isEmpty(){
        if(alive ==0){
            return true;
        }
        return false;
    }

    public int size(){
        return alive;
    }


}
