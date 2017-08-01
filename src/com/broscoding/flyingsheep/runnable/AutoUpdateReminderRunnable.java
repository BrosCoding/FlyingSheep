package com.broscoding.flyingsheep.runnable;

import com.broscoding.flyingsheep.FlyingSheep;

public class AutoUpdateReminderRunnable implements Runnable {
	
	private FlyingSheep c;
	
	public AutoUpdateReminderRunnable(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void run() {
		if (c.getVariables().updateReminder && c.getUpdateReminder().getVersionFromSpigot() != c.getDescription().getVersion()) 
			c.getUpdateReminder().sendConsoleMessage(c.getServer().getConsoleSender());
	}
}