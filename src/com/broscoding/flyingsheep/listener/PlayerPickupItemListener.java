package com.broscoding.flyingsheep.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.broscoding.flyingsheep.FlyingSheep;

public class PlayerPickupItemListener implements Listener {
	
	private FlyingSheep c;
	
	public PlayerPickupItemListener(FlyingSheep plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		c = plugin;
	}
	
	@EventHandler
	public void PlayerPickupItemEvent(PlayerPickupItemEvent e) {
		if (c.getVariables().unpickupableItems.contains(e.getItem())) e.setCancelled(true);
	}
}