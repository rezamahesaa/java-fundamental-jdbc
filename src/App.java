public class App {
    public static void main(String[] args) throws Exception {
        DbConnection connection = new DbConnection();
        System.out.println(connection.getConnection());

        RegionDAO rdao = new RegionDAO(connection.getConnection());
        for(Region region : rdao.getAll()){
            System.out.println(region.getRegion_id());
            System.out.println(region.getRegion_name());
        }

        Region region = new Region(5, "Papua");
        
        System.out.println(rdao.insert(region));

        // System.out.println(rdao.getById(1));

        // System.out.println(rdao.update(5, "wawaw"));
        
        // System.out.println(rdao.delete(5));
    }
}