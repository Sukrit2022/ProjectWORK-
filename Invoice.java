package org.example.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private int id;
    private String customerName;
    private double amount;
    private boolean paid;

    // Constructor
    public Invoice(int id, String customerName, double amount, boolean paid) {
        this.id = id;
        this.customerName = customerName;
        this.amount = amount;
        this.paid = paid;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    // Static method to retrieve all invoices
    public static List<Invoice> getAllInvoices(Statement statement) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM invoices");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String customerName = resultSet.getString("customer_name");
            double amount = resultSet.getDouble("amount");
            boolean paid = resultSet.getBoolean("paid");
            invoices.add(new Invoice(id, customerName, amount, paid));
        }
        return invoices;
    }
}
