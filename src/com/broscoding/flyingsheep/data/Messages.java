package com.broscoding.flyingsheep.data;

import com.broscoding.flyingsheep.FlyingSheep;

public class Messages {
	
	private FlyingSheep c;
	
	public Messages(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void registerMessages() {
		help = prefix + "Unknown command, for help type §c/fs help";
		onlyPlayersCanPerformThisCommand = prefix + "Only players can peform this command";
		onlyConsoleCanPerformThisCommand = prefix + "Only console can peform this command";
		noPermission = prefix + "Sorry, you don't have enough permissions";
	}
	
	public void createShortcuts() {
		c.p = prefix;
	}
	
	public String prefix = "§8[§cFlying§fSheep§8]§f ";

	public String help;
	public String onlyPlayersCanPerformThisCommand;
	public String onlyConsoleCanPerformThisCommand;
	public String noPermission;
}