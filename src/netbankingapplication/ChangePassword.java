package netbankingapplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChangePassword extends JFrame implements ActionListener {
    String username;
    JPasswordField currentPasswordField, newPasswordField, reenterNewPasswordField;
    JButton updatePasswordBtn, backBtn;

    ChangePassword(String username) {
        this.username = username;
        setTitle("Change Password - Swiss Bank");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Change Password");
        heading.setBounds(450, 50, 300, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel currentPasswordLabel = new JLabel("Current Password:");
        currentPasswordLabel.setBounds(400, 150, 200, 30);
        add(currentPasswordLabel);

        currentPasswordField = new JPasswordField();
        currentPasswordField.setBounds(600, 150, 200, 30);
        add(currentPasswordField);

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setBounds(400, 200, 200, 30);
        add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(600, 200, 200, 30);
        add(newPasswordField);

        JLabel reenterNewPasswordLabel = new JLabel("Re-enter New Password:");
        reenterNewPasswordLabel.setBounds(400, 250, 200, 30);
        add(reenterNewPasswordLabel);

        reenterNewPasswordField = new JPasswordField();
        reenterNewPasswordField.setBounds(600, 250, 200, 30);
        add(reenterNewPasswordField);

        updatePasswordBtn = new JButton("Update Password");
        updatePasswordBtn.setBounds(400, 350, 150, 50);
        updatePasswordBtn.addActionListener(this);
        updatePasswordBtn.setBackground(Color.BLACK);
        updatePasswordBtn.setForeground(Color.WHITE);
        add(updatePasswordBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(600, 350, 150, 50);
        backBtn.addActionListener(this);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        add(backBtn);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updatePasswordBtn) {
            try {
                String currentPassword = new String(currentPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String reenterNewPassword = new String(reenterNewPasswordField.getPassword());

               
                Conn c = new Conn();
                String query = "SELECT password FROM users WHERE username = '" + username + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    String currentPasswordInDB = rs.getString("password");

                   
                    if (!currentPassword.equals(currentPasswordInDB)) {
                        JOptionPane.showMessageDialog(null, "Current password is incorrect!");
                    } else if (newPassword.equals(currentPassword)) {
                        JOptionPane.showMessageDialog(null, "New password cannot be the same as the current password!");
                    } else if (!newPassword.equals(reenterNewPassword)) {
                        JOptionPane.showMessageDialog(null, "New passwords do not match!");
                    } else {
                       
                        query = "UPDATE users SET password = ? WHERE username = '" + username + "'";
                        PreparedStatement ps = c.c.prepareStatement(query);
                        ps.setString(1, newPassword);
                        ps.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Password updated successfully!");
                        setVisible(false);
                        new ProfileSettings(username);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new ProfileSettings(username);
        }
    }

    public static void main(String[] args) {
        new ChangePassword("Guest");
    }
}

