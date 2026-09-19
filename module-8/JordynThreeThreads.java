/*
 * Jordyn Rylander
 * CSD-420
 * Module 8.2
 * 9/19/2026
 *
 * A program that demonstrates using three threads to display random characters in a JavaFX text area.
 */

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * demonstrates using three threads to display random characters in a JavaFX text area
 *
 * @author Jordyn Rylander
 */
public class JordynThreeThreads extends Application {

    private static final int CHARACTER_COUNT = 10000;
    private static final String SPECIAL_CHARACTERS = "!@#$%&*^";

    private final AtomicInteger letterCount = new AtomicInteger();
    private final AtomicInteger digitCount = new AtomicInteger();
    private final AtomicInteger specialCount = new AtomicInteger();
    private final AtomicInteger completedThreads = new AtomicInteger();

    private TextArea outputArea;

    /**
     * Creates the JavaFX application window and starts the threads to generate characters.
     *
     * @param primaryStage the main application window
     */
    @Override
    public void start(Stage primaryStage) {
        outputArea = new TextArea();
        outputArea.setWrapText(true);
        outputArea.setEditable(false);

        Scene scene = new Scene(outputArea, 800, 500);

        primaryStage.setTitle("Three Threads");
        primaryStage.setScene(scene);
        primaryStage.show();

        startThreads();
    }

    /**
     * Starts three threads to generate letters, digits, and special characters.
     */
    private void startThreads() {
        Thread letterThread = new Thread(() -> {
            for (int i = 0; i < CHARACTER_COUNT; i++) {
                displayCharacter(generateLetter());
                letterCount.incrementAndGet();
            }
            threadFinished();
        });

        Thread digitThread = new Thread(() -> {
            for (int i = 0; i < CHARACTER_COUNT; i++) {
                displayCharacter(generateDigit());
                digitCount.incrementAndGet();
            }
            threadFinished();
        });

        Thread specialThread = new Thread(() -> {
            for (int i = 0; i < CHARACTER_COUNT; i++) {
                displayCharacter(generateSpecialCharacter());
                specialCount.incrementAndGet();
            }
            threadFinished();
        });

        letterThread.start();
        digitThread.start();
        specialThread.start();
    }

    /**
     *  Generates a random letter character.
     *
     * @return a random letter from a through z
     */
    private char generateLetter() {
        return (char) ('a'
                + ThreadLocalRandom.current().nextInt(26));
    }

    /**
     * Generates a random digit character.
     *
     * @return a random digit from 0 through 9
     */
    private char generateDigit() {
        return (char) ('0'
                + ThreadLocalRandom.current().nextInt(10));
    }

    /**
     * Generates a random special character from the predefined set.
     *
     * @return a random special character
     */
    private char generateSpecialCharacter() {
        int index = ThreadLocalRandom.current().nextInt(
                SPECIAL_CHARACTERS.length());

        return SPECIAL_CHARACTERS.charAt(index);
    }

    /**
     * Displays a character in the text area on the JavaFX Application Thread.
     *
     * @param character the character to display
     */
    private void displayCharacter(char character) {
        Platform.runLater(() ->
                outputArea.appendText(String.valueOf(character)));
    }

    /**
     * Increments the count of completed threads and runs the tests if all threads have finished.
     */
    private void threadFinished() {
        if (completedThreads.incrementAndGet() == 3) {
            runTests();
        }
    }

    /**
     * Tests that the characters were generated correctly and displays the results.
     */
    private void runTests() {
        char letter = generateLetter();
        char digit = generateDigit();
        char special = generateSpecialCharacter();

        boolean letterTest = letter >= 'a' && letter <= 'z';
        boolean digitTest = digit >= '0' && digit <= '9';
        boolean specialTest =
                SPECIAL_CHARACTERS.indexOf(special) >= 0;

        boolean countTest =
                letterCount.get() == CHARACTER_COUNT
                && digitCount.get() == CHARACTER_COUNT
                && specialCount.get() == CHARACTER_COUNT;

        System.out.println("Letter test: "
                + (letterTest ? "PASSED" : "FAILED"));
        System.out.println("Digit test: "
                + (digitTest ? "PASSED" : "FAILED"));
        System.out.println("Special character test: "
                + (specialTest ? "PASSED" : "FAILED"));
        System.out.println("Letters generated: "
                + letterCount.get());
        System.out.println("Digits generated: "
                + digitCount.get());
        System.out.println("Special characters generated: "
                + specialCount.get());
        System.out.println("Overall test: "
                + (letterTest && digitTest
                && specialTest && countTest
                ? "PASSED" : "FAILED"));
    }

    /**
     * launches the JavaFX application
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}