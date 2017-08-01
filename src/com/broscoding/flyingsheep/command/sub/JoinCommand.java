package com.broscoding.flyingsheep.command.sub;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.event.PlayerJoinFlyingSheepEvent;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.CommandType;
import com.broscoding.flyingsheep.util.GameStage;
import com.broscoding.flyingsheep.util.event.JoinFlyingSheepReason;

public class JoinCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public JoinCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(Player p, String name) {
		
		Game game = null;
		for (Game games : c.games) {
			if (games.getName().equalsIgnoreCase(name)) game = games;
		}
		
		if (game == null) {
			p.sendMessage(c.p + "Couldn't recognize any game with name §n" + name);
			return;
		}
		
		PlayerJoinFlyingSheepEvent e = new PlayerJoinFlyingSheepEvent(p, game, JoinFlyingSheepReason.COMMAND);
		c.getServer().getPluginManager().callEvent(e);
		
		if (!e.isCancelled()) {
			for (Game games : c.games) {
				if (games.getPlayers().contains(p)) {
					p.sendMessage(c.p + "You are allready in a game. To leave it type: §c/fs leave");
					return;
				}
			}
			
			if (game.getGameStage() == GameStage.WAITING || game.getGameStage() == GameStage.BETWEEN_ROUNDS) {
				
				game.addPlayer(p);
				c.getVariables().joinLocation.put(p, p.getLocation());
				p.teleport(game.getLobby());
				game.broadcastMessage(c.p + p.getName() + " joined the game");
				return;
			}
			if (game.getGameStage() == GameStage.FULL) {
				if (c.getVariables().fullJoin && 
						(p.isOp() || p.hasPermission("flyingsheep.*") || p.hasPermission("flyingsheep.vip") || p.hasPermission("flyingsheep.game.fulljoin"))) {
					ArrayList<Player> players = new ArrayList<Player>();
					for (Player pt : game.getPlayers()) {
						if (!(p.isOp() || p.hasPermission("flyingsheep.*") || p.hasPermission("flyingsheep.vip") || p.hasPermission("flyingsheep.game.fulljoin"))) {
							players.add(pt);
						}
					}
					Player pt = players.get(new Random().nextInt(players.size()));
					
					pt.sendMessage(c.p + "§cYou have been kicked to make space for a VIP");
					
					if (c.bungeeMode) c.sendToServer(pt, c.getVariables().fallbackServer);
					else {
						pt.teleport(c.getVariables().joinLocation.get(pt));
						c.getVariables().joinLocation.remove(pt);
					}
					
					game.addPlayer(p);
					c.getVariables().joinLocation.put(p, p.getLocation());
					p.teleport(game.getLobby());
					game.broadcastMessage(c.p + p.getName() + " joined the game");
				}
				else p.sendMessage(c.p + "The game is full");
				return;
			}
			if (game.getGameStage() == GameStage.INGAME) {
				if (c.getVariables().spectatorMode) {
					if ((game.getPlayers().size() + game.getSpectators().size()) < game.getMaxPlayers()) {
						game.addSpectator(p);
					}
					else p.sendMessage(c.p + "The game is full");	
				}
				else p.sendMessage(c.p + "The game has already started");
				return;
			}
		}
	}
}