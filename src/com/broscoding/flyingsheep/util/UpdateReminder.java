package com.broscoding.flyingsheep.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.FlyingSheep;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class UpdateReminder {
	
	private FlyingSheep c;
	
	public UpdateReminder(FlyingSheep plugin) {
		c = plugin;
	}
	
	/**
	 * 
	 * TODO: Add resource location from spigot
	 */
	public static final String RESOURCE_ID = "29765";
	public static final String RESOURCE_URL = "https://www.spigotmc.org/resources/mysterybox.29765/";
	
	public String getVersionFromSpigot() {
		try{
			HttpURLConnection con = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.getOutputStream().write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + RESOURCE_ID).getBytes("UTF-8"));
			String version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
			if (version.length() <= 7) return version;
		}
		catch (Exception ex) {
			c.getLogger().severe("Failed to check for a update on spigot!");
		}
		return null;
	}
	
	public void sendConsoleMessage(ConsoleCommandSender p) {
		p.sendMessage(ChatColor.YELLOW + "[FlyingSheep] A new version of FlyingSheep is available " + ChatColor.RED + 
				"(" + getVersionFromSpigot() + ")" + ChatColor.YELLOW + " you are currently on " + 
				ChatColor.RED + c.getDescription().getVersion());
	}
	
	public void sendFancyMessage(Player p) {
		TextComponent tc = new TextComponent();
		
		p.sendMessage(c.p + ChatColor.YELLOW + "A new version of FlyingSheep is available " + ChatColor.RED + 
				"(" + getVersionFromSpigot() + ")" + ChatColor.YELLOW + " you are currently on " + 
				ChatColor.RED + c.getDescription().getVersion());
		
		tc.setText(ChatColor.YELLOW + ChatColor.UNDERLINE.toString() + "Click to visit download page");
		
		tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, 
				new ComponentBuilder("Click to visit download page:\n" + RESOURCE_URL).create()));
		
		tc.setClickEvent(new ClickEvent(Action.OPEN_URL, RESOURCE_URL));
		
		p.spigot().sendMessage(tc);
	}
}