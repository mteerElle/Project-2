import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayStackTest {
    @Test
    void emptystack(){
        assertEquals(true, ArrayStack.<Integer>emptyStack().isEmpty());
    }

    @Test
    void push(){
        ArrayStack<Integer> arr = new ArrayStack<>();
        arr.push(1);
        assertEquals(1, arr.peek());
    }
    @Test
    void pop(){
        ArrayStack<Integer> arr = new ArrayStack<>();
        arr.push(1);
        arr.push(2);
        assertEquals(2,arr.pop());
    }
    @Test
    void pop2(){
        ArrayStack<Integer> arr = new ArrayStack<>();
        assertThrows(IndexOutOfBoundsException.class, () -> arr.pop());
    }

    @Test
    void peek(){
        ArrayStack<Integer> arr = new ArrayStack<>();
        arr.push(1);
        assertEquals(1,arr.peek());
    }
    @Test
    void peek2(){
        ArrayStack<Integer> arr = new ArrayStack<>();
        assertThrows(IndexOutOfBoundsException.class, () -> arr.peek());
        IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class, () ->
                arr.peek());
        assertEquals("Stacks empty", e.getMessage());
    }
    @Test
    void isempty(){
        ArrayStack<Integer> arr = new ArrayStack<>();
        assertEquals(true, arr.isEmpty());
        arr.push(1);
        assertEquals(false, arr.isEmpty());

    }
    @Test
    void size(){
        ArrayStack<Integer> arr = new ArrayStack<>();
        arr.push(1);
        arr.push(2);
        assertEquals(2, arr.size());
        arr.pop();
        assertEquals(1, arr.size());

    }

}
