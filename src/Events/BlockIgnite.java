package Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import dev.tjxjnoobie.ffa.Main;

public class BlockIgnite implements Listener{
	
	
	
	@EventHandler
	public void onIgnite(final BlockIgniteEvent event) {

					if(event.getCause().equals(BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL)) {
				final Location loc = event.getBlock().getLocation();
				final ItemStack fire = new ItemStack(Material.FIRE);
				Block block = event.getBlock();
				 final Block ignited = block.getLocation().subtract(0.0D, 0.0D, 0.0D).getBlock();		
				final ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL, 1,
						(short) (event.getPlayer().getItemInHand().getDurability() + 16));
				event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(), flint);
				if (flint.getDurability() >= 64) {
					event.getBlock().setType(Material.FIRE);
					new BukkitRunnable() {
						public void run() {
						event.getPlayer().getInventory().removeItem(new ItemStack[] { flint });

							
						}
					}.runTaskTimer(Main.getPlugin(), 5L, 5L);
				}
					

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					
					public void run() {
						ignited.breakNaturally(fire);
						
					}
				}, 140L); 
					
						
	}
}
}
