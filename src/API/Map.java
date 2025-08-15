package API;

import dev.tjxjnoobie.ffa.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;
import java.util.List;

public abstract class Map implements EventHandler {

	public static int map = 600;
	public static int currentmap = 1;
	public static List<String> maps = new ArrayList<String>();
	public static List<World> currentmaps = new ArrayList<World>();
	public static boolean running = false;

	public static void runMapSwticher() {
		map = 600;
		maps.add("Distaria");
		maps.add("Transylvania");
		maps.add("Survival");
		running = true;

		new BukkitRunnable() {

			public void run() {
				if (map >= 0) {
					map -= 1;
				}

				if (map <= 0) {
					cancel();
				}
				if (map == 300) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will be changed in §c5§7 minutes.");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 120) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will be changed in §c2§7 minutes.");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 60) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will be changed in §c1§7 minute.");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 30) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will be changed in §c30§7 seconds.");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 10) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will change in §c10§7 seconds");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}

				}
				if (map == 5) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will change in §c5§7 seconds");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 4) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will change in §c4§7 seconds");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 3) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will change in §c3§7 seconds");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 2) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will change in §c2§7 seconds");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 1) {
					Bukkit.broadcastMessage(Main.getPrefix() + "The map will change in §c1§7 second");
					for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
						ap.playSound(ap.getLocation(), Sound.CLICK,1.0f,1.0f);
					}
				}
				if (map == 0) {
					Bukkit.broadcastMessage(Main.getPrefix() + "§6The map is now changing...");
					running = false;
					if (currentmap == 1) {
						loadMap(maps.get(1));
						currentmap = 2;
						Live.updateMap(maps.get(1),Bukkit.getServerId());
						for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
							ap.teleport(Bukkit.getWorld(maps.get(1)).getSpawnLocation());
						}
						return;
					}
					if (currentmap == 2) {
						loadMap(maps.get(2));
						currentmap = 3;
						Live.updateMap(maps.get(2),Bukkit.getServerId());

						for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
							ap.teleport(Bukkit.getWorld(maps.get(2)).getSpawnLocation());
						}
						return;
					}
					if (currentmap == 3) {
						loadMap(maps.get(0));
						currentmap = 1;
						Live.updateMap(maps.get(0),Bukkit.getServerId());

						for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
							ap.teleport(Bukkit.getWorld(maps.get(0)).getSpawnLocation());
						}
						return;
					}

				}
			}


		}.runTaskTimer(Main.getPlugin(), 20, 20);
	}

	public static void unloadMap() {
		for (World loadedmaps : Bukkit.getServer().getWorlds()) {
			Bukkit.getServer().unloadWorld(loadedmaps, false);
			return;
		}
	}

	public static void unloadMap(String map) {
		Bukkit.getServer().unloadWorld(map, false);
	}

	public static String loadMap(String map) {

		Bukkit.getServer().createWorld(new WorldCreator(map));


		return map;

	}

	public static void swtichMap(Player allplayers, World world) {
		allplayers.teleport(world.getSpawnLocation());

	}



}


