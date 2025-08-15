package Commands;

import API.RandomKit;
import dev.tjxjnoobie.ffa.Main;
import info.techwizmatt.ServerCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import java.util.ArrayList;
import java.util.logging.Level;


public class Spec implements CommandExecutor, Listener {

	public static ArrayList<Player> list = new ArrayList<Player>();
	public static ArrayList<Player> abuse = new ArrayList<Player>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (Rank.getRankLevel(player)>=Rank.ConvertRankNameToInt("Mod")) {
			if(!abuse.contains(player)) {
				player.getInventory().setArmorContents(null);
				player.getInventory().clear();
				player.setAllowFlight(true);
				player.setFlying(true);
				list.add(player);
				abuse.add(player);
				sender.sendMessage(Main.getPrefix() + "§cYou are now a spectator");
				player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 100, true),
						false);
				player.addPotionEffect(
						new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1000, true), false);
				for (Player self : abuse) {
					self.hidePlayer(player);
				}
				for (Player playing : Main.playing) {
					playing.hidePlayer(player);
				}
				for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
				ap.hidePlayer(player);
				}
			}else{
				abuse.remove(player);
				list.remove(player);
				player.teleport(player.getWorld().getSpawnLocation());
				RandomKit.randomKit(player);
				player.setAllowFlight(false);
				player.setFlying(false);
				for (PotionEffect effects : player.getActivePotionEffects()) {
					player.removePotionEffect(effects.getType());
				}
				for (Player self : abuse) {
					self.showPlayer(player);
				}
				for (Player playing : Main.playing) {
					playing.showPlayer(player);
				}
				for (Player ap : Bukkit.getServer().getOnlinePlayers()) {
					ap.showPlayer(player);
				}
				sender.sendMessage(Main.getPrefix() + "§aYou are now a normal player");
			}


		}

		return false;
	}


	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		HumanEntity player = (Player) e.getWhoClicked();
		if (abuse.contains(player) && Rank.getRankLevel((Player) player) <= 8) {
			e.getCurrentItem().setType(Material.AIR);
			return;
		}
		Inventory inv2 = e.getInventory();
		if(inv2 instanceof AnvilInventory){
			e.setCancelled(true);

			InventoryView view = e.getView();
			int rawSlot = e.getRawSlot();

			if(rawSlot == view.convertSlot(rawSlot))
			{
				if(rawSlot == 2)
				{
					ItemStack item = e.getCurrentItem();
					if(item != null)
					{
						Bukkit.broadcastMessage("test");
					}
				}
			}
		}


		// check whether the event has been cancelled by another plugin
		if (!e.isCancelled()) {
			HumanEntity ent = e.getWhoClicked();

// not really necessary
			if (ent instanceof Player) {
				Player eplayer = (Player) ent;
				Inventory inv = e.getInventory();

// see if we are talking about an anvil here
				if (inv instanceof AnvilInventory) {
					AnvilInventory anvil = (AnvilInventory) inv;
					InventoryView view = e.getView();
					int rawSlot = e.getRawSlot();

// compare raw slot to the inventory view to make sure we are in the upper inventory
					if (rawSlot == view.convertSlot(rawSlot)) {
						// 2 = result slot
						if (rawSlot == 2) {
							// all three items in the anvil inventory
							ItemStack[] items = anvil.getContents();

// item in the left slot
							ItemStack item1 = items[0];

// item in the right slot
							ItemStack item2 = items[1];

// I do not know if this is necessary
							if (item1 != null && item2 != null) {
								int id1 = item1.getTypeId();
								int id2 = item2.getTypeId();

// if the player is repairing something the ids will be the same
								if (id1 != 0 && id1 == id2) {
									// item in the result slot
									ItemStack item3 = e.getCurrentItem();

// check if there is an item in the result slot
									if (item3 != null) {
										ItemMeta meta = item3.getItemMeta();

// meta data could be null
										if (meta != null) {
											// get the repairable interface to obtain the repair cost
											if (meta instanceof Repairable) {
												Repairable repairable = (Repairable) meta;
												int repairCost = repairable.getRepairCost();

// can the player afford to repair the item
												if (eplayer.getLevel() >= repairCost) {
													Bukkit.getLogger().log(Level.INFO,"SUCCESS");
// success
												} else {
// bugger
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}


