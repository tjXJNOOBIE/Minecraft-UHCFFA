package API;

import info.techwizmatt.ServerCore.Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class Utils {


	public static String getRank(Player player) {
		if (Rank.getRankLevel(player) == 8) {
			return ChatColor.RED + "Mod";
		}
		if (Rank.getRankLevel(player) == 9) {
			return org.bukkit.ChatColor.DARK_RED + "SrMod";
		}
		if (Rank.getRankLevel(player) == 10) {
			return ChatColor.DARK_RED + "" + ChatColor.BOLD + "Admin";
		}
		if (Rank.getRankLevel(player) == 11) {
			return org.bukkit.ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Developer";
		}

		if (Rank.getRankLevel(player) == 3) {
			return org.bukkit.ChatColor.DARK_PURPLE + "VIP";
		}
		if (Rank.getRankLevel(player) == 7) {
			return ChatColor.LIGHT_PURPLE + "Sonder";
		}
		if (Rank.getRankLevel(player) == 6) {
			return ChatColor.BLUE + "Premium";
		}
		if (Rank.getRankLevel(player) == 5) {
			return ChatColor.GREEN + "Sponsor";
		}
		if (Rank.getRankLevel(player) == 1) {
			return ChatColor.DARK_GRAY + "Default";
		}

		return org.bukkit.ChatColor.AQUA + "Default";
	}
	public static void AddGoldenHead(){
		ItemStack GoldenHead = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta GoldenHeadMeta = GoldenHead.getItemMeta();
		GoldenHeadMeta.setDisplayName(ChatColor.MAGIC + "" + ChatColor.DARK_BLUE + "--" + ChatColor.RESET + "" + ChatColor.GOLD + "" + ChatColor.BOLD + "Golden Head" + "" + ChatColor.RESET + "" +ChatColor.MAGIC + "" + ChatColor.DARK_BLUE + "--");
		GoldenHead.setItemMeta(GoldenHeadMeta);

		MaterialData data = skull.getData();
		ShapedRecipe GoldHead = new ShapedRecipe(GoldenHead);

		GoldHead.shape("ggg","gsg","ggg");

		GoldHead.setIngredient('s', data);
		GoldHead.setIngredient('g', Material.GOLD_INGOT);




		Bukkit.getServer().addRecipe(GoldHead);
	}
	public static void AddGoldenApple(){
		ItemStack GoldenHead = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemMeta GoldenHeadMeta = GoldenHead.getItemMeta();
		GoldenHeadMeta.setDisplayName(ChatColor.AQUA + "Golden Apple");
		GoldenHead.setItemMeta(GoldenHeadMeta);

		ShapedRecipe GoldApple = new ShapedRecipe(GoldenHead);

		GoldApple.shape("ggg","gsg","ggg");

		GoldApple.setIngredient('s', Material.APPLE);
		GoldApple.setIngredient('g', Material.GOLD_INGOT);




		Bukkit.getServer().addRecipe(GoldApple);
	}
	public static int getHealth(Player player) {
		return (int) StrictMath.ceil(damageable(player).getHealth());
	}
	public static Damageable damageable(Player player) {
		return player;
	}

}





