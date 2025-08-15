package Events;

import dev.tjxjnoobie.ffa.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by TJ on 2/17/2018.
 */
public class Launch implements Listener{


    ArrayList<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onthrow(ProjectileLaunchEvent e) {
        String prefix = Main.getPrefix();
        Player p = (Player) e.getEntity().getShooter();
        if(!(e.getEntity().getType() == EntityType.ENDER_PEARL)){
            return;
        }
        if (e.getEntity().getType() == EntityType.ENDER_PEARL && cooldown.contains(p)) {
            e.setCancelled(true);
            p.sendMessage(prefix+"§cYou must wait 15 seconds in between pearls");
            p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL,1));
            return;
        }

        if(e.getEntity().getType() == EntityType.ENDER_PEARL && !cooldown.contains(p)) {
            cooldown.add(p);
        }


        new BukkitRunnable(){
            @Override
            public void run() {
                cooldown.remove(p);
                p.sendMessage(prefix+"§aPearl Ready");
                p.playSound(p.getLocation(), Sound.NOTE_STICKS,1.0f,1.0f);
                cancel();
            }



        }.runTaskLater(Main.getPlugin(),20*15);

    }
}
