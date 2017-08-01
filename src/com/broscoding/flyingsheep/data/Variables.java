package com.broscoding.flyingsheep.data;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.FlyingSheep;

public class Variables {
	
	private FlyingSheep c;
	
	public Variables(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void registerVariables() {
		updateReminder = c.getConfig().getBoolean("UpdateReminder");
		bungeeMode = c.getConfig().getBoolean("BungeeMode");
		spectatorMode = c.getConfig().getBoolean("Spectator-Mode");
		fileBackend = c.getConfig().getString("Backends.file.Directory");
		fallbackServer = c.getConfig().getString("Fallback-Server");
		fullJoin = c.getConfig().getBoolean("Full-Join-Enabled");
		spectatorFlightLimit = c.getConfig().getInt("Spectator-Flight-Limit");
	}
	
	public void createShortcuts() {
		c.bungeeMode = bungeeMode;
	}
	
	public boolean updateReminder;
	public boolean bungeeMode;
	public boolean spectatorMode;
	public boolean fullJoin;
	
	public String fallbackServer;
	public String fileBackend;
	
	public int spectatorFlightLimit;
	
	public HashMap<Player, Location> joinLocation = new HashMap<Player, Location>();
	
	public ArrayList<Item> unpickupableItems = new ArrayList<Item>();
}