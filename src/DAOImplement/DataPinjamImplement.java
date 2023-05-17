/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOImplement;
import java.util.List;
import model.*;

/**
 *
 * @author acer
 */
public interface DataPinjamImplement {
    public void insert(DataPinjam p);
    public void update(DataPinjam p);
    public void delete(int id);
    public List<DataPinjam> getAll();
}
