package DAO.Implementation;
public class Region {
    private int id;
    private String name;
  
    public Region(){
  
    }
  
    public Region(int id, String name) {
      this.id = id;
      this.name = name;
    }
  
    public int getid() {
      return id;
    }
  
    public void setid(int id) {
      this.id = id;
    }
  
    public String getname() {
      return name;
    }
  
    public void setname(String name) {
      this.name = name;
    }
  
}