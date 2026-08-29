/*
 * Jordyn Rylander
 * CSD-420
 * Module 2.2
 * August 22, 2026
 *
 * a program that generates random integers and doubles, and appends them to a data file.
 * if the file does not exist, it will be created. 
 * */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

/**
 * generates random integers and doubles, and appends them to a data file.
 *
 * @author Jordyn Rylander
 */
public class RylanderDataWriter {

    private static final String DATA_FILE = "Jordyn datafile.dat";

    /**
     * The main method generates random integers and doubles, and appends them to a data file.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        int[] randomIntegers = new int[5];
        double[] randomDoubles = new double[5];
        Random random = new Random();

        // Fill the integer array with values from 1 through 100.
        for (int i = 0; i < randomIntegers.length; i++) {
            randomIntegers[i] = random.nextInt(100) + 1;
        }

        // Fill the double array with values from 0.0 through 100.0, rounded to two decimal places.
        for (int i = 0; i < randomDoubles.length; i++) {
            double value = random.nextDouble() * 100;
            randomDoubles[i] = Math.round(value * 100.0) / 100.0;
        }

        /*
         * Append the random integers and doubles to the data file. If the file does not exist, it will be created.
         * The data is also printed to the console for verification.    
         */
        try (PrintWriter output =
                new PrintWriter(new FileWriter(DATA_FILE, true))) {

            output.println("Random integers: "
                    + Arrays.toString(randomIntegers));
            output.println("Random doubles: "
                    + Arrays.toString(randomDoubles));
            output.println();

            System.out.println("The following data was added to "
                    + DATA_FILE + ":");
            System.out.println("Random integers: "
                    + Arrays.toString(randomIntegers));
            System.out.println("Random doubles: "
                    + Arrays.toString(randomDoubles));

        } catch (IOException error) {
            System.out.println("The data could not be written to the file.");
            System.out.println(error.getMessage());
        }
    }
}