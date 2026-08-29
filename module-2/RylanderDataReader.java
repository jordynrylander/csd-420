/*
 * Jordyn Rylander
 * CSD-420
 * Module 2.2
 * August 22, 2026
 *
 * a program that generates random integers and doubles, and appends them to a data file.
 * if the file does not exist, it will be created.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * opens the data file and displays each stored line.
 *
 * @author Jordyn Rylander
 */
public class RylanderDataReader {

    private static final String DATA_FILE = "Jordyn datafile.dat";

    /**
     * The main method opens the data file and displays each stored line.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        File dataFile = new File(DATA_FILE);

        // Check if the data file exists before attempting to read it. If it does not exist, display an error message and exit the program.
        if (!dataFile.exists()) {
            System.out.println(DATA_FILE + " does not exist.");
            System.out.println("Run RylanderDataWriter first.");
            return;
        }

        System.out.println("Data stored in " + DATA_FILE + ":");
        System.out.println();

        // Open the data file and read each line, displaying it to the console. If the file cannot be opened, display an error message.
        try (Scanner input = new Scanner(dataFile)) {
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
        } catch (FileNotFoundException error) {
            System.out.println("The data file could not be opened.");
            System.out.println(error.getMessage());
        }
    }
}