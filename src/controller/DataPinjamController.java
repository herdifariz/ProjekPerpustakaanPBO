/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAOData.DataPinjamDAO;
import DAOImplement.DataPinjamImplement;
import java.sql.*;
import javax.swing.JOptionPane;
import model.*;
import view.Peminjaman;
import koneksi.Connector;

/**
 *
 * @author acer
 */
public class DataPinjamController {
    Connection connection;
    Peminjaman frame;
    DataPinjamImplement impldatapinjam;
    List<DataPinjam> dp;
    
    public DataPinjamController(Peminjaman frame){
        this.frame = frame;
        impldatapinjam = new DataPinjamDAO();
        dp = impldatapinjam.getAll();
        connection = Connector.connection();
    }
    public void isitabel(){
        dp = impldatapinjam.getAll();
        ModelTabelPinjam mp = new ModelTabelPinjam(dp);
        frame.getTabelPinjam().setModel(mp);
    }
    
    public void insert(){
        DataPinjam pinjam = new DataPinjam();
        pinjam.setIdBuku(frame.getTfKode().getText());
        pinjam.setIdMhs(frame.getTfID().getText());
        if (pinjam.getIdBuku().isEmpty() || pinjam.getIdMhs().isEmpty()){
            JOptionPane.showMessageDialog(frame, "Silakan masukkan data", "Error", 0);
        } else {
            impldatapinjam.insert(pinjam);
        }
        
    }
    
    public void update(){
        DataPinjam dp = new DataPinjam();
        dp.setIdBuku(frame.getTfKode().getText());
        dp.setIdMhs(frame.getTfID().getText());
        dp.setId(Integer.parseInt(frame.getIDLabel().getText()));
        impldatapinjam.update(dp);
    }
    
    public void delete(){
        int id = Integer.parseInt(frame.getIDLabel().getText());
        impldatapinjam.delete(id);
    }
    
    public void reset(){
        frame.getIDLabel().setText(null);
        frame.getTfJudul().setText(null);
        frame.getTfKode().setText(null);
        frame.getTfNama().setText(null);
        frame.getTfNim().setText(null);
        frame.getTfID().setText(null);
    }
    
    public void ComboBoxBuku(){
        try {
            String query = "SELECT * FROM buku";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {                
                frame.getCbBuku().addItem(rs.getString("judul"));
            }
           
        } catch (SQLException e) {
        }
    }
    
    public void ComboBoxMhs(){
        try {
            String query = "SELECT * FROM mahasiswa";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {                
                frame.getCbMhs().addItem(rs.getString("nama"));
            }
           
        } catch (SQLException e) {
        }
    }
    
}
