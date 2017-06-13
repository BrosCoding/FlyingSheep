package com.broscoding.flyingsheep.variables;

import com.broscoding.flyingsheep.FlyingSheep;

public class Variables {
	
	private FlyingSheep c;
	
	public Variables(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void registerVariables() {
		updateReminder = c.getConfig().getBoolean("UpdateReminder");
		bungeeMode = c.getConfig().getBoolean("BungeeMode");
	}
	
	public void createShortcuts() {
		c.bungeeMode = bungeeMode;
	}
	
	public boolean updateReminder;
	public boolean bungeeMode;
}