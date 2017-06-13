package com.broscoding.flyingsheep.variables;

import com.broscoding.flyingsheep.FlyingSheep;

public class Messages {
	
	private FlyingSheep c;
	
	public Messages(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void registerMessages() {
		help = prefix + "Unknown command, for help type §c/fs help";
	}
	
	public void createShortcuts() {
		c.p = prefix;
	}
	
	public String prefix = "§8[§cFlying§fSheep§8]§f ";

	public String help;
}