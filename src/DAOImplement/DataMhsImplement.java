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
public interface DataMhsImplement {
    public void insert(DataMhs p);
    public void update(DataMhs p);
    public void delete(int id);
    public List<DataMhs> getAll();
}
