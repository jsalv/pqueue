package pqueue.heaps; // ******* <---  DO NOT ERASE THIS LINE!!!! *******

/* *****************************************************************************************
 * THE FOLLOWING IMPORT IS NECESSARY FOR THE ITERATOR() METHOD'S SIGNATURE. FOR THIS
 * REASON, YOU SHOULD NOT ERASE IT! YOUR CODE WILL BE UNCOMPILABLE IF YOU DO!
 * ********************************************************************************** */

import pqueue.exceptions.UnimplementedMethodException;

import java.util.Arrays;
import java.util.Iterator;


/**
 * <p>{@link ArrayMinHeap} is a {@link MinHeap} implemented using an internal array. Since heaps are <b>complete</b>
 * binary trees, using contiguous storage to store them is an excellent idea, since with such storage we avoid
 * wasting bytes per {@code null} pointer in a linked implementation.</p>
 *
 * <p>You <b>must</b> edit this class! To receive <b>any</b> credit for the unit tests related to this class,
 * your implementation <b>must</b> be a <b>contiguous storage</b> implementation based on a linear {@link java.util.Collection}
 * like an {@link java.util.ArrayList} or a {@link java.util.Vector} (but *not* a {@link java.util.LinkedList} because it's *not*
 * contiguous storage!). or a raw Java array. We provide an array for you to start with, but if you prefer, you can switch it to a
 * {@link java.util.Collection} as mentioned above. </p>
 *
 * @author -- YOUR NAME HERE ---
 *
 * @see MinHeap
 * @see LinkedMinHeap
 * @see demos.GenericArrays
 */

public class ArrayMinHeap<T extends Comparable<T>> implements MinHeap<T> {

	/* *****************************************************************************************************************
	 * This array will store your data. You may replace it with a linear Collection if you wish, but
	 * consult this class' 	 * JavaDocs before you do so. We allow you this option because if you aren't
	 * careful, you can end up having ClassCastExceptions thrown at you if you work with a raw array of Objects.
	 * See, the type T that this class contains needs to be Comparable with other types T, but Objects are at the top
	 * of the class hierarchy; they can't be Comparable, Iterable, Clonable, Serializable, etc. See GenericArrays.java
	 * under the package demos* for more information.
	 * *****************************************************************************************************************/
	private Object[] data;

	/* *********************************************************************************** *
	 * Write any further private data elements or private methods for LinkedMinHeap here...*
	 * *************************************************************************************/
	// variables
	private int current_size;
	private int itr_index = 0;
	
	// methods
	private void resize(int newCapacity) {
		Object[] temp = new Object[newCapacity];
		for (int i = 0; i < current_size; i++)
			temp[i] = data[i];
		data = temp;
	}
	
	private int getLChildPos(int index) {
		return (2*index) + 1;
	}
	
	private int getRChildPos(int index) {
		return (2*index) + 2;
	}
	
	private boolean isLeaf(int index) {
		return (index <= current_size && index >= current_size/2);
	}

	/* *********************************************************************************************************
	 * Implement the following public methods. You should erase the throwings of UnimplementedMethodExceptions.*
	 ***********************************************************************************************************/

	/**
	 * Default constructor initializes the data structure with some default
	 * capacity you can choose.
	 */
	public ArrayMinHeap(){
		current_size = 0;
		data = new Object[current_size + 1];
	}

	/**
	 *  Second, non-default constructor which provides the element with which to initialize the heap's root.
	 *  @param rootElement the element to create the root with.
	 */
	public ArrayMinHeap(T rootElement){
		current_size = 1;
		data = new Object[current_size + 1];
		data[0] = rootElement;
	}

	/**
	 * Copy constructor initializes {@code this} as a carbon copy of the {@link MinHeap} parameter.
	 *
	 * @param other The MinHeap object to base construction of the current object on.
	 */
	public ArrayMinHeap(MinHeap<T> other){
		for (T item : other) {
			insert(item);
		}
	}

	/**
	 * Standard {@code equals()} method. We provide it for you: DO NOT ERASE! Consider its implementation when implementing
	 * {@link #ArrayMinHeap(MinHeap)}.
	 * @return {@code true} if the current object and the parameter object
	 * are equal, with the code providing the equality contract.
	 * @see #ArrayMinHeap(MinHeap)
	 */
	@Override
	public boolean equals(Object other){
		if(other == null || !(other instanceof MinHeap))
			return false;
		Iterator itThis = iterator();
		Iterator itOther = ((MinHeap) other).iterator();
		while(itThis.hasNext())
			if(!itThis.next().equals(itOther.next()))
				return false;
		return !itOther.hasNext();
	}


	@Override
	public void insert(T element) {
		if (current_size == data.length) {
			// recurrent_size here
			resize(2*data.length);
		}
		data[current_size++] = element;
		heapify(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deleteMin() throws EmptyHeapException { // DO *NOT* ERASE THE "THROWS" DECLARATION!
		if (isEmpty()) {
			throw new EmptyHeapException("Heap is empty!");
		}
		T min = getMin();

		data[0] = data[current_size-1];
		heapify(0);
		data[current_size-1] = null;
		current_size--;

		return min;
	}
	
	@SuppressWarnings("unchecked")
	private void heapify(int pos) {
		if (!isLeaf(pos)) {
			T curr = (T)data[pos];
			T lChild = null;
			T rChild = null;
			if (data[getLChildPos(pos)] != null) {
				lChild = (T)data[getLChildPos(pos)];
				if (getRChildPos(pos) < current_size) {	
					rChild = (T)data[getRChildPos(pos)];			
					if (curr.compareTo(lChild) > 0 || curr.compareTo(rChild) > 0) {
						if (lChild.compareTo(rChild) < 0) {
							T temp = (T) data[pos];
							data[pos] = lChild;
							data[getLChildPos(pos)] = temp;
							heapify(getLChildPos(pos));
						} else {
							T temp = (T) data[pos];
							data[pos] = rChild;
							data[getRChildPos(pos)] = temp;
							heapify(getRChildPos(pos));
						}
					}
				} else {
					T temp = (T) data[pos];
					data[pos] = lChild;
					data[getLChildPos(pos)] = temp;
				}			
			} 	
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getMin() throws EmptyHeapException {	// DO *NOT* ERASE THE "THROWS" DECLARATION!
		return (T) data[0];
	}

	@Override
	public int size() {
		return current_size;
	}

	@Override
	public boolean isEmpty() {
		return (current_size == 0);
	}

	/**
	 * Standard equals() method.
	 * @return {@code true} if the current object and the parameter object
	 * are equal, with the code providing the equality contract.
	 */


	@Override
	public Iterator<T> iterator() {
		Object[] lst = new Object[current_size];
		for (int i = 0; i < current_size; i++)
			lst[i] = data[i];
		
		Arrays.sort(lst);
		return new Iterator<T>() {			
			@Override
			public boolean hasNext() {
				return (itr_index != current_size);
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				return (T)lst[itr_index++];
			}
		};
	}

}
