package Commands;

import info.techwizmatt.ServerCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import API.SQL;
import org.bukkit.entity.Player;

import java.util.logging.Level;


public class Tables implements CommandExecutor {
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if((Rank.getRankLevel(player)==11)) {
		SQL.createTable();
		Bukkit.getLogger().log(Level.INFO,"CREATED TABLES");
		}
		
		
		return false;
	}
}

	
	


