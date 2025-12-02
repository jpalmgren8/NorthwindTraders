package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    private static Connection connection = null;

    public static void main(String[] args) {

        boolean menuSelect = true;

        while (menuSelect) {

            System.out.println("What do you want to do?");
        System.out.println("1) Display all products");
        System.out.println("2) Display all customers");
        System.out.println("3) Display all categories");
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
            case 3:
                categoryQueries();
                break;
            case 0:
                menuSelect = false;
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }

    public static void customerQueries() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT * FROM Customers ORDER BY Country";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery();) {

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
        }
    }

    public static void productQueries() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT * FROM Products";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                System.out.println("Product ID: " + resultSet.getInt("ProductID"));
                System.out.println("Name: " + resultSet.getString("ProductName"));
                System.out.println("Price: " + resultSet.getFloat("UnitPrice"));
                System.out.println("Stock: " + resultSet.getInt("UnitsInStock"));
                System.out.println("------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void categoryQueries() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT * FROM Categories";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                System.out.println("Category ID: " + resultSet.getInt("CategoryID"));
                System.out.println("Name: " + resultSet.getString("CategoryName"));
                System.out.println("------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
