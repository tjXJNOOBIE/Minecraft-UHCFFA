package Events;

import API.RandomKit;
import dev.tjxjnoobie.ffa.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

/**
 * Created by TJ on 2/15/2018.
 */
public class WorldChangeEvent implements Listener {


    @EventHandler
    public void onTeleport(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        RandomKit.randomKit(player);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setLevel(0);
        player.sendMessage(Main.getPrefix() + "Now playing: "+ player.getWorld().getName().toString());

    }
}
