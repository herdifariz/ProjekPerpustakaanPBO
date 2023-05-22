/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOData;
import java.sql.*;
import java.util.*;
import koneksi.Connector;
import model.*;
import DAOImplement.DataBukuImplement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author acer
 */
public class DataBukuDAO implements DataBukuImplement{
    Connection connection;
    
    final String select = "SELECT * from buku;";
    final String insert = "INSERT INTO buku (judul, pengarang, penerbit, tahun_terbit) VALUES (?, ?, ?, ?);";
    final String update = "UPDATE buku set judul=?, pengarang=?, penerbit=?, tahun_terbit=? where id=?";
    final String delete = "DELETE from buku where id=?";
    public DataBukuDAO(){
        connection = Connector.connection();
    }

    @Override
    public void insert(DataBuku p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getPengarang());
            statement.setString(3, p.getPenerbit());
            statement.setString(4, p.getTahun());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                p.setId(rs.getInt(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(DataBuku p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getJudul());
            statement.setString(2, p.getPengarang());
            statement.setString(3, p.getPenerbit());
            statement.setString(4, p.getTahun());
            statement.setInt(5, p.getId());
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<DataBuku> getAll() {
        List<DataBuku> dp = null;
        try{
            dp = new ArrayList<DataBuku>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                DataBuku buku = new DataBuku();
                buku.setId(rs.getInt("id"));
                buku.setJudul(rs.getString("judul"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setTahun(rs.getString("tahun_terbit"));
                dp.add(buku);
                
            }
        }catch(SQLException ex){
            Logger.getLogger(DataBukuDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return dp;
    }
}
