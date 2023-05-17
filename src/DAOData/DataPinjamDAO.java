/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOData;
import java.sql.*;
import java.util.*;
import koneksi.Connector;
import model.*;
import DAOImplement.DataPinjamImplement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class DataPinjamDAO implements DataPinjamImplement{
    Connection connection;
    
    final String select = "SELECT * from peminjaman;";
    final String insert = "INSERT INTO peminjaman (id_buku, id_mahasiswa, tgl_pinjam, tgl_kembali) VALUES (?, ?, CURRENT_DATE, CURRENT_DATE+7);";
    final String update = "UPDATE peminjaman set id_buku=?, id_mahasiswa=? where id=?";
    final String delete = "DELETE from peminjaman where id=?";
    public DataPinjamDAO(){
        connection = Connector.connection();
    }

    @Override
    public void insert(DataPinjam p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getIdBuku());
            statement.setString(2, p.getIdMhs());
//            statement.setString(3, p.getTglPinjam());
//            statement.setString(4, p.getTglKembali());
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
    public void update(DataPinjam p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getIdBuku());
            statement.setString(2, p.getIdMhs());
//            statement.setString(3, p.getTglPinjam());
//            statement.setString(4, p.getTglKembali());
            statement.setInt(3, p.getId());
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
    public List<DataPinjam> getAll() {
        List<DataPinjam> dp = null;
        try{
            dp = new ArrayList<DataPinjam>();
            String query = "SELECT * FROM peminjaman INNER JOIN buku ON peminjaman.id_buku = buku.id INNER JOIN mahasiswa ON peminjaman.id_mahasiswa = mahasiswa.id";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                DataPinjam mhs = new DataPinjam();
                mhs.setId(rs.getInt("peminjaman.id"));
                mhs.setIdBuku(rs.getString("buku.judul"));
                mhs.setIdMhs(rs.getString("mahasiswa.nama"));
                mhs.setTglPinjam(rs.getString("tgl_pinjam"));
                mhs.setTglKembali(rs.getString("tgl_kembali"));
                dp.add(mhs);
                
            }
        }catch(SQLException ex){
            Logger.getLogger(DataBukuDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return dp;
    }
    
}
