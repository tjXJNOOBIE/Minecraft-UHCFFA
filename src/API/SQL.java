package API;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SQL {
	 public static String username;
	  public static String password;
	  public static String database;
	  public static String host;
	  public static String port;
	  public static Connection connection;
	  
	  public SQL(String user, String pass, String host2, String dB) {}
	  
	  public static void connect() {
	    if (!isConnected()) {
	      try
	      {
	    	  connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, 
	          password);
	        Bukkit.getConsoleSender().sendMessage("�aConnected to database");
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	  
	  public static void close() {
		  
	    if (isConnected()) {
	      try
	      {
	    	  connection.close();
	        Bukkit.getConsoleSender().sendMessage("�4Safely dissconnected from database");
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	  
	  public static boolean isConnected() {
	    return connection != null;
	  }
	  
	  public static void createTable() {
	    if (isConnected()) {
	      try
	      {
	    	  connection.createStatement().executeUpdate(
	    			  "CREATE TABLE IF NOT EXISTS elo (UUID VARCHAR(100), RATING int, RESETS int, USERNAME VARCHAR(20))");
	          Bukkit.getConsoleSender().sendMessage("�aElo table has been created!");
	      	  connection.createStatement().executeUpdate(
	    			  "CREATE TABLE IF NOT EXISTS ffa_stats (UUID VARCHAR(100), KILLS int, DEATHS int, ASSIST int, DAMAGEGIVEN int, DAMAGETAKEN int, CPS int, KDR double, USERNAME VARCHAR(20))");
	          Bukkit.getConsoleSender().sendMessage("�aStats table has been created!");
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	  
	  public static void update(String qry) {
	    if (isConnected()) {
	      try
	      {
	    	  connection.createStatement().executeUpdate(qry);
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	  }
	  
	  public static ResultSet getResult(String qry) {
	    ResultSet rs = null;
	    try
	    {
	      Statement st = connection.createStatement();
	      rs = st.executeQuery(qry);
	    }
	    catch (SQLException e) {
	      connect();
	      System.err.println(e);
	    }
	    return rs;
	  }
	  public static Statement createStatement() throws SQLException {
		  return connection.createStatement();
	  }
	  
	  public static File getMySQLFile() {
	    return new File("plugins/ELO", "database.yml");
	  }
	  
	  public static FileConfiguration getMySQLFileConfiguration() {
	    return YamlConfiguration.loadConfiguration(getMySQLFile());
	  }
	  
	  public static void setStandardMySQL() {
	    FileConfiguration cfg = getMySQLFileConfiguration();
	    
	    cfg.options().copyDefaults(true);
	    cfg.addDefault("username", "root");
	    cfg.addDefault("password", "password");
	    cfg.addDefault("database", "localhost");
	    cfg.addDefault("host", "localhost");
	    cfg.addDefault("port", "3306");
	    try
	    {
	      cfg.save(getMySQLFile());
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  public static void readMySQL() {
	    FileConfiguration cfg = getMySQLFileConfiguration();
	    username = cfg.getString("username");
	    password = cfg.getString("password");
	    database = cfg.getString("database");
	    host = cfg.getString("host");
	    port = cfg.getString("port");
	  }
	}


