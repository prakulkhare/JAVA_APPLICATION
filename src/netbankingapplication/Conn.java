package netbankingapplication;

import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;
    public PreparedStatement ps;

    public Conn() {
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/netbanking", "root", "2003");
           
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
