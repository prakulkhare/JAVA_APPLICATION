package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {

    String username;
    JButton accountDetails, fundTransfer, transactionHistory, billPayments, profileSettings, logout, backBtn;


    Dashboard(String username) {
        this.username = username;

        setTitle("Dashboard - Swiss Bank");
        setLayout(null);

        JLabel heading = new JLabel("Welcome to Swiss Bank "+this.username);
        heading.setBounds(400, 50, 400, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        heading.setForeground(Color.BLACK);
        add(heading);

        accountDetails = new JButton("Account Details");
        accountDetails.setBounds(300, 150, 200, 50);
        accountDetails.setBackground(Color.BLACK);
        accountDetails.setForeground(Color.WHITE);
        accountDetails.addActionListener(this);
        add(accountDetails);

        fundTransfer = new JButton("Fund Transfer");
        fundTransfer.setBounds(600, 150, 200, 50);
        fundTransfer.setBackground(Color.BLACK);
        fundTransfer.setForeground(Color.WHITE);
        fundTransfer.addActionListener(this);
        add(fundTransfer);

        transactionHistory = new JButton("Transaction History");
        transactionHistory.setBounds(300, 250, 200, 50);
        transactionHistory.setBackground(Color.BLACK);
        transactionHistory.setForeground(Color.WHITE);
        transactionHistory.addActionListener(this);
        add(transactionHistory);

        billPayments = new JButton("Bill Payments");
        billPayments.setBounds(600, 250, 200, 50);
        billPayments.setBackground(Color.BLACK);
        billPayments.setForeground(Color.WHITE);
        billPayments.addActionListener(this);
        add(billPayments);

        profileSettings = new JButton("Profile Settings");
        profileSettings.setBounds(300, 350, 200, 50);
        profileSettings.setBackground(Color.BLACK);
        profileSettings.setForeground(Color.WHITE);
        profileSettings.addActionListener(this);
        add(profileSettings);

        logout = new JButton("Logout");
        logout.setBounds(600, 350, 200, 50);
        logout.setBackground(Color.BLACK);
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);

        backBtn = new JButton("Back");
        backBtn.setBounds(450, 450, 200, 50);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == accountDetails) {
            setVisible(false);
            new AccountDetails(username);
        } else if (ae.getSource() == fundTransfer) {
            setVisible(false);
            new FundTransfer(username);
        } else if (ae.getSource() == transactionHistory) {
            setVisible(false);
            new TransactionHistory(username);
        } else if (ae.getSource() == billPayments) {
            setVisible(false);
            new BillPayments(username);
        } else if (ae.getSource() == profileSettings) {
            setVisible(false);
            new ProfileSettings(username);
        } else if (ae.getSource() == logout) {
            setVisible(false);
            new frontpage();
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new frontpage();
        }
    }

    public static void main(String[] args) {
        new Dashboard("Guest");
    }
}

