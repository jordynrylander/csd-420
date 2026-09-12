/*
 * Jordyn Rylander
 * CSD-420
 * Module 5.2
 * August 28, 2026
 *
 * A program that demonstrates generic bubble sorting with Comparable and Comparator.
 * 
 * 
 */

import java.util.Arrays;
import java.util.Comparator;

/**
 * demonstrates generic bubble sorting with Comparable and Comparator
 * @author Jordyn Rylander
 */
public class RylanderBubbleSort {

    /**
     * reads the file, displays the words, and tests the results
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Integer[] firstNumbers = {9, 4, 7, 2, 8, 1, 5};
        Integer[] expectedAscending = {1, 2, 4, 5, 7, 8, 9};

        System.out.println("Original Comparable array:");
        System.out.println(Arrays.toString(firstNumbers));

        bubbleSort(firstNumbers);

        System.out.println("Sorted with Comparable:");
        System.out.println(Arrays.toString(firstNumbers));

        boolean comparableTest =
                Arrays.equals(firstNumbers, expectedAscending);

        System.out.println("Comparable test: "
                + (comparableTest ? "PASSED" : "FAILED"));

        Integer[] secondNumbers = {9, 4, 7, 2, 8, 1, 5};
        Integer[] expectedDescending = {9, 8, 7, 5, 4, 2, 1};

        Comparator<Integer> descendingOrder =
                (first, second) -> second.compareTo(first);

        System.out.println();
        System.out.println("Original Comparator array:");
        System.out.println(Arrays.toString(secondNumbers));

        bubbleSort(secondNumbers, descendingOrder);

        System.out.println("Sorted with Comparator:");
        System.out.println(Arrays.toString(secondNumbers));

        boolean comparatorTest =
                Arrays.equals(secondNumbers, expectedDescending);

        System.out.println("Comparator test: "
                + (comparatorTest ? "PASSED" : "FAILED"));

        System.out.println();
        System.out.println("Overall test: "
                + (comparableTest && comparatorTest
                        ? "PASSED" : "FAILED"));
    }

    /**
     * Sorts an array of Comparable objects in ascending order.
     *
     * @param <E> the type of elements in the array
     * @param array the array to sort
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    E temporaryValue = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporaryValue;
                }
            }
        }
    }

    /**
     *  Sorts an array of objects in the order specified by a Comparator.
     *
     * @param <E> the type of elements in the array
     * @param array the array to sort
     * @param comparator the Comparator that controls the sorting order
     */
    public static <E> void bubbleSort(
            E[] array, Comparator<E> comparator) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    E temporaryValue = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporaryValue;
                }
            }
        }
    }
}