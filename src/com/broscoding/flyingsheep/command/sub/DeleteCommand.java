package com.broscoding.flyingsheep.command.sub;

import java.io.File;

import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.CommandType;

public class DeleteCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public DeleteCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(CommandSender sender, String name) {
		
		Game game = null;
		for (Game games : c.games) {
			if (games.getName().equalsIgnoreCase(name)) game = games;
		}
		
		if (game == null) {
			sender.sendMessage(c.p + "Couldn't recognize any game with name §n" + name);
			return;
		}
		
		File file = new File(c.getDataFolder() + "\\games", game.getName().toLowerCase() + ".yml");
		if (!file.delete()) c.log.severe("Couldn't delete file: " + game.getName() + ".yml");
		c.games.remove(game);
		
		sender.sendMessage(c.p + "Game §n" + game.getName() + "§f has been deleted");
	}
}