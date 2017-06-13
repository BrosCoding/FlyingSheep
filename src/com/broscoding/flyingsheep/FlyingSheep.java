package com.broscoding.flyingsheep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import com.broscoding.flyingsheep.commands.SubCommand;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.listeners.PlayerJoinEventListener;
import com.broscoding.flyingsheep.utils.AutoUpdateReminder;
import com.broscoding.flyingsheep.utils.UpdateReminder;
import com.broscoding.flyingsheep.utils.file.ConfigFile;
import com.broscoding.flyingsheep.variables.Messages;
import com.broscoding.flyingsheep.variables.Variables;

public class FlyingSheep extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	
	public String p;
	
	public boolean bungeeMode;
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		//test
		log.info("[FlyingSheep] Loading...");
		long time = System.currentTimeMillis();
		
		new ConfigFile(this).loadConfig();
		
		messages = new Messages(this);
		messages.registerMessages();
		messages.createShortcuts();
		variables = new Variables(this);
		variables.registerVariables();
		variables.createShortcuts();
		
		registerListeners();
		registerCommands();
		
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new AutoUpdateReminder(this), 0, 1*60*60*20);
		
		double resultTime = (System.currentTimeMillis() - time) / 1000.000D;
		log.info("[FlyingSheep] Enabled (" + resultTime + "s)");
	}
	
	public void onDisable() {
		log.info("[FlyingSheep] Wrapping up...");
		long time = System.currentTimeMillis();
		
		/**
		 * TODO: Add saving system for the game array
		 */
		
		double resultTime = (System.currentTimeMillis() - time) / 1000.000D;
		log.info("[FlyingSheep] Disabled (" + resultTime + "s)");
		
	}
	
	private void registerListeners() {
		new PlayerJoinEventListener(this);
	}
	
	private void registerCommands() {
		PluginCommand pluginCommand = getServer().getPluginCommand("flyingsheep");
		
		pluginCommand.setAliases(Arrays.asList("fs"));
		pluginCommand.setExecutor(new SubCommand(this));
	}
	
	public ArrayList<Game> games = new ArrayList<Game>();
	
	private UpdateReminder updateReminder = new UpdateReminder(this);
	private Messages messages;
	private Variables variables;
	
	public Variables getVariables() {
		return variables;
	}
	
	public Messages getMessages() {
		return messages;
	}
	
	public UpdateReminder getUpdateReminder() {
		return updateReminder;
	}
}
 