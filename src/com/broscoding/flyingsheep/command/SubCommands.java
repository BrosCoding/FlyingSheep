package com.broscoding.flyingsheep.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.sub.CreateCommand;
import com.broscoding.flyingsheep.command.sub.DeleteCommand;
import com.broscoding.flyingsheep.command.sub.HelpCommand;
import com.broscoding.flyingsheep.command.sub.JoinCommand;
import com.broscoding.flyingsheep.command.sub.LeaveCommand;
import com.broscoding.flyingsheep.command.sub.ListCommand;
import com.broscoding.flyingsheep.command.sub.ReloadCommand;
import com.broscoding.flyingsheep.command.sub.SaveCommand;
import com.broscoding.flyingsheep.command.sub.SetLobbyCommand;
import com.broscoding.flyingsheep.command.sub.SetMidCommand;
import com.broscoding.flyingsheep.command.sub.SetSpawnCommand;
import com.broscoding.flyingsheep.util.CommandType;

public class SubCommands implements CommandExecutor {
	
	private FlyingSheep c;
	
	public SubCommands(FlyingSheep plugin) {
		c = plugin;
		register();
	}
	
	private PluginCommand createCommand;
	private PluginCommand deleteCommand;
	private PluginCommand helpCommand;
	private PluginCommand joinCommand;
	private PluginCommand leaveCommand;
	private PluginCommand listCommand;
	private PluginCommand reloadCommand;
	private PluginCommand saveCommand;
	private PluginCommand setLobbyCommand;
	private PluginCommand setMidCommand;
	private PluginCommand setSpawnCommand;
	
	private void register() {
		createCommand = new CreateCommand(c, CommandType.ALL, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.create"});
		deleteCommand = new DeleteCommand(c, CommandType.ALL, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.delete"});
		helpCommand = new HelpCommand(c, CommandType.ALL, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.help"});
		joinCommand = new JoinCommand(c, CommandType.PLAYER, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.join"});
		leaveCommand = new LeaveCommand(c, CommandType.PLAYER, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.leave"});
		listCommand = new ListCommand(c, CommandType.ALL, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.list"});
		reloadCommand = new ReloadCommand(c, CommandType.ALL, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.reload"});
		saveCommand = new SaveCommand(c, CommandType.ALL, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.save"});
		setLobbyCommand = new SetLobbyCommand(c, CommandType.PLAYER, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.setlobby"});
		setMidCommand = new SetMidCommand(c, CommandType.PLAYER, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.setmid"});
		setSpawnCommand = new SetSpawnCommand(c, CommandType.PLAYER, new String[] {"FlyingSheep.*", "FlyingSheep.cmd.*", "FlyingSheep.cmd.setspawn"});
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (label.equalsIgnoreCase("flyingsheep") || label.equalsIgnoreCase("fs")) {
			
			switch (args.length) {
			
			case 1:
				
				switch (args[0]) {
				
				case "help":
					helpCommand.peform(sender);
					break;
				case "reload":
					reloadCommand.peform(sender);
					break;
				case "leave":
					leaveCommand.peform(sender);
					break;
				case "list":
					listCommand.peform(sender);
					break;
				default:
					sender.sendMessage(c.getMessages().help);
				}
				break;
				
			case 2:
				
				switch (args[0]) {
				
				case "setmid":
					setMidCommand.peform(sender, args[1]);
					break;
				case "setlobby":
					setLobbyCommand.peform(sender, args[1]);
					break;
				case "save":
					saveCommand.peform(sender, args[1]);
					break;
				case "delete":
					deleteCommand.peform(sender, args[1]);
					break;
				case "join":
					joinCommand.peform(sender, args[1]);
					break;
				case "list":
					listCommand.peform(sender, args[1]);
					break;
				default:
					sender.sendMessage(c.getMessages().help);
				}
				break;
				
			case 3:
				
				switch (args[0]) {
				
				case "create":
					createCommand.peform(sender, args[1], args[2]);
					break;
				case "setspawn":
					setSpawnCommand.peform(sender, args[1], args[2]);
					break;
				default:
					sender.sendMessage(c.getMessages().help);
				}
				break;
			
			default:
				sender.sendMessage(c.getMessages().help);
			}
			
			return true;
		}
		return false;
	}
}