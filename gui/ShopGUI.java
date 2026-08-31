package gui;

import javax.swing.*;
import java.awt.*;

public class ShopGUI extends JFrame 
{
    public JPanel panel;
    public JLabel titleLabel, productLabel, quantityLabel;
    public JLabel categoryLabel, paymentLabel, serviceLabel;
    public JLabel customerLabel, passwordLabel, receiptLabel;
    public JLabel statusLabel, searchLabel;
    public Font titleFont, normalFont, smallFont, creditfont;
    public Color backgroundColor, headerColor;
    public Color buttonColor, receiptColor;
    public JTextField customerField, quantityField, searchField;
    public JPasswordField passwordField;
    public JButton addButton, clearButton;
    public JButton showButton, exitButton;
    public JButton insertButton, updateButton, getButton, deleteButton;
    public JRadioButton cashButton, cardButton;
    public ButtonGroup paymentGroup;
    public JCheckBox bagBox, deliveryBox, membershipBox;
    public JComboBox<String> productCombo;
    public JComboBox<String> categoryCombo;
    public JTextArea receiptArea;
    public JButton[] managementButtons;

    public ShopGUI() 
    {
        super("SuperShop Management System");
        super.setBounds(250, 80, 1100, 750);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);

        titleFont = new Font("Agency FB", Font.BOLD, 38);
        normalFont = new Font("Cambria", Font.BOLD, 18);
        smallFont = new Font("Cambria", Font.PLAIN, 16);
        creditfont = new Font("Franklin Gothic", Font.BOLD, 16);

        backgroundColor = new Color(242, 244, 248);
        headerColor = new Color(25, 42, 70);
        buttonColor = new Color(30, 120, 105);
        receiptColor = new Color(255, 253, 245);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(backgroundColor);

        titleLabel = new JLabel("SUPERSHOP MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setBounds(0, 0, 1100, 80);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(235, 190, 80));
        titleLabel.setBackground(headerColor);
        titleLabel.setOpaque(true);
        panel.add(titleLabel);

        customerLabel = new JLabel("Customer Name:");
        customerLabel.setBounds(50, 110, 180, 40);
        customerLabel.setFont(normalFont);
        panel.add(customerLabel);

        customerField = new JTextField();
        customerField.setBounds(240, 110, 270, 40);
        customerField.setFont(normalFont);
        panel.add(customerField);

        passwordLabel = new JLabel("Employee Password:");
        passwordLabel.setBounds(50, 165, 180, 40);
        passwordLabel.setFont(normalFont);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(240, 165, 190, 40);
        passwordField.setFont(normalFont);
        passwordField.setEchoChar('*');
        panel.add(passwordField);

        showButton = new JButton("Show");
        showButton.setBounds(440, 165, 72, 40);
        showButton.setFont(smallFont);
        showButton.setForeground(Color.WHITE);
        showButton.setBackground(new Color(175, 130, 35));
        showButton.setFocusPainted(false);
        panel.add(showButton);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(50, 220, 180, 40);
        categoryLabel.setFont(normalFont);
        panel.add(categoryLabel);

        String[] categories = {"", "Grocery", "Beverage", "Personal Care", "Household"};
        categoryCombo = new JComboBox<>(categories);
        categoryCombo.setBounds(240, 220, 270, 40);
        categoryCombo.setFont(normalFont);
        panel.add(categoryCombo);

        productLabel = new JLabel("Product:");
        productLabel.setBounds(50, 275, 180, 40);
        productLabel.setFont(normalFont);
        panel.add(productLabel);

        String[] products = {""};
        productCombo = new JComboBox<>(products);
        productCombo.setBounds(240, 275, 270, 40);
        productCombo.setFont(normalFont);
        panel.add(productCombo);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(50, 330, 180, 40);
        quantityLabel.setFont(normalFont);
        panel.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(240, 330, 270, 40);
        quantityField.setFont(normalFont);
        panel.add(quantityField);

        paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setBounds(50, 385, 180, 40);
        paymentLabel.setFont(normalFont);
        panel.add(paymentLabel);

        cashButton = new JRadioButton("Cash");
        cashButton.setBounds(240, 385, 110, 40);
        cashButton.setFont(normalFont);
        cashButton.setBackground(backgroundColor);
        panel.add(cashButton);

        cardButton = new JRadioButton("Card");
        cardButton.setBounds(370, 385, 110, 40);
        cardButton.setFont(normalFont);
        cardButton.setBackground(backgroundColor);
        panel.add(cardButton);

        paymentGroup = new ButtonGroup();
        paymentGroup.add(cashButton);
        paymentGroup.add(cardButton);

        serviceLabel = new JLabel("Extra Services:");
        serviceLabel.setBounds(50, 440, 180, 40);
        serviceLabel.setFont(normalFont);
        panel.add(serviceLabel);

        bagBox = new JCheckBox("Shopping Bag");
        bagBox.setBounds(240, 440, 150, 40);
        bagBox.setFont(smallFont);
        bagBox.setBackground(backgroundColor);
        panel.add(bagBox);

        deliveryBox = new JCheckBox("Home Delivery");
        deliveryBox.setBounds(390, 440, 160, 40);
        deliveryBox.setFont(smallFont);
        deliveryBox.setBackground(backgroundColor);
        panel.add(deliveryBox);

        membershipBox = new JCheckBox("Membership");
        membershipBox.setBounds(240, 485, 150, 40);
        membershipBox.setFont(smallFont);
        membershipBox.setBackground(backgroundColor);
        panel.add(membershipBox);

        addButton = new JButton("Generate Bill");
        addButton.setBounds(50, 555, 200, 50);
        addButton.setFont(normalFont);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(buttonColor);
        addButton.setFocusPainted(false);
        panel.add(addButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(270, 555, 120, 50);
        clearButton.setFont(normalFont);
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(new Color(175, 130, 35));
        clearButton.setFocusPainted(false);
        panel.add(clearButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(410, 555, 100, 50);
        exitButton.setFont(normalFont);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(170, 55, 60));
        exitButton.setFocusPainted(false);
        panel.add(exitButton);

        receiptLabel = new JLabel("PURCHASE INFO / RECEIPT", SwingConstants.CENTER);
        receiptLabel.setBounds(600, 210, 400, 45);
        receiptLabel.setFont(normalFont);
        receiptLabel.setForeground(new Color(235, 190, 80));
        receiptLabel.setBackground(headerColor);
        receiptLabel.setOpaque(true);
        panel.add(receiptLabel);

        receiptArea = new JTextArea();
        receiptArea.setBounds(600, 255, 400, 360);
        receiptArea.setFont(smallFont);
        receiptArea.setBackground(receiptColor);
        receiptArea.setEditable(false);
        receiptArea.setBorder(BorderFactory.createLineBorder(new Color(205, 170, 80), 2));
        receiptArea.setText("\n Please enter the sale information.");
        panel.add(receiptArea);

        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        getButton = new JButton("Get");
        deleteButton = new JButton("Delete");
        insertButton.setBounds(600, 155, 90, 42);
        updateButton.setBounds(700, 155, 90, 42);
        getButton.setBounds(800, 155, 90, 42);
        deleteButton.setBounds(900, 155, 100, 42);
        
        managementButtons = new JButton[]{insertButton, updateButton, getButton, deleteButton};
        for (JButton managementButton : managementButtons) 
        {
            managementButton.setFont(smallFont);
            managementButton.setForeground(Color.WHITE);
            managementButton.setBackground(headerColor);
            managementButton.setFocusPainted(false);
            panel.add(managementButton);
        }
        deleteButton.setBackground(new Color(170, 55, 60));

        searchLabel = new JLabel("Search Customer:");
        searchLabel.setBounds(600, 105, 160, 35);
        searchLabel.setFont(smallFont);
        panel.add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(760, 105, 240, 35);
        searchField.setFont(smallFont);
        panel.add(searchField);

        statusLabel = new JLabel(
            "OOP1 Project | Group 5 | Tanzil Sarker [26-64169-1] & Shdadia Islam Humaira [25-63465-1]",
            SwingConstants.CENTER
        );
        statusLabel.setBounds(0, 650, 1100, 60);
        statusLabel.setFont(creditfont);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBackground(headerColor);
        statusLabel.setOpaque(true);
        panel.add(statusLabel);

        super.add(panel);
    }
}
