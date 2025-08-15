package Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockSpreadEvent;

import java.util.logging.Level;

/**
 * Created by TJ on 2/15/2018.
 */
public class FormEvent implements Listener {


    @EventHandler
    public void onForm(BlockFromToEvent e) {


        if(e.getToBlock().getType() == Material.OBSIDIAN){
            e.getToBlock().setType(Material.AIR);
            e.setCancelled(true);
        }
        if(e.getBlock().getType() == Material.WATER){
            e.setCancelled(true);
        }
    }
}
