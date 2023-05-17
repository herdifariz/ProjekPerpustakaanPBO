/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author acer
 */
public class ModelTabelPinjam extends AbstractTableModel{
    List<DataPinjam> dp;
    public ModelTabelPinjam(List<DataPinjam>dp){
        this.dp = dp;
    }
    
    @Override
    public int getRowCount() {
        return dp.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "ID";
            case 1:
                return "NAMA BUKU";
            case 2:
                return "NAMA MHS";
            case 3:
                return "TGL PINJAM";
            case 4:
                return "TGL KEMBALI";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dp.get(row).getId();
            case 1:
                return dp.get(row).getIdBuku();
            case 2:
                return dp.get(row).getIdMhs();
            case 3:
                return dp.get(row).getTglPinjam();
            case 4:
                return dp.get(row).getTglKembali();
            default:
                return null;
        }
    }
}
