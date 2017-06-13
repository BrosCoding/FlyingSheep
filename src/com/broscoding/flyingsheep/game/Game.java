package com.broscoding.flyingsheep.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.utils.TeamColors;

public class Game {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private HashMap<TeamColors, Location> spawns = new HashMap<TeamColors, Location>();
	
	private int minPlayers;
	private int maxPlayers;
	
	private Location lobby;
	
	private String name;
	
	public Game(String name, int minPlayers, int maxPlayers) {
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMinPlayers() {
		return minPlayers;
	}
	
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	public void setLobby(Location lobby) {
		this.lobby = lobby;
	}
	
	public Location getLobby() {
		return lobby;
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	public ArrayList<Player> getPlayers(Player p) {
		return players;
	}
	
	public void setSpawn(TeamColors color, Location location) {
		spawns.put(color, location);
	}
	
	public Location getSpawn(TeamColors color) {
		return spawns.get(color);
	}
	
	public HashMap<TeamColors, Location> getSpawns() {
		return spawns;
	}
}