import DAO.InterfaceRegionDAO;
import DAO.Implementation.Region;
import DAO.Implementation.RegionDAO;

public class App {
    public static void main(String[] args) throws Exception {
        DbConnection connection = new DbConnection();
        System.out.println(connection.getConnection());

        RegionDAO rdao = new RegionDAO(connection.getConnection());
        

        Region region = new Region(5, "Papua");
        
        System.out.println(rdao.insert(region));

        Region region2 = rdao.get(4);
        System.out.println("Id: " + region2.getid() + "\tName: " + region2.getname());
        
        System.out.println("Affected row(s): " + rdao.delete(5));

        // getAll using method in class
        for(Region region3 : rdao.get()){
            System.out.println("Id: " + region3.getid() + "\tName: " + region3.getname());
        }      

        InterfaceRegionDAO rdao2 = new RegionDAO(connection.getConnection());
        // getAll using method from interface
        for(Region region3 : rdao2.get()){
            System.out.println("Id: " + region3.getid() + "\tName: " + region3.getname());
        }

        Region region4 = rdao2.get(3);
        System.out.println("Id: " + region4.getid() + "\tName: " + region4.getname());

        Region region7 = new Region(5, "Canada");
        // update with object parameter
        System.out.println("Affected row(s): " + rdao2.update(region7));
        
        System.out.println(rdao2.delete(5));
    }
}