import java.util.Objects;

public class ArrayStack<T> {

    //fields
    public Object[] arr;
    public int alive;

    //Constructor
    public ArrayStack(){
        arr= new Object[0];
        alive= 0;
    }

    public static <T> ArrayStack<T> emptyStack(){
        return new ArrayStack<T>();
    }

    public void push( T t){
        Object[] ar = new Object[alive +1];
        for(int i=0; i < alive; i++){
            ar[i] = arr[i];
        }
        ar[alive] = t;
        arr = ar;

        alive++;
    }

    public T pop(){
        if(alive ==0){
            throw new IndexOutOfBoundsException();
        }
        T o = (T) arr[alive-1];
        arr[alive-1]=null;
        alive--;
        return o;
    }

    public T peek(){
        if(alive==0){
            throw new IndexOutOfBoundsException("Stacks empty");
        }
        return (T) arr[alive-1];
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
