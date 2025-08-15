package Commands;

import API.Stats;
import info.techwizmatt.ServerCore.API.Grade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.tjxjnoobie.ffa.Main;

public class StatsCMD implements CommandExecutor {



	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){

		Player p = (Player)sender;

		if (cmd.getName().equalsIgnoreCase("stats")){
			if (args.length == 0) {
				String playerusername = p.getName();
				String playeruuid = p.getUniqueId().toString();
				sender.sendMessage(Main.prefix + p.getDisplayName() + " §7's §7Records");
				sender.sendMessage(Main.prefix+ "Rank§8: §f#§a" + Stats.getRank(playeruuid,"UUID"));
				if(Grade.getPlacementMatches(playeruuid,"UUID") > 0) {
					sender.sendMessage(Main.prefix + "Grade§8: §7You have §a" + Grade.getPlacementMatches(playeruuid, "UUID") + "§7 placements");
				}else{
					sender.sendMessage(Main.prefix+"Grade§8: " +Grade.getGrade(playeruuid,"UUID"));
				}
				sender.sendMessage(Main.prefix+"ELO§8: " + Stats.getStat(playeruuid,"UUID","ELO","ffa_stats"));
				sender.sendMessage(Main.prefix  + "Bow Shots§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getStat(playeruuid, "UUID","BOWHITS","ffa_stats") + "§8/§c" + Stats.getStat(playeruuid, "UUID","BOWMISSES","ffa_stats") + "§8/§7" + Stats.getStat(playeruuid, "UUID","BOWACCURACY","ffa_stats") + "%");
				sender.sendMessage(Main.prefix  + "Swings§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getStat(playeruuid, "UUID","SWINGHITS","ffa_stats") + "§8/§c" + Stats.getStat(playeruuid, "UUID","SWINGMISSES","ffa_stats") + "§8/§7" + Stats.getStat(playeruuid, "UUID","SWINGACCURACY","ffa_stats") + "%");
				sender.sendMessage(Main.prefix  + "Kills§8:§f (§aKills§8/§cDeaths§8/§7KDR§f)§8:§a " + +Stats.getStat(playeruuid, "UUID","KILLS","ffa_stats") + "§7/§c" + Stats.getStat(playeruuid, "UUID","DEATHS","ffa_stats") + "§7/§7" + Stats.getStat(playeruuid, "UUID","KDR","ffa_stats"));
				return true;
			}


			if (args.length == 1) {
				Player tp = Bukkit.getPlayer(args[0]);
				if (tp != null) {
					String tplayerusername = args[0];
					String tplayeruuid = tp.getUniqueId().toString();
					sender.sendMessage(Main.prefix  + tp.getDisplayName() + " §7's §7Records");
					sender.sendMessage(Main.prefix+ "Rank§8: §f#§a" + Stats.getRank(tplayeruuid,"UUID"));
					if(Grade.getPlacementMatches(tplayeruuid,"UUID") > 0) {
						sender.sendMessage(Main.prefix + "Grade§8: §c This user has §a" + Grade.getPlacementMatches(tplayeruuid, "UUID") + "§c placements");
					}else{
						sender.sendMessage(Main.prefix+"Grade§8: " +Grade.getGrade(tplayeruuid,"UUID"));
					}
					sender.sendMessage(Main.prefix+"ELO§8: " + Stats.getStat(tplayeruuid,"UUID","ELO","ffa_stats"));
					sender.sendMessage(Main.prefix  + "Bow Shots§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getStat(tplayeruuid, "UUID","BOWHITS","ffa_stats") + "§8/§c" + Stats.getStat(tplayeruuid, "UUID","BOWMISSES","ffa_stats") + "§8/§7" + Stats.getStat(tplayeruuid, "UUID","BOWACCURACY","ffa_stats") + "%");
					sender.sendMessage(Main.prefix  + "Swings§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getStat(tplayeruuid, "UUID","SWINGHITS","ffa_stats") + "§8/§c" + Stats.getStat(tplayeruuid, "UUID","SWINGMISSES","ffa_stats") + "§8/§7" + Stats.getStat(tplayeruuid, "UUID","SWINGACCURACY","ffa_stats") + "%");
					sender.sendMessage(Main.prefix  + "Kills§8:§f (§aKills§8/§cDeaths§8/§7KDR§f)§8:§a " + +Stats.getStat(tplayeruuid, "UUID","KILLS","ffa_stats") + "§7/§c" + Stats.getStat(tplayeruuid, "UUID","DEATHS","ffa_stats") + "§7/§7" + Stats.getStat(tplayeruuid, "UUID","KDR","ffa_stats"));

				} else if(!Stats.OfflinePlayerExists(args[0])){
					sender.sendMessage(Main.prefix + ChatColor.RED+"That player has never played §5Sonder UHCFFA");
					return true;
				} else if(Stats.OfflinePlayerExists(args[0])){
					sender.sendMessage(Main.prefix + args[0] + " §7's §7Records");
					sender.sendMessage(Main.prefix+ "Rank§8: §f#§a" + Stats.getRank(args[0],"USERNAME"));

					if(Grade.getPlacementMatches(args[0],"USERNAME") > 0) {
						sender.sendMessage(Main.prefix + "Grade§8: §a This user has " + Grade.getPlacementMatches(args[0], "USERNAME") + "§7 placements");
					}else{
						sender.sendMessage(Main.prefix+"Grade§8: " + Grade.getGrade(args[0],"USERNAME"));
					}
					sender.sendMessage(Main.prefix+"ELO§8: " + Stats.getStat(args[0],"USERNAME","ELO","ffa_stats"));

					sender.sendMessage(Main.prefix  + "Bow Shots§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getStat(args[0], "USERNAME","BOWHITS","ffa_stats") + "§8/§c" + Stats.getStat(args[0], "USERNAME","BOWMISSES","ffa_stats") + "§8/§7" + Stats.getStat(args[0], "USERNAME","BOWACCURACY","ffa_stats") + "%");
					sender.sendMessage(Main.prefix  + "Swings§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getStat(args[0], "USERNAME","SWINGHITS","ffa_stats") + "§8/§c" + Stats.getStat(args[0], "USERNAME","SWINGMISSES","ffa_stats") + "§8/§7" + Stats.getStat(args[0], "USERNAME","SWINGACCURACY","ffa_stats") + "%");
					sender.sendMessage(Main.prefix  + "Kills§8:§f (§aKills§8/§cDeaths§8/§7KDR§f)§8:§a " + +Stats.getStat(args[0], "USERNAME","KILLS","ffa_stats") + "§7/§c" + Stats.getStat(args[0], "USERNAME","DEATHS","ffa_stats") + "§7/§7" + Stats.getStat(args[0], "USERNAME","KDR","ffa_stats"));


				}
			}





			return false;

		}
		return false;


	}
}

