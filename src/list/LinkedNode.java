package list;

public class LinkedNode <E>{
    public LinkedNode<E> prev;
    public LinkedNode<E> next;
    public E value = null;


    public LinkedNode(E newValue){
        value = newValue;


    }
}
