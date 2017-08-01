package com.broscoding.flyingsheep.util.game;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.TeamColor;
import com.google.common.io.Files;

public class WriteGame {
	
	private FlyingSheep c;
	
	public WriteGame(FlyingSheep plugin, Game game) {
		c = plugin;
		this.game = game;
	}
	
	private Game game;
	
	public void write() {
		
		data.set("Name", game.getName());
		data.set("MinPlayers", game.getMinPlayers());
		
		data.set("Lobby.World", game.getLobby().getWorld().getName());
		data.set("Lobby.X", game.getLobby().getX());
		data.set("Lobby.Y", game.getLobby().getY());
		data.set("Lobby.Z", game.getLobby().getZ());
		data.set("Lobby.Yaw", game.getLobby().getYaw());
		data.set("Lobby.Pitch", game.getLobby().getPitch());
		
		data.set("Mid.World", game.getMid().getWorld().getName());
		data.set("Mid.X", game.getMid().getX());
		data.set("Mid.Y", game.getMid().getY());
		data.set("Mid.Z", game.getMid().getZ());
		data.set("Mid.Yaw", game.getMid().getYaw());
		data.set("Mid.Pitch", game.getMid().getPitch());
		
		@SuppressWarnings("rawtypes")
		Iterator it = game.getSpawns().entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry) it.next();
			TeamColor color = (TeamColor) pair.getKey();
			Location loc = (Location) pair.getValue();
			data.set("Spawn." + color + ".World", loc.getWorld().getName());
			data.set("Spawn." + color + ".X", loc.getX());
			data.set("Spawn." + color + ".Y", loc.getY());
			data.set("Spawn." + color + ".Z", loc.getZ());
			data.set("Spawn." + color + ".Yaw", loc.getYaw());
			data.set("Spawn." + color + ".Pitch", loc.getPitch());
		}
	}
	
	private YamlConfiguration data;
	
	public void save() {
		try {
			data.save(new File(c.getDataFolder() + "\\games", game.getName().toLowerCase() + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		
		File file = new File(c.getDataFolder() + "\\games", game.getName().toLowerCase() + ".yml");
		if (!file.exists()) createFile(file);
		
		data = YamlConfiguration.loadConfiguration(new File(c.getDataFolder() + "\\games", game.getName().toLowerCase() + ".yml"));
	}
	
	private void createFile(File file) {
		try {
			file.createNewFile();
			Files.write("# NOTICE!\n# THIS FILE IS ONLY FOR STORAGE! DO NOT EDIT".getBytes(), file);
			
		} catch (IOException e) {
			c.log.severe("Creation of file failed! Error:");
			e.printStackTrace();
		}
	}
}