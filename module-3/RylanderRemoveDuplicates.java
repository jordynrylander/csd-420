/*
 * Jordyn Rylander
 * CSD-420
 * Module 3.1 
 * August 28, 2026
 *
 * a program that tests a generic method that removes duplicate values from an ArrayList.
 *  
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * a program that tests a generic method for removing duplicate values from an ArrayList.
 * @author Jordyn Rylander
 */
public class RylanderRemoveDuplicates {

    /**
     * The main method creates an ArrayList of 50 random integers from 1 through 20, and then creates a new ArrayList that contains no duplicate values.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        // Add 50 random integers from 1 through 20 to the original list.
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // Create a new list that contains no duplicate values.
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        System.out.println("Original ArrayList with 50 random values:");
        System.out.println(originalList);

        System.out.println();
        System.out.println("New ArrayList with duplicate values removed:");
        System.out.println(uniqueList);
    }

    /**
     * A generic method that removes duplicate values from an ArrayList.
     * @param <E> the type of elements stored in the ArrayList
     * @param list the original ArrayList
     * @return a new ArrayList containing no duplicate values
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> uniqueList = new ArrayList<>();

        // Iterate through the original list and add each element to the new list only if it is not already present.
        for (E element : list) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }

        return uniqueList;
    }
}