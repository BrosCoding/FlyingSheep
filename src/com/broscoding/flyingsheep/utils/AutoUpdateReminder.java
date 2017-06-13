package com.broscoding.flyingsheep.utils;

import com.broscoding.flyingsheep.FlyingSheep;

public class AutoUpdateReminder implements Runnable {
	
	private FlyingSheep c;
	
	public AutoUpdateReminder(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void run() {
		if (c.getVariables().updateReminder && c.getUpdateReminder().getVersionFromSpigot() != c.getDescription().getVersion()) 
			c.getUpdateReminder().sendConsoleMessage(c.getServer().getConsoleSender());
	}
}