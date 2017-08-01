package com.broscoding.flyingsheep.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.event.QuitFlyingSheepReason;

public class PlayerQuitFlyingSheepEvent extends Event implements Cancellable {
	
	private static final HandlerList HANDLERS = new HandlerList();
	
	private Player p;
	
	private Game game;
	
	private boolean cancelled = false;
	
	private QuitFlyingSheepReason quitFlyingSheepReason;
	
	public PlayerQuitFlyingSheepEvent(Player p, Game game, QuitFlyingSheepReason quitFlyingSheepReason) {
		this.game = game;
		this.p = p;
		this.quitFlyingSheepReason = quitFlyingSheepReason;
	}
	
	public QuitFlyingSheepReason getQuitFlyingSheepReason() {
		return quitFlyingSheepReason;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
        return HANDLERS;
    }
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
}