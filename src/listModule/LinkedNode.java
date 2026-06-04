package listModule;

public class LinkedNode<E> {
    public LinkedNode<E> prev;
    public LinkedNode<E> next;
    public E value;

    public LinkedNode(E value) {
        this.value = value;
    }
}

