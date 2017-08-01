package com.broscoding.flyingsheep;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.broscoding.flyingsheep.command.SubCommands;
import com.broscoding.flyingsheep.data.Messages;
import com.broscoding.flyingsheep.data.Variables;
import com.broscoding.flyingsheep.game.Game;
import com.broscoding.flyingsheep.listener.PlayerJoinListener;
import com.broscoding.flyingsheep.listener.PlayerPickupItemListener;
import com.broscoding.flyingsheep.runnable.ActionBarRunnable;
import com.broscoding.flyingsheep.runnable.AutoUpdateReminderRunnable;
import com.broscoding.flyingsheep.util.UpdateReminder;
import com.broscoding.flyingsheep.util.file.ConfigFile;
import com.broscoding.flyingsheep.util.file.StatsFile;
import com.broscoding.flyingsheep.util.game.ReadGame;
import com.broscoding.flyingsheep.util.game.WriteGame;

public class FlyingSheep extends JavaPlugin implements PluginMessageListener {
	
	public Logger log = Logger.getLogger("Minecraft");
	
	public String p;
	
	public boolean bungeeMode;
	
	@SuppressWarnings("deprecation")
	public void onEnable() {
		
		log.info("[FlyingSheep] Loading...");
		long time = System.currentTimeMillis();
		
		new ConfigFile(this).loadFile();
		
		messages = new Messages(this);
		messages.registerMessages();
		messages.createShortcuts();
		variables = new Variables(this);
		variables.registerVariables();
		variables.createShortcuts();
		
		new StatsFile(this).loadFile();
		
		statsFile = YamlConfiguration.loadConfiguration(new File(getDataFolder(), getVariables().fileBackend));
		
		registerListeners();
		registerCommands();
		
		File folder = new File(getDataFolder() + "\\games");
		if (!folder.exists()) if (!folder.mkdir()) log.severe("Creation of dir failed! Error:");
		
		File[] files = new File(getDataFolder() + "\\games").listFiles();
		for (File file : files) {
			if (file.isFile()) {
				ReadGame rg = new ReadGame(this, file);
				rg.load();
				games.add(rg.read());
			}
		}
		
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new AutoUpdateReminderRunnable(this), 0, 1*60*60*20);
		
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new ActionBarRunnable(this), 0, 1*20);
		
		double resultTime = (System.currentTimeMillis() - time) / 1000.000D;
		log.info("[FlyingSheep] Enabled (" + resultTime + "s)");
	}
	
	public void onDisable() {
		log.info("[FlyingSheep] Wrapping up...");
		long time = System.currentTimeMillis();
		
		for (Game game : games) {
			for (Player pt : game.getPlayers()) {
				pt.sendMessage(p + "The game you currently was playing has been shutdown");
				
				if (bungeeMode) sendToServer(pt, getVariables().fallbackServer);
				else pt.teleport(getVariables().joinLocation.get(pt));
			}
			
			WriteGame wg = new WriteGame(this, game);
			wg.load();
			wg.write();
			wg.save();
		}
		
		double resultTime = (System.currentTimeMillis() - time) / 1000.000D;
		log.info("[FlyingSheep] Disabled (" + resultTime + "s)");
		
	}
	
	private void registerListeners() {
		new PlayerJoinListener(this);
		new PlayerPickupItemListener(this);
	}
	
	private void registerCommands() {
		PluginCommand pluginCommand = getServer().getPluginCommand("flyingsheep");
		
		pluginCommand.setAliases(Arrays.asList("fs"));
		pluginCommand.setExecutor(new SubCommands(this));
	}
	
	public ArrayList<Game> games = new ArrayList<Game>();
	public ArrayList<Game> unsavedGames = new ArrayList<Game>();
	
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
	
	public YamlConfiguration statsFile;
	
	public YamlConfiguration getStatsFile() {
		return statsFile;
	}
	
	public void saveData() {
		try {
			getStatsFile().save(new File(getDataFolder(), getVariables().fileBackend));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendToServer(Player p, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try{
			out.writeUTF("Connect");
			out.writeUTF(server);
		}catch (Exception e) {
			e.printStackTrace();
		}
		p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
	}
	
	public void onPluginMessageReceived(String channel, Player p, byte[] message) {
	}
}
 