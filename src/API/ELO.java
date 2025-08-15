package API;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ELO {
	
  public static boolean playerExists(String uuid){
    try {
    	
      ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE UUID='" + uuid + "'");
      if (rs.next()) {
        return rs.getString("UUID") != null;
      }
      return false;
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  public static String getUsername(String username) {
	    try {
	      ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE USERNAME='" + username + "'");
	      if (rs.next()) {
	        return username;
	      }
	    
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
		return username;
	  }
public static void updateName(String username, String uuid) {
    if (playerExists(uuid)) {
      SQL.update("UPDATE ffa_stats SET USERNAME='" + username + "' WHERE UUID='" + uuid + "'");
    }
    else {
    	
      createPlayer(username, uuid);
      updateName(username, uuid);
    }
  }
	  
   public static void createPlayer(String username, String uuid){
    if (!playerExists(uuid)) {
      SQL.update("INSERT INTO ffa_stats (UUID, RATING, RESETS, USERNAME) VALUES ('" + uuid + "','1400', '0','" + username + "');");
    }
  }
  
   public static int getPlayerRating(String uuidORusername, String colum){
    	   int d = 0;
    	    if (playerExists(uuidORusername)){
    	      try {
    		    ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+colum+"='" + uuidORusername + "'");

    	        if ((rs.next())) {}
    	        d = rs.getInt("ELO");
    	        return rs.getInt("ELO");

    	    }
    	      catch (SQLException e){
    	        e.printStackTrace();
    	    }
    	    }

			return d;
    	    }
    
   public static void setRating(String uuid, int d){
          SQL.update("UPDATE ffa_stats SET ELO='" + d + "' WHERE UUID='" + uuid + "'");
   }
            
            



   public static double addRating(String username, String uuid, int d) {
    if(playerExists(uuid)) {
    	setRating(uuid, getPlayerRating(uuid,"UUID") + d);
    	
    	}
    else{
    	
      createPlayer(username, uuid);
      addRating(username, uuid, d);
    	}
	return d;
   }
  
  
  public static void removeRating(String username, String uuid, int d) {
    if(playerExists(uuid)) {
      setRating(uuid, getPlayerRating(uuid,"UUID") - d);
    }
    else{
    	
      createPlayer(uuid, uuid);
      removeRating(username, uuid, d);
    }
  }


  		// GETTING, SETTING, AND REMOVING THE AMOUNT OF 100 RESETS THE PLAYER HAS AQUIRED



public static void setResets(String username, String uuid, int d) {
    if(playerExists(uuid)) {
      SQL.update("UPDATE ffa_stats SET RESETS='" + d + "' WHERE UUID='" + uuid + "'");
    }
    else {
    	
      createPlayer(uuid, uuid);
      setRating(uuid, d);
    }
  }


public static void removeResets(String username, String uuid, int d) {
    if(playerExists(uuid)) {
      setResets(username, uuid, (getPlayerResets(username,uuid) - d));
    }
    else {
    	
      createPlayer(username, uuid);
      removeResets(username, uuid, d);
    }
  }

public static  int addResets(String username, String uuid, int d) {
    if(playerExists(uuid)) {
    	setResets(username, uuid, getPlayerResets(username,uuid) + d);
    	
    }
    else {
    	
      createPlayer(username, uuid);
      addResets(username, uuid, d);
    }
	return d;
  }

public static int getPlayerResets(String username, String uuid) {
	   double d = 0;
	    if(playerExists(uuid)) {
	      try {
	    	  
	        ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE UUID='" + uuid + "'");
	        if ((rs.next())) {}
	        rs.getString("RESETS");


	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    else
	    {
	      createPlayer(uuid, uuid);
	      getPlayerRating(uuid,"UUID");
	    }
		return (int) d;
	  }
}
