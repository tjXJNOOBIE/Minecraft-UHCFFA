package Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by TJ on 2/15/2018.
 */
public class FoodLevelChange implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        e.setCancelled(false);
    }



}
