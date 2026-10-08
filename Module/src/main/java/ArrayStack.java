import java.util.Objects;

public class ArrayStack {

    //fields
    public AST[] arr;
    public int alive;

    //Constructor
    public ArrayStack(){
        arr = new AST[10];
        alive= 0;
    }

    public ArrayStack emptyStack(){
        return new ArrayStack();
    }

    public void push( AST t){
        arr[alive] = t;
        alive++;
    }

    public Object pop(){
        if(alive ==0){
            throw new IndexOutOfBoundsException();
        }
        Object o = arr[alive];
        arr[alive]=null;
        alive--;
        return o;
    }

    public Object peek(){
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
