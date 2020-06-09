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
public class MinHeapPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T>{

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
	 * Write any pr6ivate data elements or private methods for MinHeapPriorityQueue here...*
	 * ***********************************************************************************/
	private MinHeap<T> heap;
	private Object[] eltData;
	private int arrdex;

	/* *********************************************************************************************************
	 * Implement the following public methods. You should erase the throwings of UnimplementedMethodExceptions.*
	 ***********************************************************************************************************/
		/**
	 * Simple default constructor.
	 */
	public MinHeapPriorityQueue(){
		heap = new ArrayMinHeap<T>();
		arrdex = 0;
		eltData = new Object[10];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(T element, int priority) throws InvalidPriorityException {	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		if (priority < 1) {
			throw new InvalidPriorityException("Priority must be >= 1.");
		}
		if (size() == 0) {
			PQueueElement elt = new PQueueElement(element,priority);
			eltData[arrdex++] = elt;
			heap.insert(element);
			return;
		}
		PQueueElement newElt = new PQueueElement(element,priority);
		int i = 0;
		while (i < eltData.length) {
			PQueueElement curr = (MinHeapPriorityQueue<T>.PQueueElement) eltData[i];
			i++;
			if (curr.priority > priority) {
				int tempDex = i;
				eltData[--tempDex] = newElt;
				eltData[i] = curr;
				break;
			}
		}
		heap = new ArrayMinHeap<T>();
		for (int j = 0; j < eltData.length; j++) {
			PQueueElement curr = (MinHeapPriorityQueue<T>.PQueueElement) eltData[j];
			if (curr != null)
				heap.insert(curr.data);
			else
				break;
		}
		

	}

	@Override
	public T dequeue() throws EmptyPriorityQueueException {		// DO *NOT* ERASE THE "THROWS" DECLARATION!		
		T res = null;
		if (size() == 0)
			throw new EmptyPriorityQueueException("Empty priority queue.");
		try {
			res = heap.deleteMin();
		} catch (EmptyHeapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		return res;
	}

	@Override
	public T getFirst() throws EmptyPriorityQueueException {	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		T first = null;
		try {
			first = (T) heap.getMin();
		} catch (EmptyHeapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return first;
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
		return heap.iterator();
	}

}
