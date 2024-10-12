package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, signupBtn;

    login() {
        setTitle("Login - Swiss Bank");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(40, 20, 100, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 20, 150, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 70, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 70, 150, 30);
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 120, 150, 30);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(this);
        add(loginBtn);

        signupBtn = new JButton("Sign Up");
        signupBtn.setBounds(150, 170, 150, 30);
        signupBtn.setBackground(Color.GRAY);
        signupBtn.setForeground(Color.WHITE);
        signupBtn.addActionListener(this);
        add(signupBtn);

        setSize(400, 300);
        setLocation(500, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginBtn) {
            try {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                Conn c = new Conn();
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password); 

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false);
                    new Dashboard(username);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == signupBtn) {
            setVisible(false);
            new signup();
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
    