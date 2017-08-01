package com.broscoding.flyingsheep.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.util.GameStage;
import com.broscoding.flyingsheep.util.TeamColor;

public class Game {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	private ArrayList<Player> spectators = new ArrayList<Player>();
	
	private HashMap<Player, Integer> sheepHearts = new HashMap<Player, Integer>();
	
	private HashMap<TeamColor, Location> spawns = new HashMap<TeamColor, Location>();
	
	private int minPlayers;
	
	private Location lobby;
	
	private Location mid;
	
	private String name;
	
	private GameStage gameStage;
	
	public Game(String name, int minPlayers) {
		this.name = name;
		this.minPlayers = minPlayers;
		this.gameStage = GameStage.WAITING;
	}
	
	public void setGameStage(GameStage gameStage) {
		this.gameStage = gameStage;
	}
	
	public GameStage getGameStage() {
		return gameStage;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMinPlayers() {
		return minPlayers;
	}
	
	public int getMaxPlayers() {
		return spawns.size();
	}
	
	public void setLobby(Location lobby) {
		this.lobby = lobby;
	}
	
	public Location getLobby() {
		return lobby;
	}
	
	public void setMid(Location mid) {
		this.mid = mid;
	}
	
	public Location getMid() {
		return mid;
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void addSpectator(Player p) {
		spectators.add(p);
	}
	
	public void removeSpectator(Player p) {
		spectators.remove(p);
	}
	
	public ArrayList<Player> getSpectators() {
		return spectators;
	}
	
	public void setSpawn(TeamColor color, Location location) {
		spawns.put(color, location);
	}
	
	public Location getSpawn(TeamColor color) {
		return spawns.get(color);
	}
	
	public HashMap<TeamColor, Location> getSpawns() {
		return spawns;
	}
	
	public int getSpawnAmount() {
		return spawns.size();
	}
	
	public void setSheepHearts(Player p, int hearts) {
		sheepHearts.put(p, hearts);
	}
	
	public int getSheepHearts(Player p) {
		return sheepHearts.get(p);
	}
	
	public void broadcastMessage(String message) {
		for (Player p : getPlayers()) {
			p.sendMessage(message);
		}
	}
}