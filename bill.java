package org.example.Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bill {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/billing_system";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        // Establish connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            System.out.println("Connected to the database!");

            // Create a statement
            Statement statement = connection.createStatement();

            // Create table if not exists
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS invoices (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "customer_name VARCHAR(100) NOT NULL," +
                    "amount DOUBLE NOT NULL," +
                    "paid BOOLEAN NOT NULL)");

            // Insert sample data
            statement.executeUpdate("INSERT INTO invoices (customer_name, amount, paid) VALUES " +
                    "('Sukrit Basnet', 100.50, false)");
            statement.executeUpdate("INSERT INTO invoices (customer_name, amount, paid) VALUES " +
                    "('Shreejal Khanal', 200.75, true)");
            statement.executeUpdate("INSERT INTO invoices (customer_name, amount, paid) VALUES " +
                    "('Abi Bhattarai', 150.25, false)");

            // Retrieve data and print invoices
            List<Invoice> invoices = getAllInvoices(statement);
            for (Invoice invoice : invoices) {
                System.out.println(invoice);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Invoice> getAllInvoices(Statement statement) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM invoices");
        while (resultSet.next()) {
            String customerName = resultSet.getString("customer_name");
            double amount = resultSet.getDouble("amount");
            boolean paid = resultSet.getBoolean("paid");
            invoices.add(new Invoice(customerName, amount, paid));
        }
        return invoices;
    }

    // Inner class Invoice
    static class Invoice {
        private String customerName;
        private double amount;
        private boolean paid;

        public Invoice(String customerName, double amount, boolean paid) {
            this.customerName = customerName;
            this.amount = amount;
            this.paid = paid;
        }

        @Override
        public String toString() {
            return "Invoice{" +
                    "customerName='" + customerName + '\'' +
                    ", amount=" + amount +
                    ", paid=" + paid +
                    '}';
        }
    }
}
