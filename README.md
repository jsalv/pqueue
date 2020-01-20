# Heaps and Priority Queues

## Overview

This is a "warmup" project, and covers material you should have
learned in CMSC132 and CMSC351. The goals are to ensure that you are:

 * familiar with the basic data structures on which we will
   build,
 * able to navigate git, and
 * able to upload your code to the submit server.
 
Please note that, per the syllabus, we **do not** accept late
submissions without an approved reason for an extension. This is
different than what you might be used to from other classes, so
ensure that you know what the deadline is on the submit server.

You will need to implement four classes:

 * `LinkedMinHeap.java`
 * `ArrayMinHeap.java`
 * `LinearPriorityQueue.java`
 * `MinHeapPriorityQueue.java`
 
If you are reading this on Gitlab, you will need to clone your own
copy. There are two ways to do this:

 1. You can simply clone this repository using either the HTTPS or SSH urls, or
 2. You can first create a personal fork, and clone that.
 
The second option is better, because you can then push your changes back to Gitlab, which means you now have a remote backup. This has several advantages:

 * If you ask a question on the discussion forum, we can see your
   code and provide a more precise response.
 * If your computer dies (which is going to happen to at least one
   person this semester, probably more), you have only lost the work
   you did since your last push.
 * If you want to work on multiple machines, it's as easy as pushing
   from one and pulling on the other. This is why git exists in the
   first place.

The starter code is in the `src/` directory, with the actual project
code in `src/pqueue/`. There is also JavaDoc in the `doc/` directory.
The code you need to modify is in `src/pqueue/heaps/` and
`src/pqueue/priorityqueues/`. You are welcome to add more directories,
files, and classes if you wish, but you are not required to.

## Prerequisites

We expect you to be familiar with Binary Search Trees, Stacks, Lists,
FIFO queues and programming in Java. Skills harnessed by a typical
UMD freshman course such as CMSC 131/132 are more than sufficient.
You will need to remind yourselves of what a binary heap is and how
insertions and min-deletions work, as well as the ways in which they
can be represented in computer memory. The structure and operation of
heaps are briefly touched upon in the next section.

In this class, we do not aim to test your programming, OOP or Java
expertise. We want to teach you Data Structures. However, a minimal
amount of familiarity with certain OOP/Java constructs will be
required. You would probably benefit from ensuring that you understand
the basics of Iterators and checked Exceptions. You should also be
familiar with what an interface is, what an anonymous inner class is,
how it differs from a functional interface, etc.

## A Brief Review of Heaps and Priority Queues

While we assume you are already familiar with these structures, we
will present a brief review, to refresh your memory.

### Heaps

#### Insertion

A *heap* is a complete binary tree (but *not* a complete binary search
tree). Insertions occur at the "rightmost" unoccupied space at the
leaf level. For example, in the diagram

```mermaid
graph TD;

3 --> 10;
3 --> 4;
10 --> 16;
```

we would add the next element below 10, to the right of 16. Let's
say we add 8 as the next value. This would then yield

```mermaid
graph TD;

3 --> 10;
3 --> 4;
10 --> 16;
10 --> 8;
```

However, heaps have another invariant: the subtree below an element
contains only elements that are greater than or equal to its value.
(Technically, this means we have a *minheap*. A *maxheap* would invert
this invariant.)
Because 8 is less than its parent 10, we need to *percolate* it
upwards.

```mermaid
graph TD;

3 --> 8;
3 --> 4;
8 --> 16;
8 --> 10;
```

8 is now less than or equal to all of the elements below it, and
greater than its parent 3. If we were now to insert 2, it would
become the first child of 4 (since the subtree under 8 is complete).

```mermaid
graph TD;

3 --> 8;
3 --> 4;
8 --> 16;
8 --> 10;
4 --> 2;
```

Again, this element is less than its parent (4), so it needs to
percolate upwards.

```mermaid
graph TD;

3 --> 8;
3 --> 2;
8 --> 16;
8 --> 10;
2 --> 4;
```

2 is still less than its parent (3), so we have to percolate again

```mermaid
graph TD;

2 --> 8;
2 --> 3;
8 --> 16;
8 --> 10;
3 --> 4;
```

#### Deletion

When deleting an element from the heap, we always delete the root element. Let's complete
the tree above as a starting point:

```mermaid
graph TD;

2 --> 8;
2 --> 3;
8 --> 16;
8 --> 10;
3 --> 4;
3 --> 12;
```

We then delete the root (2), and promote the *rightmost leaf* to be the new root:

```mermaid
graph TD;

12 --> 8;
12 --> 3;
8 --> 16;
8 --> 10;
3 --> 4;
```

This violates our invariant, but rather than percolate upwards from the insertion point,
we have to percolate *downwards* from the new root. We do this by percolating the new
element to the *lesser* of its children (which will be less than or equal to its former
sibling, which is now its child).

```mermaid
graph TD;

3 --> 8;
3 --> 12;
8 --> 16;
8 --> 10;
12 --> 4;
```

We continue this until the element we moved respects the invariant

```mermaid
graph TD;

3 --> 8;
3 --> 4;
8 --> 16;
8 --> 10;
4 --> 12;
```

#### Efficient Representation

Since heaps are complete binary trees, they can be implemented very efficiently
and compactly using an array. This is based on a breadth-first (level-order)
enumeration of the nodes in the heap.  This enumeration is exemplified as follows:

```mermaid
graph TD;

8 --> 10;
8 --> 13;
10 --> 16;
10 --> 15;
13 --> 20;
```

|  0  |  1  |  2  |  3  |  4  |  5  |
| --- | --- | --- | --- | --- | --- |
|  8  | 10  | 13  | 16  | 15  | 20  |

Note that the node at index $`i`$ has children at indices $`2i+1`$ and $`2i+2`$ (one
or two children might not even exist, of course), whereas the parent of index
$`i`$ (if it exists) is at index $`\lfloor \frac{i-1}{2} \rfloor`$.

The linked structure is what you will implement in `LinkedMinHeap`, and you will need
to implement the same data structure in this array form in `ArrayMinHeap`. Obviously,
you will need to modify not only how the data is represented in memory, but how the
percolation operations work. Since the functionality provided by these data structures
is identical, you can use the same unit tests you develop for the linked version when
testing the array version. All you should need to do is change the type referenced in the
tests.

### Priority Queues

The Priority Queue is an Abstract Data Type (ADT) with a very simple property:  Every
element to be enqueued is attached a certain positive integer priority, which predetermines
its order in the queue. By convention, smaller integers are considered "higher" in
terms of priority, such that, for example, priority 1 is considered a higher priority than
3.  Dequeueings only happen from the top of the queue, after which the element "before"
the first one will be available for immediate processing.  We see these kinds of queues all
the time in real life.

A simple FIFO (first-in, first-out) queue might look like

```mermaid
graph LR;

N --> F --> L --> D --> A --> G --> W;
```

where N is the head of the queue. Adding a new element C works as we'd expect from 132:

```mermaid
graph LR;

N --> F --> L --> D --> A --> G --> W --> C;
```

A priority queue, by contrast, assigns a priority to each of these:

```mermaid
graph LR;

N,1 --> F,2 --> L,4 --> D,6 --> A,8 --> G,11 --> W,15;
```

Now we're not just adding C, because it too has a priority (say it's 5). We would not
add C,5 at the end, because C has a higher priority (5) than W (15). Instead, we have
to insert it between L and D:

```mermaid
graph LR;

N,1 --> F,2 --> L,4 --> C,5 --> D,6 --> A,8 --> G,11 --> W,15;
```

The obvious thing to do is to scan through the queue linearly until you find the
appropriate place for the new element. This is how you will implement
`LinearPriorityQueue`. This is inefficient, however ($`\mathcal{O}(n)`$), so you will
be implementing a second version, using a binary minheap (which you have already done
in this project!). This will be `MinHeapPriorityQueue`. Since minheaps are complete and
balanced, enqueuing and dequeueing should be $`\mathcal{O}(\log_2 n)`$. This makes minheap
a very good choice for implementing a priority queue.

## Tips, Hints, and Guidelines

## Submission and Grading


