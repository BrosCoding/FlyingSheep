package com.broscoding.flyingsheep.util.game;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.TeamColor;

public class ReadGame {
	
	private FlyingSheep c;
	
	public ReadGame(FlyingSheep plugin, File file) {
		c = plugin;
		this.file = file;
	}
	
	private File file;
	
	public Game read() {
		
		Game game = new Game(data.getString("Name"), data.getInt("MinPlayers"));
		
		Location lobby = new Location(c.getServer().getWorld(data.getString("Lobby.World")), 
				data.getDouble("Lobby.X"), 
				data.getDouble("Lobby.Y"), 
				data.getDouble("Lobby.Z"), 
				data.getLong("Lobby.Yaw"), 
				data.getLong("Lobby.Pitch"));
		
		game.setLobby(lobby);
		
		Location mid = new Location(c.getServer().getWorld(data.getString("Mid.World")), 
				data.getDouble("Mid.X"), 
				data.getDouble("Mid.Y"), 
				data.getDouble("Mid.Z"), 
				data.getLong("Mid.Yaw"), 
				data.getLong("Mid.Pitch"));
		
		game.setMid(mid);
		
		for (String color : data.getConfigurationSection("Spawn").getKeys(false)) {
			Location spawn = new Location(c.getServer().getWorld(data.getString("Mid.World")), 
					data.getDouble("Spawn." + color + ".X"), 
					data.getDouble("Spawn." + color + ".Y"), 
					data.getDouble("Spawn." + color + ".Z"), 
					data.getLong("Spawn." + color + ".Yaw"), 
					data.getLong("Spawn." + color + ".Pitch"));
			game.setSpawn(TeamColor.valueOf(color), spawn);
		}
		
		return game;
	}
	
	private YamlConfiguration data;
	
	public void load() {
		data = YamlConfiguration.loadConfiguration(file);
	}
}