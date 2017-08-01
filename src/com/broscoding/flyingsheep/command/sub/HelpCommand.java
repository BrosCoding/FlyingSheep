package com.broscoding.flyingsheep.command.sub;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.broscoding.flyingsheep.FlyingSheep;
import com.broscoding.flyingsheep.command.PluginCommand;
import com.broscoding.flyingsheep.util.CommandType;
import com.broscoding.flyingsheep.util.TeamColor;

import be.maximvdw.featherboard.api.FeatherBoardAPI;

public class HelpCommand extends PluginCommand {
	
	private FlyingSheep c;
	
	public HelpCommand(FlyingSheep plugin, CommandType commandType, String[] permissions) {
		super(plugin, commandType, permissions);
		c = plugin;
	}
	
	public void execute(CommandSender p) {
		
		p.sendMessage(c.p + "FlyingSheep §c" + c.getDescription().getVersion() + " §fby BrosCoding");
		p.sendMessage(" §6/fs help" + " §8> " + "§fShows the help list for FlyingSheep");
		p.sendMessage(" §6/fs reload" + " §8> " + "§fReloads the plugin");
		p.sendMessage(" §6/fs create [name] [minPlayers]" + " §8> " + "§fCreates an unsavedGame");
		p.sendMessage(" §6/fs setspawn [unsavedGame] [teamColor]" + " §8> " + "§fStores a spawnPoint location for an unsavedGame");
		p.sendMessage(" §6/fs setlobby [unsavedGame]" + " §8> " + "§fStores a lobby location for an unsavedGame");
		p.sendMessage(" §6/fs setmid [unsavedGame]" + " §8> " + "§fStores a middle location for an unsavedGame");
		p.sendMessage(" §6/fs save [unsavedGame]" + " §8> " + "§fSaves an unsavedGame");
		p.sendMessage(" §6/fs delete [game]" + " §8> " + "§fDeletes a game");
		p.sendMessage(" §6/fs join [game]" + " §8> " + "§fJoins a game");
		p.sendMessage(" §6/fs leave" + " §8> " + "§fLeaves current game");
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	private void runEffects(Player p) {
		
		Sheep s = (Sheep) p.getWorld().spawnEntity(p.getLocation(), EntityType.SHEEP);
		
		s.setColor(DyeColor.BLUE);
		
		c.getServer().getScheduler().scheduleSyncDelayedTask(c, new Runnable() {
			public void run() {
				for (Player pa : c.getServer().getOnlinePlayers()) {
					pa.playEffect(s.getLocation(), Effect.EXPLOSION_HUGE, 10);
					pa.playSound(s.getLocation(), Sound.EXPLODE, 5.0F, 5.0F);
				}
				
				Random r = new Random();
				
				int data = 0;
				
				for (int i = 0; i < 25; i++) {
					ItemStack stack = new ItemStack(Material.WOOL, 1, (short) 11);
					ItemMeta meta = stack.getItemMeta();
					meta.setDisplayName(ChatColor.BLACK + "BROSCODING_FLYINGSHEEP_EFFECT_WOOL_EXPLOSION_" + r.nextInt(10000) + 0);
					stack.setItemMeta(meta);
					
					Item item = s.getWorld().dropItem(s.getLocation(), stack);
					
					c.getVariables().unpickupableItems.add(item);
					
					item.setVelocity(s.getLocation().add(-1000 + r.nextInt(1000-(-1000)), -1000 + r.nextInt(1000-(-1000)), -1000 + r.nextInt(1000-(-1000))).getDirection());
					
					Location loc = new Location(s.getWorld(), 0, 0, 0);
					loc.setYaw((float) 0 + r.nextInt(360-0));
					loc.setPitch((float) -45 + r.nextInt(0-(-45)));
					
					item.setVelocity(loc.getDirection().multiply(0.0 + (0.25 - 0.0) * r.nextDouble()).setY(0.4 + (0.7 - 0.4) * r.nextDouble()));
					
					c.getServer().getScheduler().scheduleAsyncDelayedTask(c, new Runnable() {
						public void run() {
							c.getVariables().unpickupableItems.remove(item);
							for (Player pa : c.getServer().getOnlinePlayers()) {
								pa.playEffect(item.getLocation(), Effect.LAVA_POP, 10);
							}
							item.remove();
						}
					}, 5*20L);
					
					if (data != 14) data++;
					else data = 0;
				}
				
				s.remove();
			}
		}, 5*20L);
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
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
			for (TeamColor color : TeamColor.values()) {
				objective.getScore(TeamColor.getChatColor(color) + p.getName()).setScore(i);
				
				i++;
			}
			
			for (Player pa : c.getServer().getOnlinePlayers()) {
				if (pa.getName().equals("Simmi_")) blue.addPlayer(pa);
				if (pa.getName().equals("CoffeeBunny")) red.addPlayer(pa);
			}
			
			p.setScoreboard(board);
		}
		else FeatherBoardAPI.toggle(p);
	}
}