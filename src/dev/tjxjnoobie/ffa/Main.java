package dev.tjxjnoobie.ffa;


import API.*;
import Commands.*;
import Events.*;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener{

	public static String prefix = ChatColor.translateAlternateColorCodes('&', "&cFFA&f » &7");
	PluginManager pm = getServer().getPluginManager();
	static Plugin plugin;
	public static ArrayList<Player> playing = new ArrayList<>();
	public void onEnable() {
		plugin = this;
		Map.running = false;
		getWorldGuard();
		SQL.setStandardMySQL();
	    SQL.readMySQL();
        SQL.connect();
	    SQL.createTable();
		pm.registerEvents(new DeathEvent(), this);
		pm.registerEvents(new BuildEvent(),this);
		pm.registerEvents(new ChatEvent(), this);
		pm.registerEvents(new BlockIgnite(), this);
		pm.registerEvents(new BlockFade(), this);
		pm.registerEvents(new Quit(), this);
		pm.registerEvents(new Damage(), this);
		pm.registerEvents(new InteractWorkBench(), this);
		pm.registerEvents(new WeatherEvent(), this);
		pm.registerEvents(new Consume(), this);
		pm.registerEvents(new WorldChangeEvent(), this);
		pm.registerEvents(new FormEvent(), this);
		pm.registerEvents(new FoodLevelChange(), this);
		pm.registerEvents(new Launch(), this);
		pm.registerEvents(new StatsEvents(), this);
		pm.registerEvents(this, this);
		getCommand("createtables").setExecutor(new Tables());
		getCommand("stats").setExecutor(new StatsCMD());
		getCommand("build").setExecutor(new BuildEvent());
		getCommand("switchmap").setExecutor(new SwitchMap());
		getCommand("spec").setExecutor(new Spec());
		getCommand("debug").setExecutor(new Debug());
		getCommand("kt").setExecutor(new KillTop());
		getCommand("killtop").setExecutor(new KillTop());
		Utils.AddGoldenHead();
		Utils.AddGoldenApple();
		Map.runMapSwticher();
		Live.createServerID(Bukkit.getServer().getServerId());
		Live.updateMap(Map.maps.get(0),Bukkit.getServer().getServerId());
		if((GameIDSQLChecker.gameExist(Bukkit.getServer().getServerId()))) {
		    GameIDSQLChecker.setGameState(0,Bukkit.getServerId());
			}
	    if(!(GameIDSQLChecker.gameExist(Bukkit.getServer().getServerId()))) {
		    GameIDSQLChecker.createServerID(Bukkit.getServer().getServerId());
		    GameIDSQLChecker.setGameState(0,Bukkit.getServerId());
			}
			new BukkitRunnable(){
				@Override
				public void run() {
					if(Map.running == true){
						return;
					}
					if(Map.running == false){
						Map.runMapSwticher();
						return;
					}
				}
			}.runTaskTimer(this,20*5,20*5);
					new BukkitRunnable() {
			public void run() {
				int online = playing.size();
				if (online == 0) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
				}
			}

		}.runTaskTimer(this, 20 * 450, 20 * 450);
	}

	public void onDisable() {
	    GameIDSQLChecker.setGameState(2,Bukkit.getServerId());
		Stats.setRanks("ffa_stats","ELO");

		SQL.close();
	   
   }
	
	public static Plugin getPlugin() {
		return plugin;
	}
	
	

		
	


	 // Player Health
	    public static int getHealth(Player player) {
	    return (int)StrictMath.ceil(damageable(player).getHealth());
	    	}
	
	    public static Damageable damageable(Player player) {
	    	  return player;
	    	}
		public static String getPrefix() {
			return prefix;
			
		}



	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player player = e.getPlayer();
		final String playeruuid = player.getUniqueId().toString();
		final String playername = player.getName();
		Storage storage = new Storage();
		e.setJoinMessage(null);
		API.Stats.createPlayer(playername,playeruuid);
		player.setSaturation(200);
		player.setLevel(0);
		player.setHealth(20);
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.setNoDamageTicks(19);
		player.setGameMode(GameMode.SURVIVAL);
		RandomKit.randomKit(player);
		playing.add(player);
		storage.add(player);
		ScoreBoard.leadboard(player);
		ELO.createPlayer(playername,playeruuid);
		storage.addElo(player,ELO.getPlayerRating(playeruuid,"UUID"));

		player.teleport(Bukkit.getWorld("limbo").getSpawnLocation());
		player.sendMessage(Main.getPrefix()+"§aYou will be transferred to play in 5 seconds...");

		new BukkitRunnable(){
	@Override
	public void run() {
		if (Map.currentmap == 1) {
			player.teleport((Bukkit.getWorld("Distaria").getSpawnLocation()));

		}
		if (Map.currentmap == 2) {
			player.teleport((Bukkit.getWorld("Transylvania").getSpawnLocation()));

		}
		if (Map.currentmap == 3) {
			player.teleport((Bukkit.getWorld("Survival").getSpawnLocation()));

		}
	}
}.runTaskLater(Main.getPlugin(),20*5);


		for (World w : Bukkit.getServer().getWorlds()){
			w.setGameRuleValue("keepInventroy", "false");
			w.setGameRuleValue("doFireTick", "false");
			w.setGameRuleValue("naturalRegeneration", "false");
			w.setGameRuleValue("doTileDrops","true");
			w.setGameRuleValue("logAdminCommands","false");
		}

		}


	private WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {

			return null; // Maybe you want throw an exception instead
		}

		return (WorldGuardPlugin) plugin;

	}}




