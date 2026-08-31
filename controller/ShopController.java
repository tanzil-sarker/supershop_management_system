package controller;

import gui.ShopGUI;               
import database.DatabaseManager;  
import model.Transaction;          
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class ShopController implements ActionListener, MouseListener 
{
    private ShopGUI gui;
    private DatabaseManager db;
    private final String DEFAULT_PASSWORD = "12345";

    public ShopController(ShopGUI gui, DatabaseManager db) 
    {
        this.gui = gui;
        this.db = db;

        // Register Action Listeners
        gui.addButton.addActionListener(this);
        gui.clearButton.addActionListener(this);
        gui.exitButton.addActionListener(this);
        gui.showButton.addActionListener(this);
        gui.categoryCombo.addActionListener(this);
        for (JButton btn : gui.managementButtons) 
        {
            btn.addActionListener(this);
        }

        // Register Mouse Listeners
        gui.addButton.addMouseListener(this);
        gui.clearButton.addMouseListener(this);
        gui.exitButton.addMouseListener(this);
        gui.showButton.addMouseListener(this);
        gui.titleLabel.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == gui.categoryCombo) 
        {
            String category = (String) gui.categoryCombo.getSelectedItem();
            gui.productCombo.removeAllItems();
            gui.productCombo.addItem("");

            if (category.equals("Grocery")) 
            {
                gui.productCombo.addItem("Rice - 80 Tk");
                gui.productCombo.addItem("Bread - 60 Tk");
                gui.productCombo.addItem("Oil - 180 Tk");
            } 
            else if (category.equals("Beverage")) 
            {
                gui.productCombo.addItem("Milk - 100 Tk");
                gui.productCombo.addItem("Juice - 120 Tk");
            } 
            else if (category.equals("Personal Care")) 
            {
                gui.productCombo.addItem("Soap - 70 Tk");
                gui.productCombo.addItem("Shampoo - 250 Tk");
            } 
            else if (category.equals("Household")) 
            {
                gui.productCombo.addItem("Detergent - 160 Tk");
                gui.productCombo.addItem("Tissue - 90 Tk");
            }
        } 
        else if (ae.getSource() == gui.exitButton) 
        {
            System.exit(0);
        } 
        else if (ae.getSource() == gui.showButton) 
        {
            if (gui.showButton.getText().equals("Show")) 
            {
                gui.passwordField.setEchoChar((char) 0);
                gui.showButton.setText("Hide");
            } 
            else 
            {
                gui.passwordField.setEchoChar('*');
                gui.showButton.setText("Show");
            }
        } 
        else if (ae.getSource() == gui.clearButton) 
        {
            clearFields();
            JOptionPane.showMessageDialog(gui, "All information has been cleared.");
        } 
        else if (ae.getSource() == gui.insertButton) 
        {
            if (!isPasswordCorrect()) return;
            String[] data = collectEntityData();
            if (data != null) 
            {
                Transaction t = new Transaction(data[0], data[1], data[2], data[3], data[4]);
                db.insertInfo(t);
                JOptionPane.showMessageDialog(gui, "Information inserted into userdata.txt");
            }
        } 
        else if (ae.getSource() == gui.updateButton) 
        {
            if (!isPasswordCorrect()) return;
            String[] data = collectEntityData();
            if (data != null) 
            {
                String searchCustomer = gui.searchField.getText().trim();
                if (searchCustomer.isEmpty()) 
                {
                    JOptionPane.showMessageDialog(gui, "Enter a customer name in the search bar.");
                    return;
                }
                Transaction t = new Transaction(data[0], data[1], data[2], data[3], data[4]);
                if (db.updateInfo(searchCustomer, t))
                    JOptionPane.showMessageDialog(gui, "Information updated successfully.");
                else
                    JOptionPane.showMessageDialog(gui, "Customer was not found.");
            }
        } 
        else if (ae.getSource() == gui.getButton) 
        {
            if (!isPasswordCorrect()) return;
            String customer = gui.searchField.getText().trim();
            if (customer.isEmpty())
                JOptionPane.showMessageDialog(gui, "Enter a customer name in the search bar.");
            else 
            {
                String[] data = db.getInfo(customer);
                if (data == null)
                    JOptionPane.showMessageDialog(gui, "Customer was not found.");
                else 
                {
                    gui.customerField.setText(data[0]);
                    gui.categoryCombo.setSelectedItem(data[1]);
                    gui.productCombo.setSelectedItem(data[2]);
                    gui.quantityField.setText(data[3]);
                    if (data[4].equals("Cash")) gui.cashButton.setSelected(true);
                    else if (data[4].equals("Card")) gui.cardButton.setSelected(true);
                    gui.receiptArea.setText("Stored customer information loaded.\n\n" +
                                        "Customer : " + data[0] + "\n" +
                                        "Category : " + data[1] + "\n" +
                                        "Product  : " + data[2] + "\n" +
                                        "Quantity : " + data[3] + "\n" +
                                        "Payment  : " + data[4]);
                }
            }
        } 
        else if (ae.getSource() == gui.deleteButton) 
        {
            if (!isPasswordCorrect()) return;
            String customer = gui.searchField.getText().trim();
            if (customer.isEmpty())
                JOptionPane.showMessageDialog(gui, "Enter a customer name in the search bar.");
            else 
            {
                if (db.deleteInfo(customer)) 
                {
                    clearFields();
                    JOptionPane.showMessageDialog(gui, "Information deleted successfully.");
                } 
                else
                    JOptionPane.showMessageDialog(gui, "Customer was not found.");
            }
        } 
        else if (ae.getSource() == gui.addButton) 
        {
            if (!isPasswordCorrect()) return;
            generateBill();
        }
    }

    private boolean isPasswordCorrect() 
    {
        String password = new String(gui.passwordField.getPassword());
        if (!password.equals(DEFAULT_PASSWORD)) 
        {
            JOptionPane.showMessageDialog(
                gui,
                "Wrong Password",
                "Access Denied",
                JOptionPane.ERROR_MESSAGE
            );
            gui.statusLabel.setText("System Status: Wrong Password");
            gui.passwordField.setText("");
            gui.passwordField.requestFocus();
            return false;
        }
        return true;
    }

    private String[] collectEntityData() 
    {
        String customer = gui.customerField.getText().trim();
        String category = (String) gui.categoryCombo.getSelectedItem();
        String product = (String) gui.productCombo.getSelectedItem();
        String quantity = gui.quantityField.getText().trim();
        String payment = "";
        if (gui.cashButton.isSelected()) payment = "Cash";
        else if (gui.cardButton.isSelected()) payment = "Card";

        if (customer.isEmpty() || category == null || category.isEmpty() ||
            product == null || product.isEmpty() || quantity.isEmpty() || payment.isEmpty()) 
        {
            JOptionPane.showMessageDialog(gui, "Fill customer, category, product, quantity and payment.");
            return null;
        }
        try 
        {
            if (Integer.parseInt(quantity) <= 0) throw new NumberFormatException();
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(gui, "Quantity must be a positive integer.");
            return null;
        }
        return new String[]{customer, category, product, quantity, payment};
    }

    private void generateBill() 
    {
        String[] data = collectEntityData();
        if (data == null) return;

        String customer = data[0], category = data[1], product = data[2], payment = data[4];
        int quantity = Integer.parseInt(data[3]);
        int unitPrice;
        if (product.equals("Rice - 80 Tk")) unitPrice = 80;
        else if (product.equals("Milk - 100 Tk")) unitPrice = 100;
        else if (product.equals("Bread - 60 Tk")) unitPrice = 60;
        else if (product.equals("Oil - 180 Tk")) unitPrice = 180;
        else if (product.equals("Soap - 70 Tk")) unitPrice = 70;
        else if (product.equals("Juice - 120 Tk")) unitPrice = 120;
        else if (product.equals("Shampoo - 250 Tk")) unitPrice = 250;
        else if (product.equals("Detergent - 160 Tk")) unitPrice = 160;
        else unitPrice = 90;

        String services = "";
        int serviceCharge = 0;
        if (gui.bagBox.isSelected()) { services += "Shopping Bag - 10 Tk\n"; serviceCharge += 10; }
        if (gui.deliveryBox.isSelected()) { services += "Home Delivery - 100 Tk\n"; serviceCharge += 100; }
        if (gui.membershipBox.isSelected()) { services += "Membership - 200 Tk\n"; serviceCharge += 200; }
        if (services.isEmpty()) services = "No extra service\n";

        int productTotal = unitPrice * quantity;
        int grandTotal = productTotal + serviceCharge;
        gui.receiptArea.setText("========================================\n" +
            "          SUPERSHOP RECEIPT\n" +
            "========================================\n" +
            "Customer Name : " + customer + "\n" +
            "Category      : " + category + "\n" +
            "Product       : " + product + "\n" +
            "Quantity      : " + quantity + "\n" +
            "Unit Price    : " + unitPrice + " Tk\n" +
            "Payment       : " + payment + "\n" +
            "----------------------------------------\n" +
            "Extra Services:\n" + services +
            "----------------------------------------\n" +
            "Product Total : " + productTotal + " Tk\n" +
            "Service Charge: " + serviceCharge + " Tk\n" +
            "Grand Total   : " + grandTotal + " Tk\n" +
            "========================================\n" +
            "        Thank you for shopping!\n");
        gui.statusLabel.setText("System Status: Bill Generated Successfully");
        JOptionPane.showMessageDialog(gui, "Bill generated successfully!");
    }

    private void clearFields() 
    {
        gui.customerField.setText("");
        gui.searchField.setText("");
        gui.passwordField.setText("");
        gui.quantityField.setText("");
        gui.categoryCombo.setSelectedIndex(0);
        gui.paymentGroup.clearSelection();
        gui.bagBox.setSelected(false);
        gui.deliveryBox.setSelected(false);
        gui.membershipBox.setSelected(false);
        gui.receiptArea.setText("\n Please enter the sale information.");
        gui.statusLabel.setText("System Status: Information Cleared");
    }

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        if (me.getSource() == gui.titleLabel)
            JOptionPane.showMessageDialog(gui, "Welcome to SuperShop Management System");
    }
    @Override
    public void mousePressed(MouseEvent me) 
    {
        if (me.getSource() == gui.addButton) gui.addButton.setText("Processing...");
    }
    @Override
    public void mouseReleased(MouseEvent me) 
    {
        if (me.getSource() == gui.addButton) gui.addButton.setText("Generate Bill");
    }
    @Override
    public void mouseEntered(MouseEvent me) 
    {
        if (me.getSource() == gui.addButton) gui.addButton.setBackground(new Color(20, 90, 80));
        else if (me.getSource() == gui.clearButton) gui.clearButton.setBackground(new Color(135, 95, 20));
        else if (me.getSource() == gui.exitButton) gui.exitButton.setBackground(new Color(125, 35, 40));
        else if (me.getSource() == gui.showButton) gui.showButton.setBackground(new Color(135, 95, 20));
    }
    @Override
    public void mouseExited(MouseEvent me) 
    {
        if (me.getSource() == gui.addButton) gui.addButton.setBackground(gui.buttonColor);
        else if (me.getSource() == gui.clearButton) gui.clearButton.setBackground(new Color(175, 130, 35));
        else if (me.getSource() == gui.exitButton) gui.exitButton.setBackground(new Color(170, 55, 60));
        else if (me.getSource() == gui.showButton) gui.showButton.setBackground(new Color(175, 130, 35));
    }
}
