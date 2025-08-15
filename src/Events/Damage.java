package Events;

import java.util.HashMap;

import API.Storage;
import API.Utils;
import dev.tjxjnoobie.ffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener{
	
	public static HashMap<Player, Player> tag = new HashMap<Player, Player>();

	 @EventHandler
	  public void onDamage(EntityDamageEvent event)
	  {
	    if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
	      event.setCancelled(true);
	    }
	  }
	   @EventHandler
       public void onEntityDamage(EntityDamageByEntityEvent e) {
           Storage storage = new Storage();
               if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                       Player hit = (Player) e.getEntity();
                       Player damager = (Player) e.getDamager();
                      
                       if (tag.get(hit) != null) {
                               if (tag.get(hit) != damager) ;
                               return;
                       }
                       else {
                               tag.put(hit, damager);
                       }
               }
           if ((e.getDamager() instanceof Arrow)) {
               Arrow a = (Arrow) e.getDamager();
               if ((a.getShooter() instanceof Player)) {
                   a.getShooter();
                   Player p = (Player) a.getShooter();

                   Damageable dp = (Damageable) e.getEntity();
                   if ((dp instanceof Player)) {
                       Player v = (Player) dp;
                       double distance = a.getLocation().distance(p.getLocation());
                       double ptviev = dp.getHealth();
                       Integer damage = Integer.valueOf((int) e.getFinalDamage());
                       if ((!dp.isDead())) {
                               storage.addBowHit(p);
                               p.sendMessage( v.getDisplayName() + " §7has §c§l " + Utils.getHealth(v) / 2 + " ❤§7's remaining");
                           }
                           if(distance >= 35&&v.isDead()){
                               p.sendMessage(Main.getPrefix() +"§c§lLongshot§f(§a"+distance+" §7Blocks§f)§8: §7You've received §a10§7 coins");
                               p.playSound(p.getLocation(), Sound.EXPLODE,1.0f,1.0f);
                           }

                       }
                   }
               }
           }

       }
	

