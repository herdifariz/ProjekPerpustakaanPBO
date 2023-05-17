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
public class ModelTabelBuku extends AbstractTableModel{
    List<DataBuku> dp;
    public ModelTabelBuku(List<DataBuku>dp){
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
                return "JUDUL";
            case 2:
                return "PENGARANG";
            case 3:
                return "PENERBIT";
            case 4:
                return "TAHUN TEBIT";
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
                return dp.get(row).getJudul();
            case 2:
                return dp.get(row).getPengarang();
            case 3:
                return dp.get(row).getPenerbit();
            case 4:
                return dp.get(row).getTahun();
            default:
                return null;
        }
    }
}
