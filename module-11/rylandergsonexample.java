/*
 * Jordyn Rylander
 * CSD-420
 * Module 11.2
 * 10/10/2026
 *
 * Program that demostrates how Gson can be used to convert a Java object 
 * into a JSON string and vice versa.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Demonstrates how Gson can be used to convert a Java object into a JSON string and vice versa.
 *
 * @author Jordyn Rylander
 */
public class RylanderGsonExample {

    /**
     * Represents a suspect with a name, alibi, and number of clues.
     */
    static class Suspect {
        private String name;
        private String alibi;
        private int clueCount;

        /**
         * Creates a new Suspect object with the specified name, alibi, and clue count.
         *
         * @param name the suspect's name
         * @param alibi the suspect's alibi
         * @param clueCount the number of clues connected to the suspect
         */
        Suspect(String name, String alibi, int clueCount) {
            this.name = name;
            this.alibi = alibi;
            this.clueCount = clueCount;
        }
    }

    /**
     * The main method that demonstrates converting a Java object to JSON and back.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Suspect originalSuspect =
                new Suspect("Rachel Green", "Central Perk", 3);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        // Convert the Java object into a JSON string.
        String json = gson.toJson(originalSuspect);

        System.out.println("Java object converted to JSON:");
        System.out.println(json);

        // Convert the JSON string back into a Java object.
        Suspect convertedSuspect =
                gson.fromJson(json, Suspect.class);

        System.out.println();
        System.out.println("JSON converted back to Java:");
        System.out.println("Name: " + convertedSuspect.name);
        System.out.println("Alibi: " + convertedSuspect.alibi);
        System.out.println("Clue count: " + convertedSuspect.clueCount);
    }
}