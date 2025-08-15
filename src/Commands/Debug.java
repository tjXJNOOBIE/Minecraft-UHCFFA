package Commands;

import API.Map;
import API.Storage;
import dev.tjxjnoobie.ffa.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by TJ on 3/24/2017.
 */
public class Debug implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Storage storage = new Storage();
        Player player = (Player)sender;


        sender.sendMessage(ChatColor.ITALIC+"Information for current local game...");
        sender.sendMessage(Main.getPrefix()+"Kills: " + storage.getKills(player));
        sender.sendMessage(Main.getPrefix()+"KillStreak: " + storage.getStreak(player));
        sender.sendMessage(Main.getPrefix()+"Deaths: " + storage.getDeaths(player));
        sender.sendMessage(Main.getPrefix() + "Bow Shots: " + storage.getBowMisses(player));
        sender.sendMessage(Main.getPrefix() + "Bow Hits: " + storage.getBowHits(player));
        sender.sendMessage(Main.getPrefix() + "Sword Swings: " + storage.getSwings(player));
        sender.sendMessage(Main.getPrefix() + "Sword Hits: " + storage.getSwingHits(player));
        sender.sendMessage(Main.getPrefix() + "ELO: " + storage.getElo(player));
        sender.sendMessage(Main.getPrefix() + "Damage Taken: " + storage.getDmgTaken(player));
        sender.sendMessage(Main.getPrefix() + "Damage Given: " + storage.getDmgGiven(player));
        sender.sendMessage(Main.getPrefix() + "Map Rotation: " + Map.currentmap);
        sender.sendMessage(Main.getPrefix() +"Maps: " + Map.currentmaps);
        return false;
    }

}
