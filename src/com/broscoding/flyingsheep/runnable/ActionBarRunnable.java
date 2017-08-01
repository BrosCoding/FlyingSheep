package com.broscoding.flyingsheep.runnable;

import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.util.ActionBar;

public class ActionBarRunnable implements Runnable {
	
	private FlyingSheep c;
	
	public ActionBarRunnable(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void run() {
		for (Game game : c.games) {
			ActionBar specBar = new ActionBar("§fYou are spectating on §n" + game.getName());
			
			for (Player pt : game.getSpectators()) {
				specBar.sendToPlayer(pt);
			}
			for (Player pt : game.getPlayers()) {
				String hearts = "";
				for (int i = 0; i < game.getSheepHearts(pt); i++) {
					hearts = hearts + "❤";
				}
				ActionBar sheepHealthBar = new ActionBar("§fYour sheep has §c" + hearts + "§f hearts");
				sheepHealthBar.sendToPlayer(pt);
			}
		}
	}
}