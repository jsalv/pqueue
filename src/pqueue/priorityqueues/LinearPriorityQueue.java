package pqueue.priorityqueues; // ******* <---  DO NOT ERASE THIS LINE!!!! *******

/* *****************************************************************************************
 * THE FOLLOWING IMPORTS ARE HERE ONLY TO MAKE THE JAVADOC AND iterator() METHOD SIGNATURE
 * "SEE" THE RELEVANT CLASSES. SOME OF THOSE IMPORTS MIGHT *NOT* BE NEEDED BY YOUR OWN
 * IMPLEMENTATION, AND IT IS COMPLETELY FINE TO ERASE THEM. THE CHOICE IS YOURS.
 * ********************************************************************************** */

import demos.GenericArrays;
import pqueue.exceptions.*;
import pqueue.fifoqueues.EmptyFIFOQueueException;
import pqueue.fifoqueues.FIFOQueue;
import pqueue.fifoqueues.LinearArrayFIFOQueue;
import pqueue.heaps.ArrayMinHeap;

import java.util.*;
/**
 * <p>{@link LinearPriorityQueue} is a {@link PriorityQueue} implemented as a linear {@link java.util.Collection}
 * of common {@link FIFOQueue}s, where the {@link FIFOQueue}s themselves hold objects
 * with the same priority (in the order they were inserted).</p>
 *
 * <p>You  <b>must</b> implement the methods in this file! To receive <b>any credit</b> for the unit tests related to
 * this class, your implementation <b>must</b>  use <b>whichever</b> linear {@link Collection} you want (e.g
 * {@link ArrayList}, {@link LinkedList}, {@link java.util.Queue}), or even the various {@link List} and {@link FIFOQueue}
 * implementations that we provide for you. You can also use <b>raw</b> arrays, but take a look at {@link GenericArrays}
 * if you intend to do so. Note that, unlike {@link ArrayMinHeap}, we do not insist that you use a contiguous storage
 * {@link Collection}, but any one available (including {@link LinkedList}) </p>
 *
 * @param <T> The type held by the container.
 *
 * @author  ---- YOUR NAME HERE ----
 *
 * @see MinHeapPriorityQueue
 * @see PriorityQueue
 * @see GenericArrays
 */
public class LinearPriorityQueue<T> implements PriorityQueue<T> {

	/* ***********************************************************************************
	 * Write any private data elements or private methods for LinearPriorityQueue here...*
	 * ***********************************************************************************/
	private ArrayList<T> data;
	private int capacity;
	private int size;
	private int index = 0;

	/* *********************************************************************************************************
	 * Implement the following public methods. You should erase the throwings of UnimplementedMethodExceptions.*
	 ***********************************************************************************************************/

	/**
	 * Default constructor initializes the element structure with
	 * a default capacity. This default capacity will be the default capacity of the
	 * underlying element structure that you will choose to use to implement this class.
	 */
	public LinearPriorityQueue(){
		data = new ArrayList<T>();
		capacity = 10;
		size = 0;
	}

	/**
	 * Non-default constructor initializes the element structure with
	 * the provided capacity. This provided capacity will need to be passed to the default capacity
	 * of the underlying element structure that you will choose to use to implement this class.
	 * @see #LinearPriorityQueue()
	 * @param capacity The initial capacity to endow your inner implementation with.
	 * @throws InvalidCapacityException if the capacity provided is less than 1.
	 */
	public LinearPriorityQueue(int capacity) throws InvalidCapacityException{	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		data = new ArrayList<T>();
		this.capacity = capacity;
		size = 0;
	}

	@Override
	public void enqueue(T element, int priority) throws InvalidPriorityException{	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		if (priority < 1) {
			throw new InvalidPriorityException("Priority input has to be >= 1.");
		}else if (size == 0) {
			data.add(element);
		}else if (priority <= size+1 && size <= capacity) {
			if (priority == 1) {
				ArrayList<T> temp = new ArrayList<T>();
				temp.add(element);
				for (T item : data) {
					temp.add(item);
				}
				data.clear();
				for (T item : temp)
					data.add(item);
			} else if (priority == size+1){
				data.add(element);
			} else {
				ArrayList<T> temp = new ArrayList<T>();				
				int count = 1;
				
				for (T item : data) {
					if (count == priority) {
						temp.add(element);
					}
					temp.add(item);
					count++;
				}
				data.clear();
				for (T item : temp)
					data.add(item);
			}
		}
		size++;
		capacity--;
	}

	@Override
	public T dequeue() throws EmptyPriorityQueueException { 	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		T first = data.get(0);	
		data.remove(0);
		
		return first;
	}

	@Override
	public T getFirst() throws EmptyPriorityQueueException {	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("Priority Queue is empty.");
		}
		
		return data.get(0);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (data.size() == 0);
	}


	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {			
			@Override
			public boolean hasNext() {
				return (data.get(index+1)!=null);
			}

			@Override
			public T next() {
				return data.get(index++);
			}
		};
	}

}