package com.broscoding.flyingsheep.command.sub;

import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.util.CommandType;

public class ReloadCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public ReloadCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(CommandSender p) {
		
		p.sendMessage(c.p + "Reloading...");
		long time = System.currentTimeMillis();
		
		c.getServer().getPluginManager().disablePlugin(c);
		c.getServer().getPluginManager().enablePlugin(c);
		
		double resultTime = (System.currentTimeMillis() - time) / 1000.000D;
		p.sendMessage(c.p + "Done (" + resultTime + "s)");
	}
}