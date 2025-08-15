package Events;

import API.Disguise;
import API.Stats;
import info.techwizmatt.ServerCore.API.Grade;
import info.techwizmatt.ServerCore.Rank.Rank;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatEvent implements Listener{
	
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		String playeruuid = player.getUniqueId().toString();
		int rank = Stats.getRank(playeruuid,"UUID");
		String playerrating = Grade.getGrade(playeruuid,"UUID");
		String chatcolor = "";
		chatcolor.replaceAll("&", "§");
		e.setCancelled(true);

	if(Disguise.isDisguised(player)) {
		TextComponent tc = new TextComponent("§8<§c"+"§c§oD+" +"§8> " +player.getDisplayName() + " §8» ");
		tc.addExtra(String.valueOf(tc.fromLegacyText(Rank.getChatColor(Rank.getRankLevel(player)) + e.getMessage())));

		tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/stats "+player.getName()));
		tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7§oRank: "+rank+ "\n§7§oClick to view stats for "+ player.getDisplayName()).create()));
		for(Player ap :Bukkit.getServer().getOnlinePlayers()) {
			ap.spigot().sendMessage(tc);
		}

	}else{

		TextComponent tc = new TextComponent("§8<§c"+playerrating +"§8> " + player.getDisplayName() + " §8» "+ Rank.getChatColor(Rank.getRankLevel(player)));
		tc.addExtra(Rank.getChatColor(Rank.getRankLevel(player)));

		tc.addExtra(e.getMessage());
		tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/stats "+player.getName()));
		tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7§oRank: "+rank+ "\n§7§oClick to view stats for "+ player.getDisplayName()).create()));
		for(Player ap : Bukkit.getServer().getOnlinePlayers()) {
			ap.spigot().sendMessage(tc);
		}


	}

	}
}




