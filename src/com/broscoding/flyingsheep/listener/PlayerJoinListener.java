package com.broscoding.flyingsheep.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.broscoding.flyingsheep.FlyingSheep;

public class PlayerJoinListener implements Listener {
	
	private FlyingSheep c;
	
	public PlayerJoinListener(FlyingSheep plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		c = plugin;
	}
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (c.getVariables().updateReminder == false) return;
		if (p.isOp() || p.hasPermission("ultramysterybox.*") || p.hasPermission("ultramysterybox.update")) {
			
			if (c.getUpdateReminder().getVersionFromSpigot() != c.getDescription().getVersion()) c.getUpdateReminder().sendFancyMessage(p);
		}
	}
}