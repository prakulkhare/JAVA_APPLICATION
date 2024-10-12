package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProfileSettings extends JFrame implements ActionListener {
    String username;

    JTextField emailField, phoneField;
    JButton changePasswordBtn, updateBtn, backBtn;

    ProfileSettings(String username) {
        this.username = username;
        setTitle("Profile Settings - Swiss Bank");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Profile Settings");
        heading.setBounds(450, 50, 300, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(400, 150, 200, 30);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(500, 150, 200, 30);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(400, 200, 200, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(500, 200, 200, 30);
        add(phoneField);

        try {
            Conn c = new Conn();
            String query = "SELECT email, phone FROM users WHERE username = '" + username + "'"; // Replace with the logged-in user
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                emailField.setText(rs.getString("email"));
                phoneField.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        changePasswordBtn = new JButton("Change Password");
        changePasswordBtn.setBounds(400, 300, 150, 50);
        changePasswordBtn.setBackground(Color.BLACK);
        changePasswordBtn.setForeground(Color.WHITE);
        changePasswordBtn.addActionListener(this);
        add(changePasswordBtn);

        updateBtn = new JButton("Update Profile");
        updateBtn.setBounds(600, 300, 150, 50);
        updateBtn.setBackground(Color.BLACK);
        updateBtn.setForeground(Color.WHITE);
        updateBtn.addActionListener(this);
        add(updateBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(450, 370, 200, 50);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            try {
                String email = emailField.getText();
                String phone = phoneField.getText();

                Conn c = new Conn();
                String query = "UPDATE users SET email = ?, phone = ? WHERE username = '" + username + "'"; // Replace with the logged-in user
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, email);
                ps.setString(2, phone);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Profile Updated Successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == changePasswordBtn) {
            setVisible(false);
            new ChangePassword(username); 
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new Dashboard(username); 
        }
    }

    public static void main(String[] args) {
        new ProfileSettings("Guest");
    }
}
