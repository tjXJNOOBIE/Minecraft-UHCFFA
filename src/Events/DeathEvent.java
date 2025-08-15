package Events;

import API.*;
import dev.tjxjnoobie.ffa.Main;
import info.techwizmatt.ServerCore.API.CoinAPI;
import info.techwizmatt.ServerCore.API.Grade;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Level;


public class DeathEvent implements Listener {


	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent e) {
		final Player p = e.getEntity().getPlayer();
		Player k = e.getEntity().getKiller();
		World w = p.getWorld();
		String playeruuid = p.getUniqueId().toString();
		String playername = p.getName();
		if (!(k instanceof Player)) {
			e.getDrops().clear();
			e.getDrops().add(RandomDrop.randomDrop());
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setLevel(0);
			p.setExhaustion(0);
			p.setFireTicks(0);
			e.setDeathMessage(p.getDisplayName() + ChatColor.RED + " died");
			new BukkitRunnable() {
				@Override
				public void run() {
					RandomKit.randomKit(p);
					p.teleport(p.getWorld().getSpawnLocation());
					p.setHealth(20);
					p.setLevel(0);
					p.setFireTicks(0);
					p.removePotionEffect(PotionEffectType.ABSORPTION);
				}
			}.runTaskLater(Main.getPlugin(), 5L);
			return;
		}
		int take = 1;
		int take2 = 2;
		int take3 = 3;
		int take4 = 4;
		int take5 = 5;
		int stolenrating = ELO.getPlayerRating(playeruuid, "UUID") * take / 100;
		int stolenrating2 = ELO.getPlayerRating(playeruuid, "UUID") * take2 / 100;
		int stolenrating3 = ELO.getPlayerRating(playeruuid, "UUID") * take3 / 100;
		int stolenrating4 = ELO.getPlayerRating(playeruuid, "UUID") * take4 / 100;
		int stolenrating5 = ELO.getPlayerRating(playeruuid, "UUID") * take5 / 100;
		int getstolen = stolenrating;
		int getstolen2 = stolenrating2;
		int getstolen3 = stolenrating3;
		int getstolen4 = stolenrating4;
		int getstolen5 = stolenrating5;
		int difference = Grade.getGradePoints(k.getUniqueId().toString(), "UUID") - Grade.getGradePoints(p.getUniqueId().toString(), "UUID");
		Storage storage = new Storage();

		double rawkillerhealth = Main.getHealth(k) / 2.5;
		double killerhealth = (Math.round(rawkillerhealth * 100d) / 100d);

		e.getDrops().add(RandomDrop.randomDrop());
		e.getDrops().remove(new ItemStack(Material.ANVIL));
		e.getDrops().remove(new ItemStack(Material.ENCHANTMENT_TABLE));
		e.getDrops().remove(new ItemStack(Material.WORKBENCH));
		p.setHealth(20);
		p.setFoodLevel(20);
		p.setExhaustion(0);
		p.setFireTicks(0);
		p.setLevel(0);
		Bukkit.getLogger().log(Level.INFO,"DEBUG: ELO DIFFERENCE " + difference);
		k.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 140, 2));
		k.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 4));
		k.playSound(k.getLocation(), Sound.AMBIENCE_THUNDER, 10, 10);
		p.sendMessage(Main.getPrefix() + "Your killer had §c" + killerhealth + " ❤ ");
		storage.addKill(k);
		storage.addDeaths(p);
		storage.addStreak(k);
		storage.resetStreaks(p);
		SQL.connect();

		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				RandomKit.randomKit(p);
				p.teleport(p.getWorld().getSpawnLocation());
				p.setHealth(20);
				p.setExhaustion(0);
				p.setLevel(0);
				p.setFireTicks(0);
				p.removePotionEffect(PotionEffectType.ABSORPTION);
				p.removePotionEffect(PotionEffectType.REGENERATION);


			}
		}, 5L);

		if ((int) storage.getStreak(k) >= 5 && k.isDead()) {
			Bukkit.broadcastMessage(Main.getPrefix() + p.getDisplayName()+" §chas broken " + k.getDisplayName() +"'s "+ storage.getStreak(k) + "§7 kill streak!");
			w.playEffect(k.getLocation(),Effect.BLAZE_SHOOT,1);
			w.playSound(k.getLocation(),Sound.CREEPER_DEATH,1.0f,1.0f);
		}

		if ((int) storage.getStreak(k) == 5) {
			Bukkit.broadcastMessage(Main.getPrefix() + k.getDisplayName() + " §7is on a kill streak of §e5§7!");
			w.playSound(k.getLocation(), Sound.FIREWORK_BLAST, 1.0F, 1.0F);
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e3§8] §ccoin for going on a §8[§e5§8]§c kill streak!");
			CoinAPI.AddTokens(k, 3);
		}
		if ((int) storage.getStreak(k) == 10) {
			Bukkit.broadcastMessage(Main.getPrefix() + k.getDisplayName() + " §7is on a kill streak of §e§l10§7!");
			w.playSound(k.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1.0F, 1.0F);
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e4§8] §ccoins for going on a §8[§e10§8]§c kill streak!");
			CoinAPI.AddTokens(k, 4);
		}
		if ((int) storage.getStreak(k) == 15) {
			Bukkit.broadcastMessage(Main.getPrefix() + k.getDisplayName() + " §7is on a kill streak of §615§7!");
			w.playSound(k.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e6§8] §ccoins for going on a §8[§e15§8]§c kill streak!");
			CoinAPI.AddTokens(k, 6);
		}
		if ((int) storage.getStreak(k) == 20) {
			Bukkit.broadcastMessage(Main.getPrefix() + k.getDisplayName() + " §7is on a kill streak of §6§l20§7!");
			w.playSound(k.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0F, 1.0F);
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e10§8] §ccoins for going on a §8[§e20§8]§c kill streak!");
			CoinAPI.AddTokens(k, 10);
		}
		if ((int) storage.getStreak(k) == 25) {
			Bukkit.broadcastMessage(Main.getPrefix() + k.getDisplayName() + " §7is on a kill streak of §c25§7!");
			w.playSound(k.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e14§8] §ccoins for going on a §8[§e25§8]§c kill streak!");
			CoinAPI.AddTokens(k, 14);
		}
		if ((int) storage.getStreak(k) == 30) {
			Bukkit.broadcastMessage(Main.getPrefix() + k.getDisplayName() + " §7is on a kill streak of §c§l30§7!");
			w.playSound(k.getLocation(), Sound.WOLF_HOWL, 1.0F, 1.0F);
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e23§8] §ccoins for going on a §8[§e30§8]§c kill streak!");
			CoinAPI.AddTokens(k, 23);
		}
		if ((int) storage.getStreak(k) == 35) {
			Bukkit.broadcastMessage(Main.getPrefix() + k.getDisplayName() + " §7is on a kill streak of §435§7!");
			w.playSound(k.getLocation(), Sound.WITHER_DEATH, 1.0F, 1.0F);
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e35§8] §ccoins for going on a §8[§e35§8]§c kill streak!");
			CoinAPI.AddTokens(k, 35);

		}
		if ((int) storage.getStreak(k) == 40) {
			Bukkit.broadcastMessage(Main.getPrefix() + k.getDisplayName() + " §7is on a kill streak of §4§l40§7!");
			w.playSound(k.getLocation(), Sound.ENDERDRAGON_DEATH, 1.0F, 1.0F);
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e40§8] §ccoins for going on a §8[§e40§8]§c kill streak!");
			CoinAPI.AddTokens(k, 40);
		}
		if ((int) storage.getStreak(k) > 40) {
			k.sendMessage(Main.getPrefix() + "§cYou've gained §8[§e" + (int) storage.getStreak(k) + "§8] §ccoins for going on a §8[§e" + storage.getStreak(p) + "§8]§c kill streak!");
			CoinAPI.AddTokens(k, (int) storage.getStreak(k));

		}

			storage.addElo(k, getstolen);
			p.sendMessage(Main.getPrefix() + "             §b§lYour Rating §7            ");
			int newelo = ((int)storage.getElo(p)-getstolen);
			p.sendMessage(Main.getPrefix() + "§c" + (int)storage.getElo(p) + " §7→§a " +newelo);
			p.sendMessage(Main.getPrefix() + "You lost §a" + (int) (Math.round(getstolen * 100d) / 100d)+ " §7rating");

			p.sendMessage(" ");

			storage.addElo(p,-getstolen);

			p.sendMessage(Main.getPrefix() + "             "+k.getDisplayName()+"§b§l's Rating §7            ");

			int killernewelo = (int)storage.getElo(k);
			p.sendMessage(Main.getPrefix() + "§c" + (int)storage.getElo(k) + " §7→§a " +killernewelo);
			k.sendMessage(Main.getPrefix() + "You've gained §a" + getstolen+ " §7rating for killing §c" + p.getDisplayName() + "§8!");
			e.setDeathMessage(Main.getPrefix() + "§a" + k.getName() + " §4§lX§c " + playername);
			ELO.addRating(k.getName(), k.getUniqueId().toString(), getstolen);
			storage.addElo(k, getstolen);


		if ((Stats.getStat(playeruuid, "UUID", "DEATHS", "ffa_stats") >= 1)) {
			double kdr = Stats.getStat(playeruuid, "UUID", "KILLS", "ffa_stats") / Stats.getStat(playeruuid, "UUID", "DEATHS", "ffa_stats");
			Stats.setKDR(playername, playeruuid, kdr);


		}



	}



}



