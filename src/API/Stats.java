package API;

import API.SQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Stats {

    public static boolean playerExists(String uuid) {
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean OfflinePlayerExists(String username) {
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE USERNAME='" + username + "'");
            if (rs.next()) {
                return rs.getString("USERNAME") != null;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int getStat(String usernameORuuid, String colunm, String statfor, String table) {
        Integer i = Integer.valueOf(0);
        try {
            Statement error = info.techwizmatt.ServerCore.Main.sqlConnection.createStatement();
            ResultSet rs = error.executeQuery("SELECT * FROM " +table +" WHERE "+colunm+"='" + usernameORuuid + "'");
            if ((rs.next()) && (statfor) != null) {
            }
            i = (rs.getInt(statfor));
            while (rs.next()) {
                i = rs.getInt(statfor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }

    public static String getUUID(String username) {
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE USERNAME='" + username + "'");
            if (rs.next()) {
                return rs.getString("UUID");
            }
            return rs.getString("UUID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error Retriving UUID";
    }

    public static boolean playerDoesntExists(String uuid) {
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") == null;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean getUsername(String username) {
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE USERNAME='" + username + "'");
            if (rs.next()) {
                return rs.getString("USERNAME") != null;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getUsernametoString(String uuid) {
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("USERNAME");
            }
            return rs.getString("USERNAME");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error retriving name";
    }


    public static void updateName(String username, String uuid) {
        if (playerExists(uuid) == true) {
            SQL.update("UPDATE ffa_stats SET USERNAME='" + username + "' WHERE UUID='" + uuid + "'");
        }

    }

    public static void createPlayer(String username, String uuid) {
        Player p = Bukkit.getPlayer(username);
        String playeruuid = p.getUniqueId().toString();
        if (playerDoesntExists(playeruuid)) {
            SQL.update("INSERT INTO ffa_stats (UUID, KILLS, DEATHS, WINS, PLAYED, LOSSES, KDR, KILLSTREAK, BOWHITS, BOWMISSES, ELO, USERNAME) VALUES ('" + uuid + "', 0, 0, 0, 0,0,0,0,0,0,1400,'" + username + "');");

        } else {
            if (playerExists(playeruuid)) {
                return;
            }
        }


    }


    public static void updateUUID(Player p, String username) {
        SQL.update("UPDATE ffa_stats SET UUID='" + p.getUniqueId().toString() + "' WHERE USERNAME='" + username + "'");

    }

    public static Integer getKills(String username, String uuidORusername, String column) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("KILLS")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("KILLS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }

    public static Integer getWins(String username, String uuidORusername, String column) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("WINS")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("WINS"));
            return rs.getInt("WINS");

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return i;
    }

    public static Integer getLosses(String username, String uuidORusername, String column) {
        Integer i = Integer.valueOf(0);
        try {
            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("LOSSES")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("LOSSES"));
            return rs.getInt("LOSSES");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;

    }


    public static Integer getKillstreak(String username, String uuidORusername, String column) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("KILLSTREAK")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("KILLSTREAK"));
            return rs.getInt("KILLSTREAK");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }
    public static Integer getTier(String username, String uuidORusername, String column, String tier) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt(tier)) != null)) {
            }
            i = Integer.valueOf(rs.getInt(tier));
            return rs.getInt(tier);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }


    public static void setWins(String username, String uuid, int damagegiven) {
        SQL.update("UPDATE ffa_stats SET WINS='" + damagegiven + "' WHERE UUID='" + uuid + "'");
    }


    public static double getKDR(String username, String uuidORusername, String column) {
        double d = 0.00;
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next())) {
            }
            d = rs.getDouble("KDR");
            return rs.getDouble("KDR");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return d;
    }

    public static Integer getGamesPlayed(String username, String uuidORusername, String column) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("PLAYED")) == null)) {
            }
            i = Integer.valueOf(rs.getInt("PLAYED"));
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return i;
    }

    public static void setLosses(String username, String uuid, int d) {
        SQL.update("UPDATE ffa_stats SET LOSSES='" + d + "' WHERE UUID='" + uuid + "'");
    }

    public static String getClanName(Player name) {
        try {
            ResultSet rs = SQL.getResult("SELECT * FROM clans WHERE members = '" + name + "';");
            if (rs.next()) {
                return rs.getString("clan_name").replaceAll("c:", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error whlist updating";
    }

    public static String getSelectedKit(String uuid) {
        try {
            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE UUID= '" + uuid + "';");
            if (rs.next()) {
                return rs.getString("SELECTED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error whlist loading kit";
    }
    public static void setSelectedKit(String username, String uuid, String kit) {
        SQL.update("UPDATE ffa_stats SET SELECTED='" + kit + "' WHERE UUID='" + uuid + "'");
    }

    public static Integer getDeaths(String username, String uuidORusername, String column) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("DEATHS")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("DEATHS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

    public static Integer getBowAccuracy(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("BOWACCURACY")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("BOWACCURACY"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getBowMisses(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("BOWMISSES")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("BOWMISSES"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getBowHits(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("BOWHITS")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("BOWHITS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getSwingsMisses(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("SWINGMISSES")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("SWINGMISSES"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getSwingHits(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("SWINGHITS")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("SWINGHITS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getSwingAccuracy(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("SWINGACCURACY")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("SWINGACCURACY"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getDmgGiven(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("DMGGIVEN")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("DMGGIVEN"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getDmgTaken(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("DMGTAKEN")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("DMGTAKEN"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getDmgAverage(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("DMGAVERAGE")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("DMGAVERAGE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public static Integer getWinRate(String username, String uuidORusername, String column) {
        int i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("WINRATE")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("WINRATE"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }


    public static void setKills(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET KILLS='" + kills + "' WHERE UUID='" + uuid + "'");

    }

    public static void setWinRate(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET WINRATE='" + kills + "' WHERE UUID='" + uuid + "'");

    }
    public static void setSwingHits(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET SWINGHITS='" + kills + "' WHERE UUID='" + uuid + "'");

    }
    public static void setSwingMisses(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET SWINGMISSES='" + kills + "' WHERE UUID='" + uuid + "'");

    }
    public static void setSwingAccuracy(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET SWINGACCURACY='" + kills + "' WHERE UUID='" + uuid + "'");

    }

    public static void setBowAccuracy(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET BOWACCURACY='" + kills + "' WHERE UUID='" + uuid + "'");

    }
    public static void setBowMisses(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET BOWMISSES='" + kills + "' WHERE UUID='" + uuid + "'");

    }
    public static void setBowHits(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET BOWHITS='" + kills + "' WHERE UUID='" + uuid + "'");

    }

    public static void setLosses(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET LOSSES='" + kills + "' WHERE UUID='" + uuid + "'");
    }


    public static void setKillstreak(String username, String uuid, Integer kills) {
        SQL.update("UPDATE ffa_stats SET KILLSTREAK='" + kills + "' WHERE UUID='" + uuid + "'");
    }


    public static void setDeaths(String username, String uuid, Integer deaths) {
        SQL.update("UPDATE ffa_stats SET DEATHS='" + deaths + "' WHERE UUID='" + uuid + "'");
    }


    public static void setKDR(String username, String uuid, double kdr) {
        SQL.update("UPDATE ffa_stats SET KDR='" + kdr + "' WHERE UUID='" + uuid + "'");
    }


    public static void setGamesPlayed(String username, String uuid, int games) {
        SQL.update("UPDATE ffa_stats SET PLAYED='" + games + "' WHERE UUID='" + uuid + "'");
    }


    public static void addKills(String username, String uuid, int kills) {
        setKills(username, uuid, Integer.valueOf(getKills(username, uuid,"UUID").intValue() + kills));
    }


    public static void addDeaths(String username, String uuid, int deaths) {
        setDeaths(username, uuid, (getDeaths(username, uuid,"UUID").intValue() + deaths));
    }


    public static void removeKills(String username, String uuid, int kills) {
        setKills(username, uuid, Integer.valueOf(getKills(username, uuid,"UUID").intValue() - kills));
    }


    public static void removeDeaths(String username, String uuid, int deaths) {
        setDeaths(username, uuid, Integer.valueOf(getDeaths(username, uuid,"UUID").intValue() - deaths));

    }

    public static void addWins(String username, String uuid, int d) {
        setWins(username, uuid, d);
    }


    public static void addLosses(String username, String uuid, int d) {
        setLosses(username, uuid, Integer.valueOf(getLosses(username, uuid,"UUID").intValue() + d));
    }


    public static void addGamesPlayed(String username, String uuid, int d) {
        setGamesPlayed(username, uuid, Integer.valueOf(getGamesPlayed(username, uuid,"UUID").intValue() + d));

    }


    public static void removeWins(String username, String uuid, int d) {
        setWins(username, uuid, Integer.valueOf(getWins(username, uuid,"UUID").intValue() - d));
    }




    public static Integer getPoints(String username, String uuid) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE UUID='" + uuid + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("POINTS")) != null)) {
            }
            i = Integer.valueOf(rs.getInt("POINTS"));
            while (rs.next()) {
                i = rs.getInt("POINTS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }


    public static void setPoints(String uuid, String username, int d) {
        SQL.update("UPDATE ffa_stats SET POINTS='" + d + "' WHERE UUID='" + uuid + "'");


    }

    public static void addPoints(String username, String uuid, int d) {
        setPoints(uuid, username, Integer.valueOf(getPoints(username, uuid).intValue() + d));

    }

    public static void removePoints(String username, String uuid, int d) {
        setPoints(uuid, username, (getPoints(username, uuid) - d));
    }

    public static int getUsersInt() {
        int users = 0;
        try {
            ResultSet rs = SQL.getResult("SELECT COUNT(*) FROM ffa_stats");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("COUNT(*)")) != null)) {
            }
            users = Integer.valueOf(rs.getInt("COUNT"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public static String getUUIDs() {
        try {
            ResultSet rs = SQL.getResult("SELECT * FROM uhc_stats WHEREUUID;");
            while(rs.next()) {
                return rs.getString("UUID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error whlist gathering UUIDs";
    }


    public static int setRanks(String database, String statfor) {
        int rank = 0;
        try {

            PreparedStatement st = SQL.connection.prepareStatement("SELECT @rank:=@rank+1 AS rank, UUID," +statfor+ " FROM "+database +" ORDER BY "+statfor+ " DESC; ");
            st.executeQuery("SET @rank = 0;");
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                rank = rs.getInt("RANK");
                SQL.update("UPDATE " + database +" SET RANK=" +rank+ " WHERE UUID='" + rs.getString("UUID")+"'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rank;
    }
    public static Integer getRank(String uuidORusername, String column) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = SQL.getResult("SELECT * FROM ffa_stats WHERE "+column+"='" + uuidORusername + "'");
            if ((rs.next())) {
                i = rs.getInt("RANK");
                return rs.getInt("RANK");

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return i;
    }

    public static int getUsersInt(String database) {
        int users = 0;
        try {
            ResultSet rs = MySQL.getResult("SELECT COUNT(*) FROM "+database);
            users = rs.getInt("COUNT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public static void setRank(String uuid, String username, int d) {
        SQL.update("UPDATE ffa_stats SET RANK='" + d + "' WHERE UUID='" + uuid + "'");
    }
    // very sloppy shit here, not really caring rn
    public static String getFirst(String statfor) {
        String name = "CantGetName";
        int limit = 1;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("SELECT * FROM `ffa_stats` ORDER BY `WINS` DESC limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                name = rs.getString("USERNAME");
            } else {
                name = "CantGetName";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static String getSecond(String statfor) {
        String name = "CantGetName";
        int limit = 2;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("SELECT * FROM `ffa_stats` ORDER BY `"+statfor+"` DESC limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }


    public static String getThird(String statfor) {
        String name = "CantGetName";
        int limit = 3;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("select * from ffa_stats order by "+ statfor +" desc limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next() && rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String getForuth(String statfor) {
        String name = "CantGetName";
        int limit = 4;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("select * from ffa_stats order by "+ statfor +" desc limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next() && rs.next() && rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;

    }

    public static String getFifit(String statfor) {
        String name = "CantGetName";
        int limit = 5;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("select * from ffa_stats order by "+ statfor +" desc limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next() && rs.next() && rs.next() && rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;

    }

    public static String getSixth(String statfor) {
        String name = "CantGetName";
        int limit = 6;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("select * from ffa_stats order by "+ statfor +" desc limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next() && rs.next() && rs.next() && rs.next() && rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;

    }

    public static String getSeven(String statfor) {
        String name = "CantGetName";
        int limit = 7;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("select * from ffa_stats order by "+ statfor +" desc limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;

    }

    public static String getEight(String statfor) {
        String name = "CantGetName";
        int limit = 8;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("select * from ffa_stats order by "+ statfor +" desc limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;

    }

    public static String getNine(String statfor) {
        String name = "CantGetName";
        int limit = 9;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("select * from ffa_stats order by "+ statfor +" desc limit " + limit);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;

    }

    public static String getTen(String statfor) {
        String name = "CantGetName";
        int limit = 10;
        try {
            PreparedStatement st = SQL.connection.prepareStatement("select * from ffa_stats order by "+ statfor +" desc limit " + limit);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next() && rs.next()) {
                    name = rs.getString("USERNAME");
                } else {
                    name = "CantGetName";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;

    }
}






