package Events;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import dev.tjxjnoobie.ffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import static Events.BuildEvent.placed;

public class InteractWorkBench implements Listener {


	@EventHandler
	public void oncraft(PlayerInteractEvent e) {
		Action a = e.getAction();

		if (e.getPlayer().getItemInHand().getType() == Material.WORKBENCH
				&& e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			e.getPlayer().openWorkbench(e.getPlayer().getLocation(), true);
			return;
		}
		if (e.getPlayer().getItemInHand().getType() == Material.ENCHANTMENT_TABLE
				&& e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			e.getPlayer().openEnchanting(e.getPlayer().getLocation(), true);
			return;
		}

		for (BlockFace blockface : BlockFace.values()) {
			if (e.getPlayer().getItemInHand().getType() == Material.LAVA_BUCKET
					&& e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getRelative(blockface).getType() == Material.WATER) {
				e.setCancelled(true);
				return;
			}
			if (e.getPlayer().getItemInHand().getType() == Material.LAVA_BUCKET
					&& e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getRelative(blockface).getType() == Material.STATIONARY_WATER) {
				e.setCancelled(true);
				return;
			}
			if(e.getPlayer().getItemInHand().getType() == Material.LAVA_BUCKET || e.getPlayer().getItemInHand().getType() == Material.WATER_BUCKET ){
				new BukkitRunnable(){
					@Override
					public void run() {
						Block b = e.getClickedBlock().getRelative(BlockFace.UP);
						b.setType(Material.AIR);

					}
				}.runTaskLater(Main.getPlugin(),20*14);
				return;
			}




		}
			if (a == Action.LEFT_CLICK_BLOCK) {
			if (placed.containsValue(e.getClickedBlock().getLocation())) {
				if (e.getClickedBlock().getType() == Material.COBBLESTONE || e.getClickedBlock().getType() == Material.WOOD ||
						e.getClickedBlock().getType() == Material.WORKBENCH||e.getClickedBlock().getType() == Material.ANVIL||e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
					e.getClickedBlock().breakNaturally();
				}
			} else {
				return;
			}
		}


	}
}
