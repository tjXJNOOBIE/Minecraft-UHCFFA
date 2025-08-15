package API;

/**
 * Created by TJ on 2/21/2018.
 */
public class Live  {

    public static void updateMap(String map,String serverid) {
        SQL.update("UPDATE live_info SET MAP='" + map + "' WHERE SERVERID='"+serverid+"'");

    }

    public static void updateServerID(String serverid) {
        SQL.update("UPDATE live_info SET SERVERID='" + serverid + "'");

    }
    public static void createServerID(String gameID) {
        SQL.update("INSERT INTO live_info (SERVERID) VALUES ('" + gameID + "');");
    }
}
