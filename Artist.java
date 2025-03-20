public class Artist{
  private long ID;//make all attributes private 
  private String name;
  private double age;
  private String talent;
  public Artist next;
  
  Artist(){}//default constructor 

  
  //getters and setters
  public void setId(long ID){
    this.ID = ID;
  }
  
  long getID(){
    return ID;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  String getName(){
    return name;
  }
  
  public void setAge(double age){
    this.age = age;
  }
  
  double getAge(){
    return age;
  }
  
  public void setTalent(String talent){
    this.talent = talent;
  }
  
  String getTalent(){
    return talent;
  }
  
  
  
  
}