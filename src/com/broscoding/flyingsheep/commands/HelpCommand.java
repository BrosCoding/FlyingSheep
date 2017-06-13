package com.broscoding.flyingsheep.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.utils.TeamColors;

import be.maximvdw.featherboard.api.FeatherBoardAPI;

public class HelpCommand {
	
	private FlyingSheep c;
	
	public HelpCommand(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void execute(CommandSender p) {
		
		p.sendMessage(c.p + "FlyingSheep §c" + c.getDescription().getVersion() + " §fby BrosCoding");
		p.sendMessage(" §6/fs help" + " §8> " + "§fShows the help list for FlyingSheep");
		p.sendMessage(" §6/fs reload" +  " §8> " + "§fReloads the plugin");
		
		runScoreboard((Player) p);
	}
	
	//⏨ ⏩ ⏪ ⏫ ⏬ ⏭ ⏮ ⏯ ⏰ ⏱ ⏲ ⏳ ⏴ ⏵ ⏶ ⏷ ⏸ ⏹ ⏺
	
	@SuppressWarnings("deprecation")
	private void runScoreboard(Player p) {
		
		if (FeatherBoardAPI.isToggled(p)) {
			FeatherBoardAPI.toggle(p);
			
			Scoreboard board = c.getServer().getScoreboardManager().getNewScoreboard();
			
			Team red = board.registerNewTeam("red");
			Team blue = board.registerNewTeam("blue");
			red.setPrefix(ChatColor.RED.toString());
			blue.setPrefix(ChatColor.BLUE.toString());
			
			Objective objective = board.registerNewObjective("board", "dummy");
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			objective.setDisplayName("§c§lFlying§f§lSheep");
			
			int i = 0;
			for (TeamColors color : TeamColors.values()) {
				objective.getScore(TeamColors.getChatColor(color) + p.getName()).setScore(i);
				
				i++;
			}
			
			for (Player pa : c.getServer().getOnlinePlayers()) {
				if (pa.getName().equals("Simmi_")) blue.addPlayer(pa);
				if (pa.getName().equals("CoffeeBunny")) red.addPlayer(pa);
			}
			
			p.setScoreboard(board);
		}
		else {
			FeatherBoardAPI.toggle(p);
			
			//p.setScoreboard(null);
		}
	}
}