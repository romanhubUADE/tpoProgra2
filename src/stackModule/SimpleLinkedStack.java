package stackModule;

import java.util.NoSuchElementException;

public class SimpleLinkedStack <E> implements SimpleStack<E>{

<<<<<<< Updated upstream
    private Node<E> top;
=======
    private LinkedNode<E> last;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        top = new Node<>(element, top);
        size ++;
=======
        if (element == null) throw new IllegalArgumentException("element cannot be null");
        LinkedNode<E> newNode = new LinkedNode<>(element);
        if (!isEmpty()) {
            newNode.prev = last;
            last.next = newNode;
        }
        last = newNode;
        size++;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    public void clear() {
        top = null;
        size = 0;
    }
=======
    public void clear() { last = null; size = 0; }
>>>>>>> Stashed changes

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


}
