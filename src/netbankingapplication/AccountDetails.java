package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AccountDetails extends JFrame {

    JButton backBtn;
    static String username; 

    AccountDetails(String username) {
        setTitle("Account Details - Swiss Bank");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Account Details");
        heading.setBounds(450, 50, 300, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        try {
            Conn c = new Conn();
            
            String query = "SELECT account_number, balance FROM accounts WHERE username = '" + username + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                String accountNumber = rs.getString("account_number");
                String balance = rs.getString("balance");

                JLabel accountNumberLabel = new JLabel("Account Number: " + accountNumber);
                accountNumberLabel.setBounds(400, 150, 400, 50);
                accountNumberLabel.setFont(new Font("serif", Font.PLAIN, 20));
                add(accountNumberLabel);

                JLabel balanceLabel = new JLabel("Balance: $" + balance);
                balanceLabel.setBounds(400, 200, 400, 50);
                balanceLabel.setFont(new Font("serif", Font.PLAIN, 20));
                add(balanceLabel);
            } else {
                JLabel errorLabel = new JLabel("No account details found.");
                errorLabel.setBounds(400, 150, 400, 50);
                errorLabel.setForeground(Color.RED);
                errorLabel.setFont(new Font("serif", Font.PLAIN, 20));
                add(errorLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        backBtn = new JButton("Back");
        backBtn.setBounds(450, 350, 200, 50);
        backBtn.addActionListener(e -> {
            setVisible(false);
                new Dashboard(username);
        });
        add(backBtn);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }


    public static void setUsername(String user) {
        username = user;
    }

    public static void main(String[] args) {
        new AccountDetails("Guest");
    }
}
