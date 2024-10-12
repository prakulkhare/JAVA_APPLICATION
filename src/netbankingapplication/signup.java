package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class signup extends JFrame implements ActionListener {
    JTextField usernameField, emailField, phoneField;
    JPasswordField passwordField;
    JButton signUpBtn, backBtn;

    signup() {
        setTitle("Sign Up - Swiss Bank");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Create Account");
        heading.setBounds(450, 50, 300, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(300, 150, 200, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(500, 150, 200, 30);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(300, 200, 200, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(500, 200, 200, 30);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(300, 250, 200, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(500, 250, 200, 30);
        add(phoneField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(300, 300, 200, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(500, 300, 200, 30);
        add(passwordField);

        signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(450, 400, 200, 50);
        signUpBtn.addActionListener(this);
        signUpBtn.setBackground(Color.BLACK);
        signUpBtn.setForeground(Color.WHITE);
        add(signUpBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(450, 470, 200, 50);
        backBtn.addActionListener(this);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        add(backBtn);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signUpBtn) {
            try {
                String username = usernameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String password = new String(passwordField.getPassword()); 

                
                if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Account not created. Please fill in all details.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                String accountNumber = generateRandomAccountNumber();

               
                double initialBalance = 2000.0;

                Conn c = new Conn();

           
                String userQuery = "INSERT INTO users (username, email, phone, password) VALUES (?, ?, ?, ?)";
                PreparedStatement psUser = c.c.prepareStatement(userQuery);
                psUser.setString(1, username);
                psUser.setString(2, email);
                psUser.setString(3, phone);
                psUser.setString(4, password);
                psUser.executeUpdate();

                String accountQuery = "INSERT INTO accounts (username, account_number, balance) VALUES (?, ?, ?)";
                PreparedStatement psAccount = c.c.prepareStatement(accountQuery);
                psAccount.setString(1, username);
                psAccount.setString(2, accountNumber);
                psAccount.setDouble(3, initialBalance);
                psAccount.executeUpdate();

                String transactionQuery = "INSERT INTO transactions (account_number, amount, type,username) VALUES (?, ?, 'credit',?)";
                PreparedStatement psTransaction = c.c.prepareStatement(transactionQuery);
                psTransaction.setString(1, accountNumber);
                psTransaction.setDouble(2, initialBalance);
                psTransaction.setString(3, username);
                
                psTransaction.executeUpdate();
                JOptionPane.showMessageDialog(null, "Account Created Successfully! Your account number is: " + accountNumber);
                setVisible(false);
                new login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new login(); 
        }
    }

    private String generateRandomAccountNumber() {
        Random random = new Random();
        
        int accountNumber = 10000000 + random.nextInt(90000000);
        return String.valueOf(accountNumber);
    }

    public static void main(String[] args) {
        new signup();
    }
}
