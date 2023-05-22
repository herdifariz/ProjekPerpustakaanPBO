/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import koneksi.Connector;
import view.Login;
import view.MainMenu;


/**
 *
 * @author acer
 */
public class LoginController {
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    Login frame;
    
    public LoginController(Login frame){
        this.frame = frame;
        Connector DB = new Connector();
        DB.connection();
        con = Connector.con;
        stat = Connector.stm;
    }
    
    public void login(){
        String username = frame.getTxUsername().getText();
        String password = frame.getTxPassword().getText();
       
        String Cekuser = null;
        String cekpass = null;
        
        if (!username.isEmpty() || !password.isEmpty()){
            try {
                sql = "SELECT * FROM user WHERE username='"+username+"' AND password='"+password+"'";
                rs = stat.executeQuery(sql);
                while(rs.next()){
                    Cekuser = rs.getString("username");
                    cekpass = rs.getString("password");
                }
            } catch (SQLException e){
                System.out.println("Terjadi error");
            }

                if(Cekuser == null && cekpass == null){
                    String pesan = "USERNAME ATAU PASSWORD SALAH";
                    JOptionPane.showMessageDialog(null, pesan, "Message", JOptionPane.INFORMATION_MESSAGE);
    //                this.dispose();
    //                Login view = new Login();
    //                view.setTitle("Login");
    //                view.setVisible(true);
    //                view.setLocationRelativeTo(null);
                }else{
                    String pesan = "Login Berhasil";
                    JOptionPane.showMessageDialog(null, pesan, "Message", JOptionPane.INFORMATION_MESSAGE);
                    MainMenu menu = new MainMenu();
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    frame.dispose();
                }
            
        } else {
            JOptionPane.showMessageDialog(null, "USERNAME ATAU PASSWORD SALAH", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
