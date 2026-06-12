package stackModule;

import java.util.NoSuchElementException;
import listModule.LinkedNode;

public class SimpleLinkedStack<E> implements SimpleStack<E> {

    private LinkedNode<E> last; // el tope de la pila
    private int size;

    public SimpleLinkedStack() {
        last = null;
        size = 0;
    }

    @Override
    public void push(E element) {
        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (!isEmpty()) {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        E value = last.value;
        if (size == 1) {
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty.");
        return last.value;
    }

    @Override
    public void clear() {
        last = null;
        size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }
}
