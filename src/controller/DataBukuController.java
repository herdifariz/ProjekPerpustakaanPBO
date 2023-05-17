/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAOData.DataBukuDAO;
import DAOImplement.DataBukuImplement;
import model.*;
import view.Buku;
/**
 *
 * @author acer
 */
public class DataBukuController {
    Buku frame;
    DataBukuImplement impldatabuku;
    List<DataBuku> dp;
    
    public DataBukuController(Buku frame){
        this.frame = frame;
        impldatabuku = new DataBukuDAO();
        dp = impldatabuku.getAll();
    }
    public void isitabel(){
        dp = impldatabuku.getAll();
        ModelTabelBuku mp = new ModelTabelBuku(dp);
        frame.getTabelBuku().setModel(mp);
    }
    
    public void insert(){
        DataBuku buku = new DataBuku();
        buku.setJudul(frame.getTfJudul().getText());
        buku.setPengarang(frame.getTfPengarang().getText());
        buku.setPenerbit(frame.getTfPenerbit().getText());
        buku.setTahun(Integer.parseInt(frame.getTfTahun().getText()));
        impldatabuku.insert(buku);
        
    }
    
    public void update(){
        DataBuku dp = new DataBuku();
        dp.setJudul(frame.getTfJudul().getText());
        dp.setPengarang(frame.getTfPengarang().getText());
        dp.setPenerbit(frame.getTfPenerbit().getText());
        dp.setTahun(Integer.parseInt(frame.getTfTahun().getText()));
        dp.setId(Integer.parseInt(frame.getIDLabel().getText()));
        impldatabuku.update(dp);
    }
    
    public void delete(){
        int id = Integer.parseInt(frame.getIDLabel().getText());
        impldatabuku.delete(id);
    }
    
    public void reset(){
        frame.getIDLabel().setText(null);
        frame.getTfJudul().setText(null);
        frame.getTfPengarang().setText(null);
        frame.getTfPenerbit().setText(null);
        frame.getTfTahun().setText(null);
    }
}
