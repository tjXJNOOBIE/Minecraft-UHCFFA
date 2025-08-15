package Events;


import API.Storage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * Created by TJ on 4/1/2017.
 */
public class StatsEvents implements Listener {





    @EventHandler
    public void onMiss(ProjectileLaunchEvent e) {
        Player shooter = (Player) e.getEntity().getShooter();
        Storage storage = new Storage();
        if (shooter.getItemInHand().getType() == Material.BOW && e.getEntity() instanceof Arrow) {
            storage.addBowMiss(shooter);
        } else {

            return;
        }

    }

    @EventHandler
    public void onDmgHit(EntityDamageByEntityEvent e) {
        Storage storage = new Storage();
        if (e.getDamager() instanceof Player player) {
            Set<Material> validMaterials = EnumSet.of(
                Material.WOOD_AXE, Material.WOOD_SWORD,
                Material.STONE_AXE, Material.STONE_SWORD,
                Material.IRON_AXE, Material.IRON_SWORD,
                Material.DIAMOND_AXE, Material.DIAMOND_SWORD
            );

            if (validMaterials.contains(player.getItemInHand().getType())) {
                storage.addSwingHit(player);
                return;
            }
        } else if (e.getDamager() instanceof Arrow arrow && arrow.getShooter() instanceof Player shooter) {
            if (e.getEntity() instanceof Player victim) {
                storage.addBowHit(shooter);
            }
        }
    }





    @EventHandler
    public void onDmgMiss(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        Storage storage = new Storage();
    
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            Set<Material> validMaterials = EnumSet.of(
                Material.WOOD_AXE, Material.WOOD_SWORD,
                Material.STONE_AXE, Material.STONE_SWORD,
                Material.IRON_AXE, Material.IRON_SWORD,
                Material.GOLD_AXE, Material.GOLD_SWORD,
                Material.DIAMOND_AXE, Material.DIAMOND_SWORD
            );

            if (validMaterials.contains(player.getItemInHand().getType())) {
                storage.addSwing(player);
            }
        }
    }

    public static int getHealth(Player player) {
        return (int) StrictMath.ceil(damageable(player).getHealth());
    }
    public static Damageable damageable(Player player) {
        return player;
    }

}
