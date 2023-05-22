/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.Connector;
import view.Login;
import view.Register;

/**
 *
 * @author acer
 */
public class RegisterController {
    Connection con;
    Statement stat;
    ResultSet r;
    String sql;
    Register frame;
    
    public RegisterController(Register frame){
        this.frame = frame;
        Connector DB = new Connector();
        DB.connection();
        con = Connector.con;
        stat = Connector.stm;
    }
    
    public void register(){
        String user = frame.getTextUser().getText();
        String password = frame.getTextPass1().getText();
        String password2 = frame.getTextPass2().getText();
        String Cekuser = null;

    if(!user.isEmpty() || !password.isEmpty() || !password2.isEmpty()){
        try {
            sql = "SELECT * FROM user WHERE username='"+user+"' AND password='"+password+"'";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet r = statement.executeQuery();

            while (r.next()) {
                Cekuser = r.getString("username");
            }

            r.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error");
        }

        if (!password.equals(password2)) {
            String pesan2 = "Password Tidak Cocok";
            JOptionPane.showMessageDialog(null, pesan2, "Kesalahan", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                sql = "INSERT INTO user VALUES ('"+user+"', '"+password+"')";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

                String pesan3 = "AKUN ANDA BERHASIL TERDAFTAR";
                JOptionPane.showMessageDialog(null, pesan3, "BERHASIL", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                Login a = new Login();
                a.setVisible(true);
                a.setLocationRelativeTo(null);
                a.setTitle("Login");
            } catch (SQLException e) {
                // Handle the exception appropriately
            }
        }
        
    } else {
        JOptionPane.showMessageDialog(null, "SILAKAN LENGKAPI DATA", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    }
}
