/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import view.Login;

/**
 *
 * @author acer
 */
public class Connector {
    
    public static Connection con;
    public static Statement stm;
    
    public static Connection connection(){
        if(con==null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("db_perpustakaan");
            data.setUser("root");
            data.setPassword("");
            try{
                con = data.getConnection();
                stm = con.createStatement();
                System.out.println("Koneksi berhasil");
            }catch(SQLException ex){
                ex.printStackTrace();
                System.out.println("Koneksi gagal");
            }
        }
        
        return con;
    }
}
