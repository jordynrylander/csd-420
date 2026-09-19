/*
 * Jordyn Rylander
 * CSD-420
 * Module 7.2
 * 9/19/2026
 *
 * A program that demonstrates styling circles in JavaFX.
 */


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * demonstrates styling 4 circles with another css file
 * @author Jordyn Rylander
 */

public class RylanderCircleStyles extends Application {

    /**
     * creates a scene with 4 circles and applies a css file to style them
     *
     * @param primaryStage the main stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        Circle circleOne = new Circle(40);
        Circle circleTwo = new Circle(40);
        Circle circleThree = new Circle(40);
        Circle circleFour = new Circle(40);

        // apply the white-fill and black-stroke CSS class
        circleOne.getStyleClass().add("plaincircle");
        circleTwo.getStyleClass().add("plaincircle");

        // apply the red and green CSS IDs
        circleThree.setId("redcircle");
        circleFour.setId("greencircle");

        // place the first circle inside a pane with a black border
        StackPane borderedPane = new StackPane(circleOne);
        borderedPane.getStyleClass().add("border");
        borderedPane.setPrefSize(100, 180);

        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                borderedPane, circleTwo, circleThree, circleFour);

        Scene scene = new Scene(root, 450, 220);

        // load the external CSS style sheet
        String cssFile = getClass().getResource("mystyle.css").toExternalForm();
        scene.getStylesheets().add(cssFile);

        runTests(circleOne, circleTwo, circleThree, circleFour, borderedPane);

        primaryStage.setTitle("Jordyn's CSS Circle Styles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * tests that the CSS class and IDs were assigned correctly
     *
     */
    private void runTests(Circle circleOne, Circle circleTwo,
            Circle circleThree, Circle circleFour,
            StackPane borderedPane) {

        boolean classTest =
                circleOne.getStyleClass().contains("plaincircle")
                && circleTwo.getStyleClass().contains("plaincircle");

        boolean idTest =
                "redcircle".equals(circleThree.getId())
                && "greencircle".equals(circleFour.getId());

        boolean borderTest =
                borderedPane.getStyleClass().contains("border");

        System.out.println("CSS class test: "
                + (classTest ? "PASSED" : "FAILED"));
        System.out.println("CSS ID test: "
                + (idTest ? "PASSED" : "FAILED"));
        System.out.println("Border class test: "
                + (borderTest ? "PASSED" : "FAILED"));
        System.out.println("Overall test: "
                + (classTest && idTest && borderTest
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
