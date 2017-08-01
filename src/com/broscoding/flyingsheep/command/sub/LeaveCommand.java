package com.broscoding.flyingsheep.command.sub;

import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.event.PlayerQuitFlyingSheepEvent;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.CommandType;
import com.broscoding.flyingsheep.util.event.QuitFlyingSheepReason;

public class LeaveCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public LeaveCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(Player p) {
		
		Game game = null;
		for (Game games : c.games) {
			for (Player pt : games.getPlayers()) {
				if (pt == p) game = games;
			}
		}
		
		if (game == null) {
			p.sendMessage(c.p + "You aren't in any game");
			return;
		}
		
		PlayerQuitFlyingSheepEvent e = new PlayerQuitFlyingSheepEvent(p, game, QuitFlyingSheepReason.COMMAND);
		c.getServer().getPluginManager().callEvent(e);
		
		if (!e.isCancelled()) {
			game.removePlayer(p);
			game.broadcastMessage(c.p + p.getName() + " left the game");
			p.sendMessage(c.p + "You left the game");
		}
	}
}