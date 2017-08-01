package com.broscoding.flyingsheep.command.sub;

import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.CommandType;

public class SetMidCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public SetMidCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(Player p, String name) {
		
		Game game = null;
		for (Game games : c.unsavedGames) {
			if (games.getName().equalsIgnoreCase(name)) game = games;
		}
		
		if (game == null) {
			p.sendMessage(c.p + "Couldn't recognize any unsavedGame with name §n" + name + 
					"§f. To create a game, type: §c/fs create [name] [minPlayers] [maxPlayers]");
			return;
		}
		
		game.setMid(p.getLocation());
		
		p.sendMessage(c.p + "The middle has been set for game §n" + game.getName());
	}
}