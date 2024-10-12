package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BillPayments extends JFrame implements ActionListener {
    String username;
    JTextField billerField, amountField;
    JButton payBtn, backBtn;

    BillPayments(String username) {
        this.username = username;
        setTitle("Bill Payments - Swiss Bank");
        setLayout(null);

        JLabel heading = new JLabel("Bill Payments");
        heading.setBounds(450, 50, 300, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel billerLabel = new JLabel("Biller Name:");
        billerLabel.setBounds(300, 150, 200, 30);
        add(billerLabel);

        billerField = new JTextField();
        billerField.setBounds(500, 150, 200, 30);
        add(billerField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(300, 200, 200, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(500, 200, 200, 30);
        add(amountField);

        payBtn = new JButton("Pay");
        payBtn.setBounds(450, 300, 200, 50);
        payBtn.addActionListener(this);
        add(payBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(450, 370, 200, 50);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == payBtn) {
            try {
                String amountStr = amountField.getText();
                double amount = Double.parseDouble(amountStr); 

                Conn c = new Conn();
                
                String updateBalanceQuery = "UPDATE accounts SET balance = balance - ? WHERE username = ?";
                PreparedStatement ps = c.c.prepareStatement(updateBalanceQuery);
                ps.setDouble(1, amount);
                ps.setString(2, username);
                ps.executeUpdate();
                
                String billsQuery = "INSERT INTO bills (username, amount, biller_name) VALUES (?, ?, ?)";
                PreparedStatement psbills = c.c.prepareStatement(billsQuery);
                psbills.setString(1, username); // Assuming username corresponds to account_number
                psbills.setDouble(2, amount);
                psbills.setString(3,billerField.getText());
                psbills.executeUpdate();
                String transactionQuery = "INSERT INTO transactions ( amount, type,username) VALUES ( ?, 'debit',?)";
                PreparedStatement psTransaction = c.c.prepareStatement(transactionQuery);
                psTransaction.setString(2, username); 
                psTransaction.setDouble(1, amount);
                
                psTransaction.executeUpdate();

                JOptionPane.showMessageDialog(null, "Bill Paid Successfully!");
                setVisible(false);
                new Dashboard(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new Dashboard(username);
        }
    }

    public static void main(String[] args) {
        new BillPayments("Guest");
    }
}
