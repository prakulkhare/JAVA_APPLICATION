package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class TransactionHistory extends JFrame {
    String username;
    JButton backBtn;

    TransactionHistory(String username) {
        this.username = username;
        setTitle("Transaction History - Swiss Bank");
        setLayout(null);

        JLabel heading = new JLabel("Transaction History");
        heading.setBounds(450, 50, 300, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JTextArea transactionHistory = new JTextArea();
        transactionHistory.setBounds(400, 150, 400, 300);
        add(transactionHistory);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM transactions WHERE username = '"+ username + "'"; // Replace '123456789' with the actual user's account number
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                String date = rs.getString("date");

                transactionHistory.append("Type: " + type + " | Amount: $" + amount + " | Date: " + date + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        backBtn = new JButton("Back");
        backBtn.setBounds(450, 450, 200, 50);
        backBtn.addActionListener(e -> {
            setVisible(false);
             new Dashboard(username);
        });
        add(backBtn);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TransactionHistory("Guest");
    }
}
