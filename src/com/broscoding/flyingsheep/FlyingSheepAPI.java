package com.broscoding.flyingsheep;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.event.PlayerJoinFlyingSheepEvent;
import com.broscoding.flyingsheep.event.PlayerQuitFlyingSheepEvent;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.GameStage;
import com.broscoding.flyingsheep.util.event.JoinFlyingSheepReason;
import com.broscoding.flyingsheep.util.event.QuitFlyingSheepReason;

public class FlyingSheepAPI {
	
	private static FlyingSheep c;
	
	public FlyingSheepAPI(FlyingSheep plugin) {
		c = plugin;
	}
	
	public static Game getGameByName(String name) {
		for (Game game : c.games) {
			if (game.getName().equalsIgnoreCase(name)) return game;
		}
		return null;
	}
	
	public static ArrayList<Game> getGames() {
		return c.games;
	}
	
	public static boolean joinGame(Player p, Game game) {
		PlayerJoinFlyingSheepEvent e = new PlayerJoinFlyingSheepEvent(p, game, JoinFlyingSheepReason.API);
		c.getServer().getPluginManager().callEvent(e);
		
		if (!e.isCancelled()) {
			for (Game games : c.games) {
				if (games.getPlayers().contains(p)) return false;
			}
			
			if (game.getGameStage() == GameStage.WAITING || game.getGameStage() == GameStage.BETWEEN_ROUNDS) {
				
				game.addPlayer(p);
				p.teleport(game.getLobby());
				game.broadcastMessage(c.p + p.getName() + " joined the game");
				return true;
			}
			if (game.getGameStage() == GameStage.INGAME) {
				game.addPlayer(p);
				return true;
			}
		}
		return false;
	}
	
	public static boolean quitGame(Player p) {
		Game game = null;
		for (Game games : c.games) {
			for (Player pt : games.getPlayers()) {
				if (pt == p) game = games;
			}
		}
		
		if (game == null) return false;
		
		PlayerQuitFlyingSheepEvent e = new PlayerQuitFlyingSheepEvent(p, game, QuitFlyingSheepReason.API);
		c.getServer().getPluginManager().callEvent(e);
		
		if (!e.isCancelled()) {
			game.removePlayer(p);
			game.broadcastMessage(c.p + p.getName() + " left the game");
			return true;
		}
		return false;
	}
}