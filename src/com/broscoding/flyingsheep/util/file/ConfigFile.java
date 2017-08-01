package com.broscoding.flyingsheep.util.file;

import com.broscoding.flyingsheep.FlyingSheep;

public class ConfigFile {

	private FlyingSheep c;

	public ConfigFile(FlyingSheep plugin) {
		c = plugin;
	}

	public void loadFile() {
		
		c.saveDefaultConfig();
		c.reloadConfig();
	}
}