package com.broscoding.flyingsheep.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.util.CommandType;

public abstract class PluginCommand {
	
	public PluginCommand(FlyingSheep c, CommandType commandType, String[] permissions) {
		this.c = c;
		this.commandType = commandType;
		this.permissions = permissions;
	}
	
	private FlyingSheep c;
	
	private String[] permissions;
	
	private CommandType commandType;
	
	public void execute(Player sender) {};
	
	public void execute(Player sender, String arg1) {};
	
	public void execute(Player sender, String arg1, String arg2) {};
	
	public void execute(Player sender, String arg1, String arg2, String arg3) {};
	
	public void execute(Player sender, String arg1, String arg2, String arg3, String arg4) {};
	
	public void execute(Player sender, String arg1, String arg2, String arg3, String arg4, String arg5) {};
	
	public void execute(CommandSender sender) {};
	
	public void execute(CommandSender sender, String arg1) {};
	
	public void execute(CommandSender sender, String arg1, String arg2) {};
	
	public void execute(CommandSender sender, String arg1, String arg2, String arg3) {};
	
	public void execute(CommandSender sender, String arg1, String arg2, String arg3, String arg4) {};
	
	public void execute(CommandSender sender, String arg1, String arg2, String arg3, String arg4, String arg5) {};
	
	public void peform(CommandSender sender) {
		if (hasPermission(sender)) {
			if (sender instanceof Player) {
				if (commandType == CommandType.PLAYER) execute((Player) sender);
				else if (commandType == CommandType.ALL) execute(sender);
				else sender.sendMessage(c.getMessages().onlyConsoleCanPerformThisCommand);
				
			}
			else {
				if (commandType == CommandType.CONSOLE) execute(sender);
				else if (commandType == CommandType.ALL) execute(sender);
				else sender.sendMessage(c.getMessages().onlyPlayersCanPerformThisCommand);
			}
		}
		else sender.sendMessage(c.getMessages().noPermission);
	}
	
	public void peform(CommandSender sender, String arg1) {
		if (hasPermission(sender)) {
			if (sender instanceof Player) {
				if (commandType == CommandType.PLAYER) execute((Player) sender, arg1);
				else if (commandType == CommandType.ALL) execute(sender, arg1);
				else sender.sendMessage(c.getMessages().onlyConsoleCanPerformThisCommand);
				
			}
			else {
				if (commandType == CommandType.CONSOLE) execute(sender, arg1);
				else if (commandType == CommandType.ALL) execute(sender, arg1);
				else sender.sendMessage(c.getMessages().onlyPlayersCanPerformThisCommand);
			}
		}
		else sender.sendMessage(c.getMessages().noPermission);
	}
	
	public void peform(CommandSender sender, String arg1, String arg2) {
		if (hasPermission(sender)) {
			if (sender instanceof Player) {
				if (commandType == CommandType.PLAYER) execute((Player) sender, arg1, arg2);
				else if (commandType == CommandType.ALL) execute(sender, arg1, arg2);
				else sender.sendMessage(c.getMessages().onlyConsoleCanPerformThisCommand);
				
			}
			else {
				if (commandType == CommandType.CONSOLE) execute(sender, arg1, arg2);
				else if (commandType == CommandType.ALL) execute(sender, arg1, arg2);
				else sender.sendMessage(c.getMessages().onlyPlayersCanPerformThisCommand);
			}
		}
		else sender.sendMessage(c.getMessages().noPermission);
	}
	
	public void peform(CommandSender sender, String arg1, String arg2, String arg3) {
		if (hasPermission(sender)) {
			if (sender instanceof Player) {
				if (commandType == CommandType.PLAYER) execute((Player) sender, arg1, arg2, arg3);
				else if (commandType == CommandType.ALL) execute(sender, arg1, arg2, arg3);
				else sender.sendMessage(c.getMessages().onlyConsoleCanPerformThisCommand);
				
			}
			else {
				if (commandType == CommandType.CONSOLE) execute(sender, arg1, arg2, arg3);
				else if (commandType == CommandType.ALL) execute(sender, arg1, arg2, arg3);
				else sender.sendMessage(c.getMessages().onlyPlayersCanPerformThisCommand);
			}
		}
		else sender.sendMessage(c.getMessages().noPermission);
	}
	
	public void peform(CommandSender sender, String arg1, String arg2, String arg3, String arg4) {
		if (hasPermission(sender)) {
			if (sender instanceof Player) {
				if (commandType == CommandType.PLAYER) execute((Player) sender, arg1, arg2, arg3, arg4);
				else if (commandType == CommandType.ALL) execute(sender, arg1, arg2, arg3, arg4);
				else sender.sendMessage(c.getMessages().onlyConsoleCanPerformThisCommand);
				
			}
			else {
				if (commandType == CommandType.CONSOLE) execute(sender, arg1, arg2, arg3, arg4);
				else if (commandType == CommandType.ALL) execute(sender, arg1, arg2, arg3, arg4);
				else sender.sendMessage(c.getMessages().onlyPlayersCanPerformThisCommand);
			}
		}
		else sender.sendMessage(c.getMessages().noPermission);
	}
	
	public void peform(CommandSender sender, String arg1, String arg2, String arg3, String arg4, String arg5) {
		if (hasPermission(sender)) {
			if (sender instanceof Player) {
				if (commandType == CommandType.PLAYER) execute((Player) sender, arg1, arg2, arg3, arg4, arg5);
				else if (commandType == CommandType.ALL) execute(sender, arg1, arg2, arg3, arg4, arg5);
				else sender.sendMessage(c.getMessages().onlyConsoleCanPerformThisCommand);
				
			}
			else {
				if (commandType == CommandType.CONSOLE) execute(sender, arg1, arg2, arg3, arg4, arg5);
				else if (commandType == CommandType.ALL) execute(sender, arg1, arg2, arg3, arg4, arg5);
				else sender.sendMessage(c.getMessages().onlyPlayersCanPerformThisCommand);
			}
		}
		else sender.sendMessage(c.getMessages().noPermission);
	}
	
	private boolean hasPermission(CommandSender sender) {
		boolean hasPermission = false;
		
		for (String permission : permissions) {
			if (sender.hasPermission(permission)) hasPermission = true;
		}
		
		if (sender.isOp()) hasPermission = true;
		
		return hasPermission;
	}
}