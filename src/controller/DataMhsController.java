/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;
import DAOData.DataMhsDAO;
import DAOImplement.DataMhsImplement;
import model.*;
import view.Mahasiswa;

/**
 *
 * @author acer
 */
public class DataMhsController {
    Mahasiswa frame;
    DataMhsImplement impldatamhs;
    List<DataMhs> dp;
    
    public DataMhsController(Mahasiswa frame){
        this.frame = frame;
        impldatamhs = new DataMhsDAO();
        dp = impldatamhs.getAll();
    }
    public void isitabel(){
        dp = impldatamhs.getAll();
        ModelTabelMhs mp = new ModelTabelMhs(dp);
        frame.getTabelMahasiswa().setModel(mp);
    }
    
    public void insert(){
        DataMhs mhs = new DataMhs();
        mhs.setNim(frame.getTfNIM().getText());
        mhs.setNama(frame.getTfNama().getText());
        mhs.setJurusan(frame.getTfJurusan().getText());
        impldatamhs.insert(mhs);
        
    }
    
    public void update(){
        DataMhs dp = new DataMhs();
        dp.setNim(frame.getTfNIM().getText());
        dp.setNama(frame.getTfNama().getText());
        dp.setJurusan(frame.getTfJurusan().getText());
        dp.setId(Integer.parseInt(frame.getIDLabel().getText()));
        impldatamhs.update(dp);
    }
    
    public void delete(){
        int id = Integer.parseInt(frame.getIDLabel().getText());
        impldatamhs.delete(id);
    }
    
    public void reset(){
        frame.getIDLabel().setText(null);
        frame.getTfNIM().setText(null);
        frame.getTfNama().setText(null);
        frame.getTfJurusan().setText(null);       
    }
}
