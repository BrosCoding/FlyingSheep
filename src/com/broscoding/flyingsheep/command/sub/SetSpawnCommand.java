package com.broscoding.flyingsheep.command.sub;

import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.CommandType;
import com.broscoding.flyingsheep.util.TeamColor;

public class SetSpawnCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public SetSpawnCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(Player p, String name, String color) {
		
		Game game = null;
		for (Game games : c.unsavedGames) {
			if (games.getName().equalsIgnoreCase(name)) game = games;
		}
		
		if (game == null) {
			p.sendMessage(c.p + "Couldn't recognize any unsavedGame with name §n" + name + 
					"§f. To create a game, type: §c/fs create [name] [minPlayers] [maxPlayers]");
			return;
		}
		
		TeamColor teamColor = null;
		for (TeamColor teamColors : TeamColor.values()) {
			if (teamColors.toString().equalsIgnoreCase(color)) teamColor = teamColors;
		}
		
		if (teamColor == null) {
			p.sendMessage(c.p + "Can't recognize any teamColor with name §n" + color + "§e (list of teamColors: http://broscoding.com/go/fs/teamcolors/)");
			return;
		}
		
		game.setSpawn(teamColor, p.getLocation());
		
		int minimumSpawns = game.getMinPlayers() - game.getSpawnAmount();
		
		p.sendMessage(c.p + "Spawn for team §n" + TeamColor.getChatColor(teamColor) + teamColor.toString().toUpperCase() + "§f has been set for game §n" + game.getName());
		if (minimumSpawns > 0) p.sendMessage(c.p + "You need at least §n" + minimumSpawns + "§f more spawnPoints");
	}
}