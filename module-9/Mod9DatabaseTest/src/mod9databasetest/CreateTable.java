/*
 * Jordyn Rylander
 * CSD-420
 * Module 9.2
 * 9/27/2026
 *
 * A program that connects to the databasedb database and creates the address33 table used by the programs provided. 
 */

package mod9databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Creates a table to test the Java connection to MySQL.
 *
 * @author Jordyn Rylander
 */
public class CreateTable {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/databasedb";
        String user = "student1";
        String password = "pass";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS address33");

            statement.executeUpdate(
                    "CREATE TABLE address33 (" +
                    "ID INT PRIMARY KEY, " +
                    "FIRSTNAME VARCHAR(30), " +
                    "LASTNAME VARCHAR(30), " +
                    "STREET VARCHAR(50), " +
                    "CITY VARCHAR(30), " +
                    "STATE VARCHAR(20), " +
                    "ZIP VARCHAR(10))"
            );

            System.out.println("Database connection successful.");
            System.out.println("The address33 table was created successfully.");

            statement.close();
            connection.close();

        } catch (Exception error) {
            System.out.println("The table could not be created.");
            System.out.println(error.getMessage());
        }
    }
}