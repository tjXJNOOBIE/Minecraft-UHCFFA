package Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import dev.tjxjnoobie.ffa.Main;
import info.techwizmatt.ServerCore.Rank.Rank;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BuildEvent implements Listener, CommandExecutor {
	
	ArrayList<Player> block = new ArrayList<Player>();
	public static HashMap<Block, Location> placed = new HashMap<>();
	public static Map<Block, ItemStack> anvil = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		Player p = (Player) sender;
		
		if (Rank.getRankLevel(p)>=10) {
			p.sendMessage(ChatColor.RED + "You do not have permission for this.");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("build")) {
			if(!block.contains(p)) {
			p.sendMessage(ChatColor.GREEN + "You are now able to build");
			block.add(p);	
			}
		  if (block.contains(p.getName())) {
			p.sendMessage(ChatColor.RED + "You have disabled build mode.");
			block.remove(p);
			return true;
		}	
		}
		return false;
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();

		if(event.getPlayer().getItemInHand().getType() == Material.WORKBENCH
				) {
			event.setCancelled(true);
			return;
		}
		if(event.getBlockPlaced().getType() == Material.WOOD||
				event.getBlockPlaced().getType() == Material.COBBLESTONE ){
			int y = (int) p.getLocation().getY();
			if(y > 140){
				p.sendMessage(Main.getPrefix() + "§cYou can't build at this height");
				event.setCancelled(true);
				return;
			}
			if(p.getItemInHand().getType() == Material.WATER_BUCKET || p.getItemInHand().getType() == Material.LAVA_BUCKET){
				if(!WorldGuardPlugin.inst().canBuild(p,p.getLocation())){
					event.getPlayer().sendMessage(Main.getPrefix()+"§cYou can't place liquids here");
					return;
				}
			}
			if(!WorldGuardPlugin.inst().canBuild(p,p.getLocation())){
				event.setCancelled(true);
				p.sendMessage(Main.getPrefix() + "§cYou can't build here");
				return;
			}

			event.setCancelled(false);
			placed.put(event.getBlockPlaced(),event.getBlockPlaced().getLocation());

			new BukkitRunnable(){
				@Override
				public void run() {
					event.getBlock().breakNaturally();
					if(placed.containsKey(event.getBlock())){
						placed.remove(event.getBlock());
					}
				}
			}.runTaskLater(Main.getPlugin(),20*7);
			return;
		}
		if(event.getPlayer().getItemInHand().getType() == Material.ANVIL||event.getPlayer().getItemInHand().getType() == Material.ENCHANTMENT_TABLE){
			placed.put(event.getBlockPlaced(),event.getBlockPlaced().getLocation());
			p.sendMessage(Main.getPrefix()+"You have 30 seconds to use your utility.");
			new BukkitRunnable(){
				@Override
				public void run() {
					event.getBlock().breakNaturally();
					if(placed.containsKey(event.getBlock())){
						placed.remove(event.getBlock());
					}

					p.getInventory().addItem(anvil.get(event.getBlockPlaced()));
				}
			}.runTaskLater(Main.getPlugin(),20*30);
			return;
		}

		if(p.getItemInHand().getType() == Material.FLINT_AND_STEEL) {
			event.setCancelled(false);
		}else{
			event.setCancelled(true);
			return;
		}


	}
	

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player p = event.getPlayer();
		if(p.getItemInHand().getType() == Material.WATER_BUCKET || p.getItemInHand().getType() == Material.LAVA_BUCKET){
			if(!WorldGuardPlugin.inst().canBuild(p,p.getLocation())){
				event.getPlayer().sendMessage(Main.getPrefix()+"§cYou can't place liquids here");
			}
		}
		if(!WorldGuardPlugin.inst().canBuild(p,p.getLocation())){
			event.setCancelled(true);
			p.sendMessage(Main.getPrefix() + "§cYou can't build here");
			return;
		}
		if(p.getItemInHand().getType() == Material.FLINT_AND_STEEL) {
			event.setCancelled(false);
		}else{
			event.setCancelled(true);
			return;
		}

	}
	@EventHandler
	public void onDrop(PlayerBucketEmptyEvent e) {
		Player p = e.getPlayer();
		if(e.getBucket() == Material.WATER_BUCKET || e.getBucket() == Material.LAVA_BUCKET){
			if(!WorldGuardPlugin.inst().canBuild(p,p.getLocation())){
				e.setCancelled(true);
				e.getPlayer().sendMessage(Main.getPrefix()+"§cYou can't place liquids here");
			}
		}

	}

}
