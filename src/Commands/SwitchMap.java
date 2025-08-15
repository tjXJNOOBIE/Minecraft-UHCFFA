package Commands;

import API.Map;
import dev.tjxjnoobie.ffa.Main;
import info.techwizmatt.ServerCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by TJ on 3/25/2017.
 */
public class SwitchMap implements CommandExecutor{


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (Rank.getRankLevel(player) == 11) {
            sender.sendMessage(Main.getPrefix() + "The map will now change in 10 seconds.");
            Map.map = 10;
            Bukkit.broadcastMessage(Main.getPrefix() + "The map is being changed by " + ((Player) sender).getDisplayName());
        } else {
            sender.sendMessage(Main.getPrefix() + "§cYou must be a Developer to use this command.");
        }
            return false;
    }

    }
