
package mealmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * @author SHAFIN
 */
public class DBConfiguration {
    
    String MySQL_DATABASE_URL;
    String SQLite_DATABASE_URL;
    String USER = "root";
    String PASSWORD = "1234";
    
    //setters
    public void setSQLite_DATABASE_URL() throws UnsupportedEncodingException {
      
        String path = DBConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, "UTF-8");
        
        System.out.println(decodedPath);
        SQLite_DATABASE_URL = "jdbc:sqlite:"+decodedPath+"meal_manager.sqlite";
    }
    
    public void setMySQL_DATABASE_URL(String URL){
               
        MySQL_DATABASE_URL = URL;
    }
    
    public void setUSER(String user){
               
        USER = user;
    }
    
    public void setPASSWORD(String password){
               
        PASSWORD = password;
    }
    
    //getters
     public String getSQLite_DATABASE_URL(){
              
        return SQLite_DATABASE_URL ;
    }
    
    public String getMySQL_DATABASE_URL(){
               
        return MySQL_DATABASE_URL;
    }
    
    public String getUSER(){
               
        return USER;
    }
    
    public String getPASSWORD(){
               
        return PASSWORD;
    }
}
