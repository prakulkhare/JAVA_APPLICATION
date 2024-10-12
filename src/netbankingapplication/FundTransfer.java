package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class FundTransfer extends JFrame implements ActionListener {
    String username;
    JTextField amountField, accountField;
    JButton transferBtn, backBtn;

    FundTransfer(String username) {
        this.username = username;
        setTitle("Fund Transfer - Swiss Bank");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Fund Transfer");
        heading.setBounds(450, 50, 300, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel accountLabel = new JLabel("Beneficiary Account:");
        accountLabel.setBounds(300, 150, 200, 30);
        add(accountLabel);

        accountField = new JTextField();
        accountField.setBounds(500, 150, 200, 30);
        add(accountField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(300, 200, 200, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(500, 200, 200, 30);
        add(amountField);

        transferBtn = new JButton("Transfer");
        transferBtn.setBounds(450, 300, 200, 50);
        transferBtn.addActionListener(this);
        add(transferBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(450, 370, 200, 50);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == transferBtn) {
            try {
                String beneficiaryAccount = accountField.getText();
                String amountStr = amountField.getText();
                double amount = Double.parseDouble(amountStr); 

                Conn c = new Conn();
                
                String updateSenderQuery = "UPDATE accounts SET balance = balance - ? WHERE username = ?";
                PreparedStatement psSender = c.c.prepareStatement(updateSenderQuery);
                psSender.setDouble(1, amount);
                psSender.setString(2, username);
                psSender.executeUpdate();

                // Update beneficiary's account balance
                String updateBeneficiaryQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
                PreparedStatement psBeneficiary = c.c.prepareStatement(updateBeneficiaryQuery);
                psBeneficiary.setDouble(1, amount);
                psBeneficiary.setString(2, beneficiaryAccount);
                psBeneficiary.executeUpdate();

               
                String transactionQuery = "INSERT INTO transactions ( amount, type,username) VALUES ( ?, 'debit',?)";
                PreparedStatement psTransaction = c.c.prepareStatement(transactionQuery);
                psTransaction.setString(2, username); 
                psTransaction.setDouble(1, amount);
                psTransaction.executeUpdate();
                

                JOptionPane.showMessageDialog(null, "Amount Transferred Successfully!");
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
        new FundTransfer("Guest");
    }
}
