package DAO;
import java.util.List;
import DAO.Implementation.*;

public interface InterfaceRegionDAO {
    public boolean insert(Region region);
    public int update(Region inputRegion);
    public int delete(int inputId);
    public List<Region> get();
    public Region get(int inputId);
}
