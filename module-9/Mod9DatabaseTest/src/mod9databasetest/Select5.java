/*
 * Jordyn Rylander
 * CSD-420
 * Module 9.2
 * 9/27/2026
 *
 * This program connects to the databasedb database, retrieves all
 * records from the address33 table provided, and displays the results.
 *
 */



package mod9databasetest;

import java.sql.*;
/**
 * Uses and displays records from the MySQL database.
 *
 * @author Jordyn Rylander
 */
public class Select5 {

    public static void main(String[] args) {

        try {
            Connection con;

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/databasedb?";

            con = DriverManager.getConnection(
                    url + "user=student1&password=pass");

            System.out.println(
                    "Connection established - now executing a select");

            Statement stmt = con.createStatement();

            ResultSet rs =
                    stmt.executeQuery("SELECT * FROM address33");

            System.out.println("Received Results:");

            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {

                for (int column = 1;
                        column <= columnCount;
                        column++) {

                    System.out.println(rs.getString(column));
                }

                System.out.println();
            }

            stmt.close();
            con.close();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}