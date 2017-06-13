package com.broscoding.flyingsheep.commands;

import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;

public class ReloadCommand {
	
	private FlyingSheep c;
	
	public ReloadCommand(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void execute(CommandSender p) {
		
		p.sendMessage(c.p + "Reloading...");
		long time = System.currentTimeMillis();
		
		c.getServer().getPluginManager().disablePlugin(c);
		c.getServer().getPluginManager().enablePlugin(c);
		
		double resultTime = (System.currentTimeMillis() - time) / 1000.000D;
		p.sendMessage(c.p + "Done (§c" + resultTime + "s§f)");
	}
}