package Events;

import API.Storage;
import Commands.Spec;
import dev.tjxjnoobie.ffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

import java.util.logging.Level;

public class Quit implements Listener{
	
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onQuit(PlayerQuitEvent e) {
		Main.playing.remove(e.getPlayer());
		e.setQuitMessage(null);
		Storage storage = new Storage();
		Player player = e.getPlayer();
		String playeruuid = e.getPlayer().getUniqueId().toString();
		String playername = e.getPlayer().getName();
		storage.update(player,playername,playeruuid);
		if(Damage.tag.containsKey(player)){
			Bukkit.getLogger().log(Level.INFO,playername +" Just combat logged");
		}
		for(PotionEffect effects :  player.getActivePotionEffects()){
			player.removePotionEffect(effects.getType());
		}
		Spec.abuse.remove(player);
		Spec.list.remove(player);


	}

}
