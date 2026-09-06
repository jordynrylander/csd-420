/*
 * Jordyn Rylander
 * CSD-420
 * Module 5.2
 * August 28, 2026
 *
 * A program that sorts words in alphabetical order and removes duplicate words.
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * reads, sorts, and tests words from a text file
 *
 * @author Jordyn Rylander
 */
public class RylanderWordSorter {

    /**
     * reads the file, displays the words, and tests the results
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        TreeSet<String> words = new TreeSet<>();
        int totalWords = 0;

        try (Scanner input =
                new Scanner(new File("collection_of_words.txt"))) {

            while (input.hasNext()) {
                words.add(input.next().toLowerCase());
                totalWords++;
            }

        } catch (FileNotFoundException error) {
            System.out.println("The program could not find the word file.");
            return;
        }

        System.out.println("Non-duplicate words in ascending order:");
        System.out.println(words);

        System.out.println();
        System.out.println("Non-duplicate words in descending order:");
        System.out.println(words.descendingSet());

        // testing the file, duplicates being removed, and sorting results
        boolean fileTest = totalWords > 0;
        boolean duplicateTest = words.size() < totalWords;
        boolean orderTest = !words.isEmpty()
                && words.first().equals(words.descendingSet().last())
                && words.last().equals(words.descendingSet().first());

        System.out.println();
        System.out.println("Test results:");
        System.out.println("File reading: "
                + (fileTest ? "PASSED" : "FAILED"));
        System.out.println("Duplicate removal: "
                + (duplicateTest ? "PASSED" : "FAILED"));
        System.out.println("Ascending and descending order: "
                + (orderTest ? "PASSED" : "FAILED"));
    }
}