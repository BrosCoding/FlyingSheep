package com.broscoding.flyingsheep.util;

import org.bukkit.ChatColor;

public enum TeamColor {
	
	WHITE,
	PINK,
	GRAY,
	GREEN,
	BLUE,
	YELLOW,
	ORANGE,
	RED,
	CYAN,
	PURPLE,
	DARK_BLUE,
	DARK_GREEN,
	BROWN,
	DARK_GRAY;
	
	public static ChatColor getChatColor(TeamColor color) {
		if (color == WHITE) return ChatColor.WHITE;
		if (color == PINK) return ChatColor.LIGHT_PURPLE;
		if (color == GRAY) return ChatColor.GRAY;
		if (color == GREEN) return ChatColor.GREEN;
		if (color == BLUE) return ChatColor.BLUE;
		if (color == YELLOW) return ChatColor.YELLOW;
		if (color == ORANGE) return ChatColor.GOLD;
		if (color == RED) return ChatColor.DARK_RED;
		if (color == CYAN) return ChatColor.DARK_AQUA;
		if (color == PURPLE) return ChatColor.DARK_PURPLE;
		if (color == DARK_BLUE) return ChatColor.DARK_BLUE;
		if (color == DARK_GREEN) return ChatColor.DARK_GREEN;
		if (color == BROWN) return ChatColor.RED;
		if (color == DARK_GRAY) return ChatColor.DARK_GRAY;
		else return ChatColor.MAGIC;
	}
}
