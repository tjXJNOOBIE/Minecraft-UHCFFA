package Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * Created by TJ on 2/15/2018.
 */
public class BlockFade implements Listener {


    @EventHandler
    public void onFade(BlockFadeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPhysic(BlockFromToEvent event) {
        if (event.getBlock().getTypeId() == 31) //tall grass
        {
            Block b = (Block) event.getBlock();
            if (b.getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
                event.setCancelled(true);
                b.getDrops().clear();
                b.setType(Material.AIR);
            }
        }
        int id = event.getBlock().getTypeId();
        if(id == 10 || id == 11) {
            event.setCancelled(true);
        }
        if (((event.getBlock().getType()==Material.LAVA)||(event.getBlock().getType()==Material.WATER)||(event.getBlock().getType()==Material.STATIONARY_LAVA)||(event.getBlock().getType()==Material.STATIONARY_WATER))&&event.getToBlock().getType()==Material.REDSTONE_WIRE) {
            event.getToBlock().breakNaturally();
        }
    }
}

