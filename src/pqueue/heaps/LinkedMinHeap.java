package pqueue.heaps; // ******* <---  DO NOT ERASE THIS LINE!!!! *******

import java.util.ArrayList;

/* *****************************************************************************************
 * THE FOLLOWING IMPORT IS NECESSARY FOR THE ITERATOR() METHOD'S SIGNATURE. FOR THIS
 * REASON, YOU SHOULD NOT ERASE IT! YOUR CODE WILL BE UNCOMPILABLE IF YOU DO!
 * ********************************************************************************** */

import java.util.Iterator;

import pqueue.exceptions.UnimplementedMethodException;
import pqueue.trees.EmptyTreeException;

/**
 * <p>A {@link LinkedMinHeap} is a tree (specifically, a <b>complete</b> binary tree) where every node is
 * smaller than or equal to its descendants (as defined by the {@link Comparable#compareTo(Object)} overridings of the type T).
 * Percolation is employed when the root is deleted, and insertions guarantee maintenance of the heap property in logarithmic time. </p>
 *
 * <p>You <b>must</b> edit this class! To receive <b>any</b> credit for the unit tests related to this class,
 * your implementation <b>must</b> be a &quot;linked&quot;, <b>non-contiguous storage</b> implementation based on a
 * binary tree of nodes and references. Use the skeleton code we have provided to your advantage, but always remember
 * that the only functionality our tests can test is {@code public} functionality.</p>
 * 
 * @author --- YOUR NAME HERE! ---
 *
 * @param <T> The {@link Comparable} type of object held by {@code this}.
 *
 * @see MinHeap
 * @see ArrayMinHeap
 */
public class LinkedMinHeap<T extends Comparable<T>> implements MinHeap<T> {

	/* ***********************************************************************
	 * An inner class representing a minheap's node. YOU *SHOULD* BUILD YOUR *
	 * IMPLEMENTATION ON TOP OF THIS CLASS!                                  *
 	 * ********************************************************************* */
	private class MinHeapNode {
		private T data;
		private MinHeapNode lChild, rChild;

		/* *******************************************************************
		 * Write any further data elements or methods for MinHeapNode here...*
		 ********************************************************************* */

		public MinHeapNode(T data) {
			this.data = data;
		}
	}

	/* *********************************
	  * Root of your tree: DO NOT ERASE!
	  * *********************************
	 */
	private MinHeapNode root;




    /* *********************************************************************************** *
     * Write any further private data elements or private methods for LinkedMinHeap here...*
     * *************************************************************************************/
	private int size;
	private int index = 0;
	
	/* Helper methods for insert: */
	private MinHeapNode build(T newElt, MinHeapNode rt) {
		
		if (rt == null) {
			size++;
			return new MinHeapNode(newElt);
		} 
		else if (rt.lChild != null && rt.rChild != null) {
			int balanceOfLChild = balance(rt.lChild);
			int balanceOfRChild = balance(rt.rChild);
			int lHeight = height(rt.lChild);
			int rHeight = height(rt.rChild);
			
			if (lHeight == rHeight && (balanceOfRChild == 0)) {
				build(newElt,rt.lChild);
			} else if ((lHeight != rHeight) && (balanceOfLChild != 0)) {
				build(newElt,rt.lChild);
			}
			else {
				build(newElt,rt.rChild);
			}
		}
		else if (rt.lChild == null) {
			rt.lChild = build(newElt,rt.lChild);
		}
		else if (rt.lChild != null && rt.rChild == null) {
			rt.rChild = build(newElt,rt.rChild);
		} 
		/*
		if (rt == null) {
			size++;
			return new MinHeapNode(newElt);
		}
		if (rt.lChild == null) {
			rt.lChild = build(newElt,rt.lChild);
			return rt;
		}
		if (rt.rChild == null) {
			rt.rChild = build(newElt,rt.rChild);
			return rt;
		}*/
		return rt;
	}
	
	private int height(MinHeapNode rt) {
		if (rt == null) {
			return -1;
		} else {
			int lHeight = height(rt.lChild);
			int rHeight = height(rt.rChild);
			
			if (rHeight > lHeight)
				return rHeight + 1;
			else
				return lHeight + 1;
		}
		
	}
	
	private int balance(MinHeapNode rt) {
		return height(rt.lChild) - height(rt.rChild);
	}
	
	
	private void inOrder(MinHeapNode n,ArrayList<T> list) {	
		if (n.lChild != null)
			inOrder(n.lChild, list);
		list.add(n.data);
		if (n.rChild != null)
			inOrder(n.rChild, list);
	}
	



    /* *********************************************************************************************************
     * Implement the following public methods. You should erase the throwings of UnimplementedMethodExceptions.*
     ***********************************************************************************************************/

	/**
	 * Default constructor.
	 */
	public LinkedMinHeap() {
		root = null;
		size = 0;
	}

	/**
	 * Second constructor initializes {@code this} with the provided element.
	 *
	 * @param rootElement the data to create the root with.
	 */
	public LinkedMinHeap(T rootElement) {
		root = new MinHeapNode(rootElement);
		root.lChild = null;
		root.rChild = null;
	}

	/**
	 * Copy constructor initializes {@code this} as a carbon
	 * copy of the parameter, which is of the general type {@link MinHeap}!
	 * Since {@link MinHeap} is an {@link Iterable} type, we can access all
	 * of its elements in proper order and insert them into {@code this}.
	 *
	 * @param other The {@link MinHeap} to copy the elements from.
	 */
	public LinkedMinHeap(MinHeap<T> other) {
		throw new UnimplementedMethodException();
	}


    /**
     * Standard {@code equals} method. We provide this for you. DO NOT EDIT!
     * You should notice how the existence of an {@link Iterator} for {@link MinHeap}
     * allows us to access the elements of the argument reference. This should give you ideas
     * for {@link #LinkedMinHeap(MinHeap)}.
     * @return {@code true} If the parameter {@code Object} and the current MinHeap
     * are identical Objects.
     *
     * @see Object#equals(Object)
     * @see #LinkedMinHeap(MinHeap)
     */
	/**
	 * Standard equals() method.
	 *
	 * @return {@code true} If the parameter Object and the current MinHeap
	 * are identical Objects.
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof MinHeap))
			return false;
		Iterator itThis = iterator();
		Iterator itOther = ((MinHeap) other).iterator();
		while (itThis.hasNext())
			if (!itThis.next().equals(itOther.next()))
				return false;
		return !itOther.hasNext();
	}

	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			root = new MinHeapNode(element);
			size = 1;
		} else {
			root = build(element,root);
			if (size > 1)
				root = sort(root);
		}
	}
	
	// helper
	public T getRootData() {
		return root.data;
	}
	
	
	private MinHeapNode sort(MinHeapNode rt) {
		MinHeapNode curr = rt;
		// Case 1: lChild is not null, rChild is null
		// if root is less than lChild, return root
		// else switch values
		if (curr.lChild != null && curr.rChild == null) {
			if (curr.data.compareTo(curr.lChild.data) < 0) {
				return curr;
			} else {
				T temp = curr.data;
				curr.data = curr.lChild.data;
				curr.lChild.data = temp;
			}
		} 
		// Case 2: both children are not null
		else if (curr.lChild != null && curr.rChild != null) {
			if ((curr.data.compareTo(curr.lChild.data) < 0) &&
					(curr.data.compareTo(curr.rChild.data) < 0)) {
				return curr;
			} else {
				if (curr.lChild.data.compareTo(curr.rChild.data) < 0) {
					T temp = curr.data;
					curr.data = curr.lChild.data;
					curr.lChild.data = temp;
				} else {
					T temp = curr.data;
					curr.data = curr.rChild.data;
					curr.rChild.data = temp;
				}
			}
		}	
		return rt;
	}
	
	

	@Override
	public T getMin() throws EmptyHeapException {		// DO *NOT* ERASE THE "THROWS" DECLARATION!
		if(isEmpty())
			throw new EmptyHeapException("Min Heap is empty.");
		return root.data;
	}

	@Override
	public T deleteMin() throws EmptyHeapException {    // DO *NOT* ERASE THE "THROWS" DECLARATION!
		throw new UnimplementedMethodException();
	}

	@Override
	public Iterator<T> iterator() {
		ArrayList<T> elementList = new ArrayList<T>();
		inOrder(root,elementList);

		return new Iterator<T>() {			
			@Override
			public boolean hasNext() {
				return (index != size);
			}

			@Override
			public T next() {
				return elementList.get(index++);
			}
		};
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



