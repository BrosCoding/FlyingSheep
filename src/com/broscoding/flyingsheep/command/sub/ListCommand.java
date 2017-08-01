package com.broscoding.flyingsheep.command.sub;

import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.CommandType;

public class ListCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public ListCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(CommandSender p, String pageString) {
		
		int page;
		try {
			page = Integer.parseInt(pageString);
		}
		catch (NumberFormatException e) {
			p.sendMessage(c.p + "Illegal arguments. The argument §n" + pageString + "§f couldn't be fetched as a number");
			return;
		}
		
		int maxPage = (c.games.size() / 10) + 1;
		
		if (page < 1 || page > maxPage) {
			p.sendMessage(c.p + "Page §n" + page + "§f is out of range");
			return;
		}
		
		p.sendMessage("§2§m--------------[§c Flying§fSheep Games §2§m]--------------");
		
		int i1 = page * 10;
		for (int i = page * 10 - 10; i1 > i;) {
			if (c.games.size() >= i + 1) {
				Game game = c.games.get(i);
				p.sendMessage("§6" + game.getName() + " - " + game.getGameStage() + " - " + game.getPlayers().size() + "/" + game.getMaxPlayers());
			}
			i++;
		}
		
		p.sendMessage("§2§m------------------[§f Page " + page + "/" + maxPage + " §2§m]------------------");
	}
	
	public void execute(CommandSender p) {
		
		int page = 1;
		
		p.sendMessage("§2§m--------------[§c Flying§fSheep Games §2§m]--------------");
		
		int i1 = page * 10;
		for (int i = page * 10 - 10; i1 > i;) {
			if (c.games.size() >= i + 1) {
				Game game = c.games.get(i);
				p.sendMessage("§6" + game.getName() + " - " + game.getGameStage() + " - " + game.getPlayers().size() + "/" + game.getMaxPlayers());
			}
			i++;
		}
		
		int maxPage = (c.games.size() / 10) + 1;
		
		p.sendMessage("§2§m------------------[§f Page " + page + "/" + maxPage + " §2§m]------------------");
	}
}