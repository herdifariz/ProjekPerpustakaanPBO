/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAOData.DataPinjamDAO;
import DAOImplement.DataPinjamImplement;
import model.*;
import view.Peminjaman;

/**
 *
 * @author acer
 */
public class DataPinjamController {
    Peminjaman frame;
    DataPinjamImplement impldatapinjam;
    List<DataPinjam> dp;
    
    public DataPinjamController(Peminjaman frame){
        this.frame = frame;
        impldatapinjam = new DataPinjamDAO();
        dp = impldatapinjam.getAll();
    }
    public void isitabel(){
        dp = impldatapinjam.getAll();
        ModelTabelPinjam mp = new ModelTabelPinjam(dp);
        frame.getTabelPinjam().setModel(mp);
    }
    
    public void insert(){
        DataPinjam pinjam = new DataPinjam();
//        pinjam.setIdBuku(Integer.parseInt(frame.getTfKode().getText()));
//        pinjam.setIdMhs(Integer.parseInt(frame.getTfID().getText()));
        pinjam.setIdBuku(frame.getTfKode().getText());
        pinjam.setIdMhs(frame.getTfID().getText());
        impldatapinjam.insert(pinjam);
        
    }
    
    public void update(){
        DataPinjam dp = new DataPinjam();
        dp.setIdBuku(frame.getTfKode().getText());
        dp.setIdMhs(frame.getTfNim().getText());
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
    
}
