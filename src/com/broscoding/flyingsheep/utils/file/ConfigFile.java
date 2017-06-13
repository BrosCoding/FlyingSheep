package com.broscoding.flyingsheep.utils.file;

import com.broscoding.flyingsheep.FlyingSheep;

public class ConfigFile {
	
	private FlyingSheep c;
	
	public ConfigFile(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void loadConfig() {
		
		c.saveDefaultConfig();
		c.reloadConfig();
	}
}