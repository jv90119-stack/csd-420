// JOSE VELAZQUEZ
// MODULE 4.2 ASSIGNMENT 
// DATE: 04/12/2026
// This program tests the traversal times of a LinkedList using both 
// an Iterator and the get(index) method.


import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTraversalTest {

    public static void main(String[] args) {
        System.out.println("Testing LinkedList traversal times...\n");

        runTest(50000);
        System.out.println("\n--------------------------------------\n");
        runTest(500000);
    }

    public static void runTest(int size) {
        LinkedList<Integer> list = new LinkedList<>();

        // Fill the LinkedList with integers
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // Test to ensure data was stored correctly
        if (list.size() != size) {
            System.out.println("Error: List size is incorrect.");
            return;
        }

        if (!list.getFirst().equals(0) || !list.getLast().equals(size - 1)) {
            System.out.println("Error: List values are incorrect.");
            return;
        }

        System.out.println("Testing with " + size + " integers:");

        long iteratorTime = traverseWithIterator(list);
        long getIndexTime = traverseWithGet(list);

        System.out.println("Iterator traversal time: " + iteratorTime + " ms");
        System.out.println("get(index) traversal time: " + getIndexTime + " ms");
    }

    public static long traverseWithIterator(LinkedList<Integer> list) {
        long startTime = System.currentTimeMillis();

        Iterator<Integer> iterator = list.iterator();
        long sum = 0; // prevents optimization and confirms traversal

        while (iterator.hasNext()) {
            sum += iterator.next();
        }

        long endTime = System.currentTimeMillis();

        // Correctness check
        if (sum < 0) {
            System.out.println("Unexpected result.");
        }

        return endTime - startTime;
    }

    public static long traverseWithGet(LinkedList<Integer> list) {
        long startTime = System.currentTimeMillis();

        long sum = 0; // prevents optimization and confirms traversal

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        long endTime = System.currentTimeMillis();

        // Correctness check
        if (sum < 0) {
            System.out.println("Unexpected result.");
        }

        return endTime - startTime;
    }
}

/*
Explanation of Results:

This program compares two ways of traversing a LinkedList:

1. Using an Iterator
2. Using get(index)

Why Iterator is Faster:
A LinkedList stores elements as nodes connected. An Iterator moves
from one node to the next directly, which makes traversal efficient.

Why get(index) is Slower:
The get(index) method must start at the beginning or end of the LinkedList and
move through nodes until it reaches the requested index. Doing this inside a loop
causes repeated searches, slowing traversal.

Expected Results:
- With 50,000 integers, the Iterator should be much faster than get(index).
- With 500,000 integers, the difference becomes dramatically larger.
- The Iterator approach grows much more efficiently as the list size increases.
- The get(index) approach becomes inefficient because each access may require
stepping through many nodes.

For LinkedList traversal, an Iterator is a better choice because it allows access 
to each element in sequence. The get(index) method is not well-suited for 
LinkedList—especially for traversal with large datasets such as 50,000 or 500,000 elements. 
As the list grows, the time difference becomes much more noticeable.
*/