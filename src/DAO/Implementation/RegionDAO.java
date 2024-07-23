package DAO.Implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.InterfaceRegionDAO;

public class RegionDAO implements InterfaceRegionDAO{
  private Connection connection;

  public RegionDAO(Connection connection) {
    this.connection = connection;
  }

  public boolean insert(Region region){
    try{
      PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO regions (region_id, region_name) VALUES (?,?);");
      preparedStatement.setInt(1, region.getid());
      preparedStatement.setString(2, region.getname());
      preparedStatement.executeUpdate();
      return true;
    } catch(SQLException e){
      // e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return false;
  }

  public int update(Region inputRegion){
    String query = "UPDATE regions SET region_name = ? WHERE region_id = ?";
    Region region = get(inputRegion.getid());
    if(region == null){
      return 0;
    }
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, inputRegion.getname());
      statement.setInt(2, region.getid());
      int countAffectedRow = statement.executeUpdate();
      return (countAffectedRow);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return 0;
  }

  public int delete(int inputId){
    String query = "DELETE FROM regions WHERE region_id = ?";
    Region region = get(inputId);
    if(region == null){
      return 0;
    }
    try {
      PreparedStatement statement = connection.prepareStatement(query);
      statement.setInt(1, region.getid());
      int countAffectedRow = statement.executeUpdate();
      return (countAffectedRow);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return 0;
  }

  public List<Region> get() {
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
 
  public Region get(int inputId) {
    String query = "SELECT region_id, region_name FROM regions WHERE region_id = ?";
    Region result = null;
    try (PreparedStatement getID = connection.prepareStatement(query)) {
        getID.setInt(1, inputId);
        try (ResultSet rs = getID.executeQuery()) {
            if (rs.next()) {
              result = new Region(rs.getInt("region_id"), rs.getString("region_name"));
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return result;
  }

}

