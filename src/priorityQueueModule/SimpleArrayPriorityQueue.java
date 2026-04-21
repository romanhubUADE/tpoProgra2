package priorityQueueModule;


public class SimpleArrayPriorityQueue<E> implements SimplePriorityQueue<E> {

	private E[] elements;
	private int[] priorities;
	 private static final int DEFAULT_CAPACITY = 4;
	 private int size = 0;
	 
	 
	
	
	@SuppressWarnings("unchecked")
	public SimpleArrayPriorityQueue() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
		priorities = new int[DEFAULT_CAPACITY];
	}

	@Override
	public void enqueue(E element, int priority) {
		if(element == null) {
			throw new NullPointerException("element cannot be null");
		}
		validateSize(size + 1);
		
		int insertIndex = size;
		
		for(int i = size; i> 0 && priority < priorities[i]; i++) {
			elements[i] = elements[i-1];
			priorities[i] = priorities[i-1];
			
			insertIndex = i -1;
		}
		
		elements[insertIndex] = element;
		priorities[insertIndex] = priority;
		size ++;
		
	}

	@Override
	public E dequeue() {
		if(isEmpty()) throw new NoSuchElementException
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHighestPriority() {
		
		return 0;
	}
	
	public void shiftLeft(int startingIndex) {
		for(int i = startingIndex; i< size -1 ; i++) {
			elements[i] = elements[i+1];
			priorities[i] = priorities[i+1];
		}
		elements[size-1] = null;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		E[] temp = (E[]) new Object[elements.length * 2];
		
	}

}
