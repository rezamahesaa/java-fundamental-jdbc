import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {
  private Connection connection;

  public RegionDAO(Connection connection) {
    this.connection = connection;
  }

  public List<Region> getAll() {
    List<Region> regions = new ArrayList<>();
    String query = "SELECT * FROM regions";

    try {
      ResultSet resultSet = connection.prepareStatement(query).executeQuery();

      while (resultSet.next()) {
        Region region = new Region(resultSet.getInt("region_id"), resultSet.getString("region_name"));
        regions.add(region);
      }
    } catch (SQLException e) {
        // e.printStackTrace();
        System.out.println(e.getMessage());
    }
      return regions;
    }

  public boolean insert(Region region){
    try{
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO regions (region_id, region_name) VALUES (?,?);");
      preparedStatement.setInt(1, region.getRegion_id());
      preparedStatement.setString(2, region.getRegion_name());
      preparedStatement.executeUpdate();
      return true;
    } catch(SQLException e){
      // e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return false;
  }

  
  public Region getById(int input_id) {
    String query = "SELECT region_id, region_name FROM regions WHERE region_id = ?";
    Region result = null;
    try (PreparedStatement getID = connection.prepareStatement(query)) {
        getID.setInt(1, input_id);
        try (ResultSet rs = getID.executeQuery()) {
            if (rs.next()) {
              result = new Region(rs.getInt("region_id"), rs.getString("region_name"));
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    // Return false if no result is found or an exception occurs
    return result;

    // String query = "SELECT region_id, region_name FROM regions WHERE region_id = ?";
    // Region result = null;
    // try (PreparedStatement getID = connection.prepareStatement(query)) {
    //     getID.setInt(1, input_id);
    //     try (ResultSet rs = getID.executeQuery()) {
    //         if (rs.next()) {
    //             result = new Region(rs.getInt("region_id"), rs.getString("region_name"));
    //         }
    //     }
    // } catch (SQLException e) {
    //     System.out.println(e.getMessage());
    // }
    // return result;
  }

  public String update(int input_id, String newRegionName){
    String query = "UPDATE regions SET region_name = ? WHERE region_id = ?";
    Region region = getById(input_id);
    if(region == null){
      return "ID not found";
    }
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, newRegionName);
      statement.setInt(2, region.getRegion_id());
      int countAffectedRow = statement.executeUpdate();
      return ("Affected row(S): " + countAffectedRow);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return "ID not found";
  }

  public String delete(int input_id){
    String query = "DELETE FROM regions WHERE region_id = ?";
    Region region = getById(input_id);
    if(region == null){
      return "ID not found";
    }
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, region.getRegion_id());
      int countAffectedRow = statement.executeUpdate();
      return ("Affected row(S): " + countAffectedRow);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return "ID not found";
  }

}

