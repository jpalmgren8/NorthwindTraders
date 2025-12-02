package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean menuSelect = true;

        while (menuSelect) {

            System.out.println("What do you want to do?");
        System.out.println("1) Display all products");
        System.out.println("2) Display all customers");
        System.out.println("0) Exit");
        System.out.println("Select an option: ");

        int userInput = sc.nextInt();
        sc.nextLine();


        switch (userInput) {
            case 1:
                productQueries();
                break;
            case 2:
                customerQueries();
                break;
            case 0:
                menuSelect = false;
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }

    public static void customerQueries() {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        String query = "SELECT * FROM Customers ORDER BY Country";

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup");

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Contact Name: " + resultSet.getString("ContactName"));
                System.out.println("Company: " + resultSet.getString("CompanyName"));
                System.out.println("City: " + resultSet.getString("City"));
                System.out.println("Country: " + resultSet.getString("Country"));
                System.out.println("Phone #: " + resultSet.getString("Phone"));
                System.out.println("------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void productQueries() {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        String query = "SELECT * FROM Products";

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup");

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Product ID: " + resultSet.getInt("ProductID"));
                System.out.println("Name: " + resultSet.getString("ProductName"));
                System.out.println("Price: " + resultSet.getFloat("UnitPrice"));
                System.out.println("Stock: " + resultSet.getInt("UnitsInStock"));
                System.out.println("------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
