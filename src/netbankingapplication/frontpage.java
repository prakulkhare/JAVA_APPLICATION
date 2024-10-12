package netbankingapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class frontpage extends JFrame implements ActionListener {

    JButton Login, signup;

    frontpage() {
        getContentPane().setBackground(Color.WHITE);
            setLayout(null);

        JLabel heading = new JLabel("SWISS BANK");
        heading.setBounds(420, 30, 1000, 60);
        heading.setFont(new Font("serif", Font.BOLD, 40));
        heading.setForeground(Color.BLACK);
        add(heading);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/front.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1170,650,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setSize(1170,650);
        add(image);

        Login = new JButton("LOGIN ");
        Login.setBounds(335, 400, 200, 70);
        Login.addActionListener(this);
        Login.setBackground(Color.BLACK);
        Login.setForeground(Color.WHITE);
        image.add(Login);

      
        signup = new JButton("SIGN UP");
        signup.setBounds(545, 400, 200, 70);
        signup.addActionListener(this);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        image.add(signup);
        
        setSize(1170, 650);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Login) {
            setVisible(false);
            new login();
        } else if (ae.getSource() == signup) {
            setVisible(false);
           
            new signup();
        }
    }

    public static void main(String[] args) {
        new frontpage();
    }
}