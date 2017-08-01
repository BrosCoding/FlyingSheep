package com.broscoding.flyingsheep.command.sub;

import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.CommandType;
import com.broscoding.flyingsheep.util.UTF8Utils;

public class CreateCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public CreateCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(CommandSender p, String name, String minPlayersString) {
		
		if (UTF8Utils.isUTF8(name)) {
			p.sendMessage(c.p + "Illegal arguments. Name §n" + name + "§f contains UTF-8 characters");
			return;
		}
		
		Game game = null;
		for (Game games : c.unsavedGames) {
			if (games.getName().equalsIgnoreCase(name)) game = games;
		}
		for (Game games : c.games) {
			if (games.getName().equalsIgnoreCase(name)) game = games;
		}
		
		if (game != null) {
			p.sendMessage(c.p + "Game name §n" + name + "§f is already in use");
			return;
		}
		
		int minPlayers;
		try {
			minPlayers = Integer.parseInt(minPlayersString);
		}
		catch (NumberFormatException e) {
			p.sendMessage(c.p + "Illegal arguments. Usage: §c/fs create [name] [minPlayers] [maxPlayers]");
			return;
		}
		
		
		if (minPlayers > 14 || minPlayers < 2) {
			p.sendMessage(c.p + "MinPlayers can't be §n" + minPlayers + "§f. It has to be minimum 2 and maximum 14");
			return;
		}
		
		game = new Game(name, minPlayers);
		
		c.unsavedGames.add(game);
		
		int minimumSpawns = game.getMinPlayers() - game.getSpawnAmount();
		
		p.sendMessage(c.p + "Game §n" + name + "§f has been created");
		p.sendMessage(c.p + "§lBefore you can save the game by using §c§l/fs save §n" + name + "§f§l you have to:");
		p.sendMessage("");
		p.sendMessage("§7�? §fAdd spawn points");
		p.sendMessage(" §7�? §f§c/fs setspawn §n" + name + "§c [teamColor]§f while standing on the location");
		p.sendMessage(" §7�? §fList of teamColors: http://broscoding.com/go/fs/teamcolors/");
		p.sendMessage(" §7�? §fYou need at least §n" + minimumSpawns + "§f more spawnPoints");
		p.sendMessage("");
	}
}