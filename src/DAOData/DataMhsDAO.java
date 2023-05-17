/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOData;
import java.sql.*;
import java.util.*;
import koneksi.Connector;
import model.*;
import DAOImplement.DataMhsImplement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author acer
 */
public class DataMhsDAO implements DataMhsImplement{
    Connection connection;
    
    final String select = "SELECT * from mahasiswa;";
    final String insert = "INSERT INTO mahasiswa (nim, nama, jurusan) VALUES (?, ?, ?);";
    final String update = "UPDATE mahasiswa set nim=?, nama=?, jurusan=? where id=?";
    final String delete = "DELETE from mahasiswa where id=?";
    public DataMhsDAO(){
        connection = Connector.connection();
    }

    @Override
    public void insert(DataMhs p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getNim());
            statement.setString(2, p.getNama());
            statement.setString(3, p.getJurusan());
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
    public void update(DataMhs p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getNim());
            statement.setString(2, p.getNama());
            statement.setString(3, p.getJurusan());
            statement.setInt(4, p.getId());
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
    public List<DataMhs> getAll() {
        List<DataMhs> dp = null;
        try{
            dp = new ArrayList<DataMhs>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                DataMhs mhs = new DataMhs();
                mhs.setId(rs.getInt("id"));
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setJurusan(rs.getString("jurusan"));
                dp.add(mhs);
                
            }
        }catch(SQLException ex){
            Logger.getLogger(DataBukuDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return dp;
    }
}
