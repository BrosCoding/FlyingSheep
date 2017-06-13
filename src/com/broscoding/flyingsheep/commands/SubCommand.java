package com.broscoding.flyingsheep.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;

public class SubCommand implements CommandExecutor {
	
	private FlyingSheep c;
	
	public SubCommand(FlyingSheep plugin) {
		c = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("flyingsheep") || label.equalsIgnoreCase("fs")) {
			
			if (sender.isOp() || sender.hasPermission("flyingsheep.*") || sender.hasPermission("flyingsheep.cmd")) {
				
				switch (args.length) {
				
				//Check if args is 1
				case 1:
					
					//Check the args for the command
					switch (args[0]) {
					
					case "help":
						new HelpCommand(c).execute(sender);
						break;
					case "reload":
						new ReloadCommand(c).execute(sender);
						break;
					default:
						sender.sendMessage(c.getMessages().help);
					}
					break;
				
				case 4:
					
					switch (args[0]) {
					
					case "create":
						new CreateCommand(c).execute(sender, args[1], args[2], args[3]);
						break;
					default:
						sender.sendMessage(c.getMessages().help);
					}
					break;
				//Well lets return with a help message
				default:
					sender.sendMessage(c.getMessages().help);
				}
			}
			else sender.sendMessage(c.p + "FlyingSheep §c" + c.getDescription().getVersion() + " §fby BrosCoding");
			return true;
		}
		return false;
	}
}