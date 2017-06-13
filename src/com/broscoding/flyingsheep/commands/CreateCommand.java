package com.broscoding.flyingsheep.commands;

import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.utils.UTF8Utils;

public class CreateCommand {
	
	private FlyingSheep c;
	
	public CreateCommand(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void execute(CommandSender p, String name, String minPlayersString, String maxPlayersString) {
		
		if (UTF8Utils.isUTF8(name)) {
			p.sendMessage(c.p + "§fIllegal arguments. §cName " + name + " contains UTF-8 characters");
			return;
		}
		
		int minPlayers;
		try {
			minPlayers = Integer.parseInt(minPlayersString);
		}
		catch (NumberFormatException e) {
			p.sendMessage(c.p + "§fIllegal arguments. Usage: §c/fs create [name] [minPlayers] [maxPlayers]");
			return;
		}
		int maxPlayers;
		try {
			maxPlayers = Integer.parseInt(maxPlayersString);
		}
		catch (NumberFormatException e) {
			p.sendMessage(c.p + "§fIllegal arguments. Usage: §c/fs create [name] [minPlayers] [maxPlayers]");
			return;
		}
		
		c.games.add(new Game(name, minPlayers, maxPlayers));
		
		p.sendMessage(c.p + "Game " + name + " has been created");
		p.sendMessage(c.p + "Before you can save it by using §c/fs save " + name + " §fyou have to:");
		p.sendMessage("- Add spawn points (§c/fs addspawn " + name + " [color]§f while standing on the location)");
		p.sendMessage("");
	}
}