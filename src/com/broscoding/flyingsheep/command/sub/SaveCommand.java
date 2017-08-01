package com.broscoding.flyingsheep.command.sub;

import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.CommandType;

public class SaveCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public SaveCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(CommandSender p, String name) {
		
		Game game = null;
		for (Game games : c.unsavedGames) {
			if (games.getName().equalsIgnoreCase(name)) game = games;
		}
		
		if (game == null) {
			p.sendMessage(c.p + "Couldn't recognize any unsavedGame with name §n" + name + 
					"§f. To create a game, type: §c/fs create [name] [minPlayers] [maxPlayers]");
			return;
		}
		
		int minimumSpawns = game.getMinPlayers() - game.getSpawnAmount();
		
		if (minimumSpawns > 0) {
			p.sendMessage(c.p + "Game §n" + game.getName() + "§f couldn't be saved because");
			p.sendMessage(c.p + "You need at least §n" + minimumSpawns + "§f more spawnPoints");
			return;
		}
		
		if (game.getMid() == null) {
			p.sendMessage(c.p + "Game §n" + game.getName() + "§f couldn't be saved because");
			p.sendMessage(c.p + "You need to locate the middle of the map. Type: §c/fs setmid §n" + game.getName());
			return;
		}
		
		if (game.getLobby() == null) {
			p.sendMessage(c.p + "Game §n" + game.getName() + "§f couldn't be saved because");
			p.sendMessage(c.p + "You need to locate the lobby for the game. Type: §c/fs setlobby §n" + game.getName());
			return;
		}
		
		c.unsavedGames.remove(game);
		c.games.add(game);
		
		p.sendMessage(c.p + "The game §n" + game.getName() + "§f has been saved");
	}
}