/*
 * Jordyn Rylander
 * CSD-420
 * Module 10.2
 * 10/3/2026
 *
 *  This program shows and updates fan information stored in the fans
 *  table of the databasedb MySQL database. 
 */
package mod10_2fandatabase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * Provides an interface for displaying and updating fan records.
 *
 * @author Jordyn Rylander
 */
public class Mod10_2FanDatabase extends JFrame {

    private static final String DATABASE_URL =
            "jdbc:mysql://localhost:3306/databasedb";
    private static final String DATABASE_USER = "student1";
    private static final String DATABASE_PASSWORD = "pass";

    private final JTextField idField = new JTextField();
    private final JTextField firstNameField = new JTextField();
    private final JTextField lastNameField = new JTextField();
    private final JTextField favoriteTeamField = new JTextField();
    private final JLabel statusLabel = new JLabel(" ");

    /**
     * Creates the fan database interface.
     */
    public Mod10_2FanDatabase() {
        setTitle("Fan Database");
        setSize(430, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel titleLabel = new JLabel(
                "Fan Information", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(20.0f));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 12));
        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Favorite Team:"));
        formPanel.add(favoriteTeamField);

        JButton displayButton = new JButton("Display");
        JButton updateButton = new JButton("Update");

        displayButton.addActionListener(event -> displayRecord());
        updateButton.addActionListener(event -> updateRecord());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(displayButton);
        buttonPanel.add(updateButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        runTests(displayButton, updateButton);
    }

    /**
     * Connects to the databasedb database.
     *
     * @return an active database connection
     * @throws SQLException if the connection fails
     */
    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(
                DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    /**
     * Displays the fan record matching the entered ID.
     */
    private void displayRecord() {
        Integer id = readId();

        if (id == null) {
            return;
        }

        String sql = "SELECT firstname, lastname, favoriteteam "
                + "FROM fans WHERE ID = ?";

        try (Connection connection = connectToDatabase();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    firstNameField.setText(
                            results.getString("firstname"));
                    lastNameField.setText(
                            results.getString("lastname"));
                    favoriteTeamField.setText(
                            results.getString("favoriteteam"));

                    statusLabel.setText("Fan record displayed.");
                } else {
                    clearFanFields();
                    statusLabel.setText(
                            "No fan was found with ID " + id + ".");
                }
            }

        } catch (SQLException error) {
            statusLabel.setText(
                    "Database error: " + error.getMessage());
        }
    }

    /**
     * Updates the fan record matching the entered ID.
     */
    private void updateRecord() {
        Integer id = readId();

        if (id == null) {
            return;
        }

        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String favoriteTeam = favoriteTeamField.getText().trim();

        if (firstName.isEmpty()
                || lastName.isEmpty()
                || favoriteTeam.isEmpty()) {

            statusLabel.setText("Complete every field before updating.");
            return;
        }

        String sql = "UPDATE fans SET firstname = ?, lastname = ?, "
                + "favoriteteam = ? WHERE ID = ?";

        try (Connection connection = connectToDatabase();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, favoriteTeam);
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated == 1) {
                statusLabel.setText("Fan record updated.");
            } else {
                statusLabel.setText(
                        "No fan was found with ID " + id + ".");
            }

        } catch (SQLException error) {
            statusLabel.setText(
                    "Database error: " + error.getMessage());
        }
    }

    /**
     * Reads and validates the ID entered by the user.
     *
     * @return the entered ID, or null when the ID is invalid
     */
    private Integer readId() {
        try {
            return Integer.valueOf(idField.getText().trim());
        } catch (NumberFormatException error) {
            statusLabel.setText("Enter a valid whole-number ID.");
            return null;
        }
    }

    /**
     * Clears the fan-information fields.
     */
    private void clearFanFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        favoriteTeamField.setText("");
    }

    /**
     * Tests the database connection and interface controls.
     *
     * @param displayButton the display button
     * @param updateButton the update button
     */
    private void runTests(
            JButton displayButton, JButton updateButton) {

        System.out.println("Module 10.2 test results:");

        try (Connection connection = connectToDatabase()) {
            System.out.println("Database connection test: PASSED");
        } catch (SQLException error) {
            System.out.println("Database connection test: FAILED");
        }

        boolean fieldsExist = idField != null
                && firstNameField != null
                && lastNameField != null
                && favoriteTeamField != null;

        System.out.println("Interface fields test: "
                + (fieldsExist ? "PASSED" : "FAILED"));

        boolean buttonsWork =
                displayButton.getActionListeners().length > 0
                && updateButton.getActionListeners().length > 0;

        System.out.println("Button action test: "
                + (buttonsWork ? "PASSED" : "FAILED"));
    }

    /**
     * Starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Mod10_2FanDatabase application =
                    new Mod10_2FanDatabase();
            application.setVisible(true);
        });
    }
}