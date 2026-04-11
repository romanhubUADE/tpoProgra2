package stackModule;

import java.util.NoSuchElementException;

public class SimpleLinkedStack <E> implements SimpleStack<E>{

    private Node<E> top;
    private int size;

    public static class Node<E>{
        E data;
        Node<E> next;

        Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }

    }

    @Override
    public void push(E element) {
        top = new Node<>(element, top);
        size ++;
    }

    @Override
    public E pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        E data = top.data;
        top = top.next;
        size --;
        return data;
    }

    @Override
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return top.data;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


}
