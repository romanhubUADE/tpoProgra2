package list;

public class SimpleListE <E> implements SimpleList <E>{

    private LinkedNode<E> first = null;
    private LinkedNode<E> last = null;
    private int size = 0;

    @Override
    public boolean add(E element) {
        LinkedNode<E> addedNode = new LinkedNode<E>(element);

        if(size == 0)

            first = addedNode;
        else
        {
            last.next = addedNode;
            addedNode.prev = last;

        }
        last = addedNode;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index == size)
        {
            add(element);
            return;
        }
        validateIndex(index);
        LinkedNode<E> addedNode = new LinkedNode<E>(element);
        if (index == 0)
        {
            addedNode.next = first;
            first.prev = addedNode;
            first = addedNode;
        }
        else
        {
            LinkedNode<E> existingNode = getNodeByIndex(index);
            addedNode.next = existingNode;
            addedNode.prev = existingNode.prev;

            existingNode.prev.next = addedNode;
            existingNode.prev = addedNode;
        }
        size ++;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private void validateIndex(int index)
    {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

    }
    private LinkedNode<E> getNodeByIndex(int index)
    {
        if(index < size - index)
        {
            LinkedNode<E> currentNode = first;
            for (int i = 0; i < index; i++)
                currentNode = currentNode.next;
            return currentNode;
        }
        else
        {
            LinkedNode<E> currentNode = last;
            for (int i = size -1; i > index; i--)
                currentNode =  currentNode.prev;
            return currentNode;
        }

    }

}

