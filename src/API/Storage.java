package API;

import info.techwizmatt.ServerCore.API.CoinAPI;
import info.techwizmatt.ServerCore.Main;
import info.techwizmatt.ServerCore.SqlDB.DatabaseCore;
import org.apache.commons.lang.mutable.MutableInt;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class Storage {
	private static Map<UUID, MutableInt> players;
	private static Map<Player, Object> stats = new HashMap<Player, Object>();

	public static Map kills = new HashMap();
	private static Map deaths = new HashMap();
	private static Map played = new HashMap();
	private static Map wins = new HashMap();
	private static Map streak = new HashMap();
	private static Map losses = new HashMap();
	private static Map bowhits = new HashMap();
	private static Map bowmisses = new HashMap();
	private static Map swings = new HashMap();
	private static Map swinghits = new HashMap();
	private static Map dmgtaken = new HashMap();
	private static Map dmggiven = new HashMap();
	private static Map chest = new HashMap();
	private static Map coins = new HashMap();
	private static Map earnedcoins = new HashMap();
	private static Map elo = new HashMap();
	private static Map rushertier = new HashMap();
	private static Map healertier = new HashMap();
	private static Map weldertier = new HashMap();
	private static Map armourtier = new HashMap();

	public Storage() {
		players = new HashMap<>();
	}

	public void add(Player player) {
		kills.put(player.getName(), 0);
		played.put(player.getName(), 0);
		wins.put(player.getName(), 0);
		streak.put(player.getName(), 0);
		losses.put(player.getName(), 0);
		deaths.put(player.getName(), 0);
		bowhits.put(player.getName(), 0);
		bowmisses.put(player.getName(), 0);
		swings.put(player.getName(), 0);
		swinghits.put(player.getName(), 0);
		dmgtaken.put(player.getName(), 0);
		dmggiven.put(player.getName(), 0);
		chest.put(player.getName(), 0);
		coins.put(player.getName(),0);
		earnedcoins.put(player.getName(),0);
		elo.put(player.getName(),0);
		rushertier.put(player.getName(),0);
		healertier.put(player.getName(),0);
		weldertier.put(player.getName(),0);
		armourtier.put(player.getName(),0);


	}

	public void remove(Player player) {
		kills.remove(player.getName());
		played.remove(player.getName());
		wins.remove(player.getName());
		losses.remove(player.getName());
		deaths.remove(player.getName());
		bowhits.remove(player.getName());
		bowmisses.remove(player.getName());
		swings.remove(player.getName());
		swinghits.remove(player.getName());
		dmggiven.remove(player.getName());
		dmgtaken.remove(player.getName());
		chest.remove(player.getName());
		coins.remove(player.getName());
		earnedcoins.remove(player.getName());
		elo.remove(player.getName());
		rushertier.remove(player.getName());
		healertier.remove(player.getName());
		weldertier.remove(player.getName());
		armourtier.remove(player.getName());

	}

	public void addKill(Player player) {
		kills.put(player.getName(), (int) getKills(player) + 1);
	}

	public void addDeaths(Player player) {
		deaths.put(player.getName(), (int) getDeaths(player) + 1);
	}

	public void addLosses(Player player) {
		losses.put(player.getName(), (int) getLosses(player) + 1);
	}

	public void addPlayed(Player player) {
		played.put(player.getName(), (int) getPlayed(player) + 1);
	}

	public void addStreak(Player player) {
		streak.put(player.getName(), (int) getStreak(player) + 1);
	}
	public void resetStreaks(Player player) {
		streak.remove(player);
		streak.put(player.getName(), 0);
	}
	public void addWins(Player player) {
		wins.put(player.getName(), (int) getWins(player) + 1);
	}

	public void addBowMiss(Player player) {
		bowmisses.put(player.getName(), (int) getBowMisses(player) + 1);
	}

	public void addBowHit(Player player) {
		bowhits.put(player.getName(), (int) getBowHits(player) + 1);
	}

	public void addSwing(Player player) {
		swings.put(player.getName(), (int) getSwings(player) + 1);
	}

	public void addSwingHit(Player player) {
		swinghits.put(player.getName(), (int) getSwingHits(player) + 1);
	}

	public void addDmgTaken(Player player, int i) {
		dmgtaken.put(player.getName(), (int) getDmgTaken(player) + i);
	}

	public void addDmgGiven(Player player, Integer i) {
		dmggiven.put(player.getName(), (int) getDmgGiven(player) + i);
	}

	public void addChestsOpen(Player player) {
		chest.put(player.getName(), (int) getChestOpened(player) + 1);
	}


	public void addElo(Player player,int i) {
		elo.put(player.getName(), (int) getElo(player) + i);
	}

	public void addRusherTier(Player player, int i) {
		rushertier.put(player.getName(), (int)  i);
	}



	public void addHealerTier(Player player, int i) {
		healertier.put(player.getName(),i);
	}

	public void addCoins(Player player, Integer i) {
		coins.put(player.getName(),i);
	}

	public void addCoinsEarned(Player player, Integer i) {
		earnedcoins.put(player.getName(),i);
	}


	public void addWelderTier(Player player, int i) {
		weldertier.put(player.getName(), i);
	}

	public void addArmourTier(Player player, int i) {
		armourtier.put(player.getName(), i);
	}


	public Object getKills(Player player) {
		return kills.get(player.getName());
	}

	public Object getDeaths(Player player) {
		return deaths.get(player.getName());
	}

	public Object getPlayed(Player player) {
		return played.get(player.getName());
	}

	public Object getLosses(Player player) {
		return losses.get(player.getName());
	}

	public Object getStreak(Player player) {
		return streak.get(player.getName());
	}

	public Object getWins(Player player) {
		return wins.get(player.getName());
	}

	public Object getStat(Player player) {return stats.get(player);
	}


	public Object getCoins(Player player) {
		return coins.get(player.getName());
	}

	public Object getCoinsEarned(Player player) {
		return earnedcoins.get(player.getName());
	}

	public Object getElo(Player player) {
		return elo.get(player.getName());
	}

	public Object getRusherTier(Player player) {
		return rushertier.get(player.getName());
	}

	public Object getHealerTier(Player player) {
		return healertier.get(player.getName());
	}

	public Object getWelderTier(Player player) {
		return weldertier.get(player.getName());
	}

	public Object getArmourTier(Player player) {
		return armourtier.get(player.getName());
	}

	public Object getBowMisses(Player player) {
		return bowmisses.get(player.getName());
	}

	public Object getBowHits(Player player) {
		return bowhits.get(player.getName());
	}

	public Object getSwings(Player player) {
		return swings.get(player.getName());
	}

	public Object getSwingHits(Player player) {
		return swinghits.get(player.getName());
	}

	public Object getDmgTaken(Player player) {
		return dmgtaken.get(player.getName());
	}

	public Object getDmgGiven(Player player) {
		return dmggiven.get(player.getName());
	}

	public Object getChestOpened(Player player) {
		return chest.get(player.getName());
	}

	public Object setStat(Object j,Player player) {
		if(stats.containsKey(player) && stats.containsValue("ELO")){

			stats.replace(player,j);
		}
		return stats.put(player,j);
	}


	public void update(Player p, String playername, String playeruuid) {
		ELO.setRating(playeruuid,(int)getElo(p));
		if((int)getWins(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 wins");
		}
		if((int)getSwings(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 swings");
		}
		if((int)getSwingHits(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 swing hits");
		}
		if((int)getKills(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 kills");
		}
		if((int)getLosses(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 losses");
		}
		if((int)getDeaths(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 deaths");
		}
		if((int)getPlayed(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 played");
		}
		if((int)getBowMisses(p) == 0 || (int)getBowHits(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 bow misses");
		}
		if((int)getSwings(p) == 0 || (int)getSwingHits(p) == 0){
			Bukkit.getLogger().log(Level.INFO,p.getName()+" has left with 0 played");
		}







		if((int)getWins(p)>1) {
			Stats.addWins(playername, playeruuid, (int) getWins(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getWins(p));

		}
		if((int)getBowMisses(p)>0 ||(int)getBowHits(p)>0) {
			int bowrate = (int)(((int)Stats.getBowMisses(p.getName(), p.getUniqueId().toString(),"UUID") * 100.0f) / (int)Stats.getBowHits(p.getName(), p.getUniqueId().toString(),"UUID"));
			Stats.setBowAccuracy(playername,playeruuid,bowrate);

			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getWins(p));

		}
		if((int)getSwings(p)>0 ||(int)getSwingHits(p)>0) {
			int swingrate = (int)(((int)Stats.getSwingHits(p.getName(), p.getUniqueId().toString(),"UUID") * 100.0f) / (int)Stats.getSwingsMisses(p.getName(), p.getUniqueId().toString(),"UUID"));
			Stats.setSwingAccuracy(playername,playeruuid,swingrate);

			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getWins(p));

		}

		if((int)getSwingHits(p)>0) {
			Stats.setSwingHits(playername,playeruuid,Stats.getSwingHits(playername,playeruuid,"UUID") +(int)getSwingHits(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getSwingHits(p));
		}
		if((int)getSwings(p)>0) {
			Stats.setSwingMisses(playername,playeruuid,Stats.getSwingsMisses(playername,playeruuid,"UUID") +(int)getSwings(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getSwings(p));
		}


		if((int)getBowMisses(p)>0) {
			Stats.setBowMisses(playername,playeruuid,Stats.getBowHits(playername,playeruuid,"UUID") + (int) getBowMisses(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getSwingHits(p));
		}
		if((int)getBowHits(p)>0) {
			Stats.setBowHits(playername,playeruuid,Stats.getBowHits(playername,playeruuid,"UUID") + (int) getBowHits(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getSwings(p));
		}


		if((int)getLosses(p)>0) {
			Stats.addLosses(playername,playeruuid,(int)getLosses(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getLosses(p));

		}
		if((int)getKills(p)>0) {
			Stats.addKills(playername,playeruuid,(int)getKills(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getKills(p));

		}
		if((int)getDeaths(p)>0) {
			Stats.addDeaths(playername,playeruuid,(int) getDeaths(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getDeaths(p) + " deaths");

		}
		if((int)getPlayed(p)>0) {
			Stats.addGamesPlayed(playername,playeruuid,(int) getPlayed(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getPlayed(p));

		}
		if((int)getStreak(p) > Stats.getKillstreak(playername,playeruuid,"UUID")){
			Stats.setKillstreak(playername,playeruuid,(int) getStreak(p));
			Bukkit.getLogger().log(Level.INFO,p.getName()+"updated with " + getStreak(p) + " killstreak");

		}
		remove(p);
	}

	public static Map<Player, Object> getStats(Player user){
		String PlayerName = user.getName();
		String UUID = user.getUniqueId().toString();

		SQL.connect();

		try
		{
			Statement statement = Main.sqlConnection.createStatement();
			ResultSet ServerSQLRAW = statement.executeQuery("SELECT * FROM `ffa_stats` WHERE `UUID` = '"+ UUID + "';");
			if(ServerSQLRAW.next()) {
				stats.put(user,"" + ServerSQLRAW.getInt("LOSSES"));
				stats.put(user,"" + ServerSQLRAW.getInt("WINS"));
				stats.put(user,"" + ServerSQLRAW.getInt("DEATHS"));
				stats.put(user,"" + ServerSQLRAW.getInt("KILLS"));
				stats.put(user,"" + ServerSQLRAW.getInt("WINRATE"));
				stats.put(user,"" + ServerSQLRAW.getInt("WINS"));
				stats.put(user,"" + ServerSQLRAW.getDouble("KDR"));
				stats.put(user,"" + ServerSQLRAW.getInt("POINTS"));
				stats.put(user,"" + ServerSQLRAW.getInt("DMGTAKEN"));
				stats.put(user, "" + ServerSQLRAW.getInt("PLAYED"));
				stats.put(user,"" + ServerSQLRAW.getInt("DMGGIVEN"));
				stats.put(user,"" + ServerSQLRAW.getInt("BOWACCURACY"));
				stats.put(user,"" + ServerSQLRAW.getInt("RANK"));
				stats.put(user,"" + ServerSQLRAW.getInt("ELO"));
				stats.put(user,"" + ServerSQLRAW.getInt("KILLSTREAK"));

			}
		}
		catch (SQLException error)
		{
			Bukkit.getLogger().warning("MySQL: SQLException Debug Command- " + error);
			String ConnectionRefusedError = error.toString();

			ConnectionRefusedError = ConnectionRefusedError.split(":")[0];

			Bukkit.getLogger().warning("Hey DEVS - " + ConnectionRefusedError);
			if (ConnectionRefusedError == "com.MySQL.jdbc.exceptions.jdbc4.Communications Exception") {
				Bukkit.getLogger().warning("CONNECTION REFUSED DETECTED!");
				stats.clear();
				stats = getStats(user);

			}
		}


		return stats;
	}

}



