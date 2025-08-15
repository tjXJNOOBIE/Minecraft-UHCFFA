package Commands;


import API.Storage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

/**
 * Created by TJ on 1/7/2018.
 */
public class KillTop implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.LIGHT_PURPLE + "          Top 10          ");


            class ValueComparator implements Comparator<String> {

                Map<String,Integer> base;

                public ValueComparator(Map map) {
                    this.base = map;
                }

                public int compare(String a, String b) {
                    if (base.get(a) >= base.get(b)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }

            Storage storage = new Storage();
            ValueComparator bvc = new ValueComparator(Storage.kills);
            TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
            sorted_map.putAll(Storage.kills);
            Bukkit.getLogger().log(Level.INFO,"First: "+ sorted_map.get(0));
            for (int i = 1; i < 11; i++) {
                Map.Entry<String, Integer> e = sorted_map.pollFirstEntry();
                String pname = e.getKey();
                int score = e.getValue();
                player.sendMessage("§7#"+i + ChatColor.BOLD+" "+ ChatColor.GRAY + pname + " §7▏ " + ChatColor.GREEN + score);
            }

        }
        return false;
    }
}
