/*
 * Jordyn Rylander
 * CSD-420
 * Module 1.3 Programming Assignment
 * August 16, 2026
 *
 * a program that displays four randomly selected playing cards.
 * using JavaFX
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * displays four randomly selected card images in a JavaFX window.
 *
 * @author Jordyn Rylander
 */
public class RylanderCardDisplay extends Application {

    private final List<String> cardFiles = new ArrayList<>();
    private final List<ImageView> cardViews = new ArrayList<>();
    private List<String> currentCards = new ArrayList<>();

    /**
     * Creates the application window and displays the first four cards.
     *
     * @param primaryStage the main application window
     */
    @Override
    public void start(Stage primaryStage) {
        buildDeck();

        Label titleLabel = new Label("Pick four Random cards!");
        titleLabel.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;"
        );

        HBox cardBox = new HBox(15);
        cardBox.setAlignment(Pos.CENTER);

        // Create four ImageView objects for displaying the cards.
        for (int i = 0; i < 4; i++) {
            ImageView cardView = new ImageView();
            cardView.setFitWidth(130);
            cardView.setPreserveRatio(true);

            cardViews.add(cardView);
            cardBox.getChildren().add(cardView);
        }

        Button refreshButton = new Button("Deal another set");
        refreshButton.setStyle(
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-color: #d4af37;" +
            "-fx-text-fill: #1b1b1b;"
        );

        // lambda expression that displays new cards when the button is clicked.
        refreshButton.setOnAction(event -> displayRandomCards());

        VBox root = new VBox(20, titleLabel, cardBox, refreshButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #14532d;");

        // display the first four cards when the program starts.
        displayRandomCards();

        Scene scene = new Scene(root, 650, 400);

        primaryStage.setTitle("Jordyn's Card Table");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * builds a list containing 1.png through 52.png.
     */
    private void buildDeck() {
        for (int cardNumber = 1; cardNumber <= 52; cardNumber++) {
            cardFiles.add(cardNumber + ".png");
        }
    }

    /**
     * randomly selects and displays four cards and the cards currently
     * displayed are removed from the choices before selecting new ones.
     */
    private void displayRandomCards() {
        List<String> availableCards = new ArrayList<>(cardFiles);

        // prevent the currently displayed cards from immediately reappearing.
        availableCards.removeAll(currentCards);

        // randomize the order of the remaining cards.
        Collections.shuffle(availableCards);

        // select the first four cards from the shuffled list.
        currentCards = new ArrayList<>(availableCards.subList(0, 4));

        // Load the four selected images from the cards folder.
        for (int i = 0; i < cardViews.size(); i++) {
            File cardFile = new File("cards", currentCards.get(i));
            Image cardImage = new Image(cardFile.toURI().toString());
            cardViews.get(i).setImage(cardImage);
        }
    }

    /**
     * launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}