/*
 * Jordyn Rylander
 * CSD-420
 * Module 4 Programming Assignment
 * August 29, 2026
 *
 * a program that tests iterator and get(index) traversal times for a LinkedList. 
 *
 * 
 *
 * Results discussion:
 * During my first test with 50,000 integers, the iterator took 4.577
 * milliseconds, while get(index) took 800.847 milliseconds. When I
 * increased the list to 500,000 integers, the iterator only took 10.898
 * milliseconds, but get(index) took 546,316.806 milliseconds, which
 * equaled a little over nine minutes.
 *
 * I noticed that the iterator handled both list sizes much faster.
 * The iterator moved directly from one value to the next, while
 * get(index) searched through the LinkedList again for every index.
 * Both methods produced the same sum, which was expected, but the iterator
 * was much more efficient for traversing a LinkedList.
 *
 */
import java.util.Iterator;
import java.util.LinkedList;

/**
 * tests iterator and get(index) traversal times for a LinkedList
 *
 * @author Jordyn Rylander
 */
public class RylanderLinkedListTest {

    /**
     * runs the traversal tests with 50,000 and 500,000 integers
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        testTraversal(50_000);
        testTraversal(500_000);
    }

    /**
     * tests the traversal times for a LinkedList of integers using both an iterator and get(index).
     * 
     *
     * @param listSize the number of integers to store in the LinkedList
     */
    public static void testTraversal(int listSize) {
        LinkedList<Integer> numbers = new LinkedList<>();

        // Store sequential integers from 0 through listSize - 1.
        for (int i = 0; i < listSize; i++) {
            numbers.add(i);
        }

        System.out.println();
        System.out.println("Testing LinkedList with "
                + String.format("%,d", listSize) + " integers");

        System.out.println("Starting iterator traversal...");
        long iteratorStart = System.nanoTime();

        long iteratorSum = 0;
        Iterator<Integer> iterator = numbers.iterator();

        while (iterator.hasNext()) {
            iteratorSum += iterator.next();
        }

        long iteratorEnd = System.nanoTime();
        double iteratorTime =
                (iteratorEnd - iteratorStart) / 1_000_000.0;

        System.out.printf("Iterator traversal time: %.3f milliseconds%n",
                iteratorTime);

        System.out.println("Starting get(index) traversal...");
        System.out.flush();

        long indexStart = System.nanoTime();

        long indexSum = 0;

        for (int i = 0; i < numbers.size(); i++) {
            indexSum += numbers.get(i);
        }

        long indexEnd = System.nanoTime();
        double indexTime = (indexEnd - indexStart) / 1_000_000.0;

        System.out.printf("get(index) traversal time: %.3f milliseconds%n",
                indexTime);

        // Calculate the sum that both traversal methods should produce.
        long expectedSum = (long) listSize * (listSize - 1) / 2;

        boolean testPassed =
                iteratorSum == expectedSum && indexSum == expectedSum;

        System.out.println("Iterator sum: " + iteratorSum);
        System.out.println("get(index) sum: " + indexSum);
        System.out.println("Expected sum: " + expectedSum);
        System.out.println("Correctness test: "
                + (testPassed ? "PASSED" : "FAILED"));
    }
}