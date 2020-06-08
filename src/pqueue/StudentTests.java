package pqueue;

import org.junit.Test;
import pqueue.exceptions.InvalidCapacityException;
import pqueue.exceptions.InvalidPriorityException;
import pqueue.heaps.ArrayMinHeap;
import pqueue.heaps.EmptyHeapException;
import pqueue.heaps.LinkedMinHeap;
import pqueue.heaps.MinHeap;
import pqueue.priorityqueues.EmptyPriorityQueueException;
import pqueue.priorityqueues.LinearPriorityQueue;
import pqueue.priorityqueues.MinHeapPriorityQueue;
import pqueue.priorityqueues.PriorityQueue;

import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * {@link StudentTests} is a {@code jUnit} testing library which you should extend with your own tests.
 *
 * @author  <a href="https://github.com/JasonFil">Jason Filippou</a> and --- YOUR NAME HERE! ----
 */
public class StudentTests {

//    private static String throwableInfo(Throwable thrown){
//        return "Caught a " + thrown.getClass().getSimpleName() +
//                " with message: " + thrown.getMessage();
//    }
//
//    private MinHeap<String> myHeap;
//    private PriorityQueue<String> myQueue;
//
//    @Test
//    public void initAndAddOneElement() throws InvalidPriorityException {
//        try {
//            myHeap = new ArrayMinHeap<>();
//            myQueue = new MinHeapPriorityQueue<>();
//        } catch(Throwable t){
//            fail(throwableInfo(t));
//        }
//        assertTrue("After initialization, all MinHeap and PriorityQueue implementations should report that they are empty.",
//                myHeap.isEmpty() && myQueue.isEmpty());
//        assertTrue("After initialization, all MinHeap and PriorityQueue implementations should report a size of 0.",
//                (myHeap.size() == 0) && (myQueue.size() == 0));
//        myHeap.insert("Mary");
//        assertEquals("After inserting an element, ArrayMinHeap instances should report a size of 1.", 1, myHeap.size());
//
//        // MinHeap::enqueue() declares that it checks InvalidPriorityException if priority <= 0 (from the docs of MinHeap).
//        // In this case, we know for sure that InvalidPriorityException should *not* be thrown, since priority = 2 >= 0.
//        // To avoid cluttering a code with "dummy" try-catch blocks, we declare InvalidPriorityException as checked from
//        // this test as well. This is why we have the throws declaration after the name of the test.
//        myQueue.enqueue("Jason", 2);
//        assertEquals("After inserting an element, MinHeapPriorityQueue instances should report a size of 1.", 1, myQueue.size());
//    }
//
//    // Here is one simple way to write tests that expect an Exception to be thrown. Another, more powerful method is to
//    // use the class org.junit.rules.ExpectedException: https://junit.org/junit4/javadoc/4.12/org/junit/rules/ExpectedException.html
//    @Test(expected = InvalidCapacityException.class)
//    public void ensureInvalidCapacityExceptionThrown() throws InvalidCapacityException{
//         myQueue = new LinearPriorityQueue<>(-2);
//    }
//
//    @Test(expected = InvalidPriorityException.class)
//    public void ensureInvalidPriorityExceptionThrown() throws InvalidPriorityException, InvalidCapacityException{
//        myQueue = new LinearPriorityQueue<>(4);
//        myQueue.enqueue("Billy", -1);
//    }
//
//    @Test
//    public void testEnqueingOrder() throws InvalidPriorityException, EmptyPriorityQueueException {
//        myQueue = new MinHeapPriorityQueue<>();
//        myQueue.enqueue("Ashish", 8);
//        myQueue.enqueue("Diana", 2);        // Lower priority, so should be up front.
//        myQueue.enqueue("Adam", 2);        // Same priority, but should be second because of FIFO.
//        assertEquals("We were expecting Diana up front.", "Diana", myQueue.getFirst());
//    }
//
//    @Test
//    public void testDequeuingOrder() throws InvalidPriorityException, EmptyPriorityQueueException {
//        testEnqueingOrder();    // To populate myQueue with the same elements.
//        myQueue.dequeue();      // Now Adam should be up front.
//        assertEquals("We were expecting Adam up front.", "Adam", myQueue.getFirst());
//    }

    /* ******************************************************************************************************** */
    /* ********************** YOU SHOULD ADD TO THESE UNIT TESTS BELOW. *************************************** */
    /* ******************************************************************************************************** */

    @Test
    public void testLinkedminHeapisEmpty() {
        LinkedMinHeap<Integer> heap = new LinkedMinHeap<Integer>();
        
        assertTrue(heap.isEmpty());
    }
    
    @Test
    public void testInsertOneElement() {
    	LinkedMinHeap<Integer> heap = new LinkedMinHeap<Integer>();
        heap.insert(0);
        
        assertEquals(1,heap.size());
    }
    
    @Test
    public void testMakeMinHeap() {
    	LinkedMinHeap<Integer> heap = new LinkedMinHeap<Integer>();
    	Integer[] a = new Integer[1];
    	
    	heap.insert(0);
    	heap.insert(1);
    	heap.insert(2);
    	
    	// Test if lChild and rChild of node 0 is correct.
    	int lChild_0 = heap.getLChild(0,a,heap.getRoot());
    	int rChild_0 = heap.getRChild(0,a,heap.getRoot());
    	
    	assertEquals("Expected left child of 0 is 1.",1,lChild_0);
    	assertEquals("Expected right child of 0 is 2.",2,rChild_0);
    }
    
    @Test
    public void testMakeHeap2Levels() {
    	LinkedMinHeap<Integer> heap = new LinkedMinHeap<Integer>();
    	Integer[] a = new Integer[1];
    	
    	// level 0
    	heap.insert(0);
    	// level 1
    	heap.insert(1);
    	heap.insert(2);
    	// level 2
    	heap.insert(3);
    	heap.insert(4);
    	heap.insert(5);
    	heap.insert(6);
    	// level 3
    	heap.insert(7);
    	 	
    	
    	// Test if lChild and rChild of node 0 is correct.
    	int lChild_0 = heap.getLChild(0,a,heap.getRoot());
    	int rChild_0 = heap.getRChild(0,a,heap.getRoot());
    	
    	assertEquals("Expected left child of 0 is 1.",1,lChild_0);
    	assertEquals("Expected right child of 0 is 2.",2,rChild_0);
    	
    	// Test if lChild and rChild of node 1 is correct.
    	int lChild_1 = heap.getLChild(1,a,heap.getRoot());
    	int rChild_1 = heap.getRChild(1,a,heap.getRoot());
    	
    	assertEquals("Expected left child of 1 is 3.",3,lChild_1);
    	assertEquals("Expected right child of 1 is 4.",4,rChild_1);
    	
    	// Test if lChild and rChild of node 2 is correct.
    	int lChild_2 = heap.getLChild(2,a,heap.getRoot());
    	int rChild_2 = heap.getRChild(2,a,heap.getRoot());
    	
    	assertEquals("Expected left child of 2 is 5.",5,lChild_2);
    	assertEquals("Expected right child of 2 is 6.",6,rChild_2);
    	
    	// Test if lChild of node 3 is correct.
    	int lChild_3 = heap.getLChild(3,a,heap.getRoot());
    	
    	assertEquals("Expected left child of 3 is 7.",7,lChild_3);
    }
    
    @Test
    public void testDeleteMin() throws EmptyHeapException {
    	LinkedMinHeap<Integer> heap = new LinkedMinHeap<Integer>();
    	Integer[] a = new Integer[1];
    	
    	heap.insert(0);
    	heap.insert(1);
    	heap.insert(2);
    	
    	heap.deleteMin();
    	
    	int lChild_1 = heap.getLChild(1,a,heap.getRoot());
    	int heapMin = heap.getMin();
    	
    	assertEquals("Expected new minimum is 1.",1,heapMin);
    	assertEquals("Expected left child of 1 is 2.",2,lChild_1);
    }
    
    @Test
    public void testCopyConstructor() {
    	LinkedMinHeap<Integer> heap = new LinkedMinHeap<Integer>();
    	Integer[] a = new Integer[1];
    	
    	heap.insert(0);
    	heap.insert(1);
    	heap.insert(2);
    	
    	LinkedMinHeap<Integer> heap1 = new LinkedMinHeap<Integer>(heap);
    	
    	// Test if lChild and rChild of node 0 is correct.
    	int lChild_0 = heap.getLChild(0,a,heap.getRoot());
    	int rChild_0 = heap.getRChild(0,a,heap.getRoot());
    	
    	assertEquals("Expected left child of 0 is 1.",1,lChild_0);
    	assertEquals("Expected right child of 0 is 2.",2,rChild_0);
    }
    
    @Test
    public void testMakeArrayMinHeap() {
    	// Initialize empty array
    	ArrayMinHeap<Integer> arrHeap0 = new ArrayMinHeap<Integer>();
    	
    	assertTrue(arrHeap0.isEmpty());
    	
    	// Initialize nonempty array
    	ArrayMinHeap<Integer> arrHeap1 = new ArrayMinHeap<Integer>(1);
    	
    	assertFalse(arrHeap1.isEmpty());
    }
    
    @Test
    public void testInsertArrayMinHeap() {
    	// Insert first element into empty heap
    	ArrayMinHeap<Integer> arrHeap0 = new ArrayMinHeap<Integer>();
    	arrHeap0.insert(0);
    	
    	assertEquals("Expected size is 1.",1,arrHeap0.size());  	
    	// Insert second element into empty heap
    	arrHeap0.insert(1);
    	
    	assertEquals("Expected size is 2.",2,arrHeap0.size());	
    	// Insert elements into nonempty heap
    	ArrayMinHeap<Integer> arrHeap1 = new ArrayMinHeap<Integer>(10);
    	arrHeap1.insert(20);
    	arrHeap1.insert(30);
    	
    	assertEquals("Expected size is 3.",3,arrHeap1.size());
    }
    
    @Test
    public void testDeleteMinArrayMinHeap() throws EmptyHeapException {
    	ArrayMinHeap<Integer> arrHeap1 = new ArrayMinHeap<Integer>(10);
    	int min = 0;
    	
    	arrHeap1.insert(20);
    	arrHeap1.insert(30);
    	
    	arrHeap1.deleteMin();
    	min = arrHeap1.getMin();
    	
    	assertEquals("Expected size is 2.",2,arrHeap1.size());
    	assertEquals("Expected current min value is 20.",20,min);
    }
    	
}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
