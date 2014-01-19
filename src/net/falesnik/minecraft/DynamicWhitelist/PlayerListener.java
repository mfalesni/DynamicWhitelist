package net.falesnik.minecraft.DynamicWhitelist;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerListener implements Listener {

	private final DynamicWhitelist plugin;    

    public PlayerListener(DynamicWhitelist instance) {
        plugin = instance;        
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        String playerName = event.getPlayer().getName();
        WhitelistResponse response = plugin.checkWhitelisted(playerName);
        // Kick
        if(!response.canGoIn())
        {
        	plugin.logInfo("Player " + playerName + " is not allowed to join!");
        	event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, response.getMessage());
        }
        else
        {
        	plugin.logInfo("Player " + playerName + " was permitted to join!");
        }
    }
}
