package pqueue.priorityqueues; // ******* <---  DO NOT ERASE THIS LINE!!!! *******


/* *****************************************************************************************
 * THE FOLLOWING IMPORTS WILL BE NEEDED BY YOUR CODE, BECAUSE WE REQUIRE THAT YOU USE
 * ANY ONE OF YOUR EXISTING MINHEAP IMPLEMENTATIONS TO IMPLEMENT THIS CLASS. TO ACCESS
 * YOUR MINHEAP'S METHODS YOU NEED THEIR SIGNATURES, WHICH ARE DECLARED IN THE MINHEAP
 * INTERFACE. ALSO, SINCE THE PRIORITYQUEUE INTERFACE THAT YOU EXTEND IS ITERABLE, THE IMPORT OF ITERATOR
 * IS NEEDED IN ORDER TO MAKE YOUR CODE COMPILABLE. THE IMPLEMENTATIONS OF CHECKED EXCEPTIONS
 * ARE ALSO MADE VISIBLE BY VIRTUE OF THESE IMPORTS.
 ** ********************************************************************************* */

import pqueue.exceptions.*;
import pqueue.heaps.ArrayMinHeap;
import pqueue.heaps.EmptyHeapException;
import pqueue.heaps.MinHeap;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * <p>{@link MinHeapPriorityQueue} is a {@link PriorityQueue} implemented using a {@link MinHeap}.</p>
 *
 * <p>You  <b>must</b> implement the methods of this class! To receive <b>any credit</b> for the unit tests
 * related to this class, your implementation <b>must</b> use <b>whichever</b> {@link MinHeap} implementation
 * among the two that you should have implemented you choose!</p>
 *
 * @author  ---- Jemimah E.P. Salvacion ----
 *
 * @param <T> The Type held by the container.
 *
 * @see LinearPriorityQueue
 * @see MinHeap
 * @see PriorityQueue
 */
public class MinHeapPriorityQueue<T> implements PriorityQueue<T>{

	/* New class to store data and priority */
	private class PQueueElement {
		private T data;
		private int priority;
		
		public PQueueElement(T dataInput, int priorityInput) {
			data = dataInput;
			priority = priorityInput;
		}
	}
	/* ***********************************************************************************
	 * Write any private data elements or private methods for MinHeapPriorityQueue here...*
	 * ***********************************************************************************/
	MinHeap<Integer> heap;
	private Object[] eltData;
	private int itrIndex;
	private int curr_size;
	
	private void resize(int newCapacity) {
		Object[] temp = new Object[newCapacity];
		for (int i = 0; i < curr_size; i++)
			temp[i] = eltData[i];
		eltData = temp;
	}


	/* *********************************************************************************************************
	 * Implement the following public methods. You should erase the throwings of UnimplementedMethodExceptions.*
	 ***********************************************************************************************************/
		/**
	 * Simple default constructor.
	 */
	public MinHeapPriorityQueue(){
		heap = new ArrayMinHeap<Integer>();
		curr_size = 0;
		eltData = new Object[curr_size+1];
	}

	@Override
	public void enqueue(T element, int priority) throws InvalidPriorityException {	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		if (priority < 1) {
			throw new InvalidPriorityException("Priority must be >= 1.");
		}
		if (curr_size == eltData.length) {
			resize(2*eltData.length);
		}
		
		PQueueElement elt = new PQueueElement(element,priority);
		eltData[curr_size++] = elt;
		heap.insert(priority);
		
		if (heap.size() > 1) {
			PQueueElement curr = (MinHeapPriorityQueue<T>.PQueueElement) eltData[curr_size-1];
			PQueueElement predecessor = (MinHeapPriorityQueue<T>.PQueueElement) eltData[curr_size-1];
			int i = curr_size-1;
			int j = i-1;
			
			while (j >= 0) {
				curr = (MinHeapPriorityQueue<T>.PQueueElement) eltData[i];
				predecessor = (MinHeapPriorityQueue<T>.PQueueElement) eltData[j];
				
				if (curr.priority < predecessor.priority) {
					eltData[j] = curr;
					eltData[i] = predecessor;
  				}
				i--;
				j--;
			}								
		}				
	}

	@Override
	public T dequeue() throws EmptyPriorityQueueException {		// DO *NOT* ERASE THE "THROWS" DECLARATION!
		int i = 0;
		int j = 1;
		
		while (j < curr_size) {
			eltData[i] = eltData[j];
			i++;
			j++;
		}
		
		try {
			heap.deleteMin();
		} catch (EmptyHeapException e) {e.printStackTrace();}
		
		eltData[--curr_size] = null;		
		PQueueElement curr = (MinHeapPriorityQueue<T>.PQueueElement) eltData[0];
		
		return curr.data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getFirst() throws EmptyPriorityQueueException {	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("Queue is empty!");
		}
		PQueueElement first = (MinHeapPriorityQueue<T>.PQueueElement) eltData[0];
		
		return first.data;
	}

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}


	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return (itrIndex < curr_size);
			}

			@Override
			public T next() {
				PQueueElement elt = (MinHeapPriorityQueue<T>.PQueueElement) eltData[itrIndex++];
				return (elt.data);
			}
			
		};
	}

}
