package com.broscoding.flyingsheep.util.file;

import java.io.File;
import java.io.IOException;

import com.broscoding.flyingsheep.FlyingSheep;
import com.google.common.io.Files;

public class StatsFile {
	
	private FlyingSheep c;
	
	public StatsFile(FlyingSheep plugin) {
		c = plugin;
	}
	
	public void loadFile() {
		
		File file = new File(c.getDataFolder(), c.getVariables().fileBackend);
		if (!file.exists()) createFile(file);
	}
	
	private void createFile(File file) {
		try {
			file.createNewFile();
			Files.write("# NOTICE!\n# THIS FILE IS ONLY FOR STORAGE! DO NOT EDIT".getBytes(), file);
			
		} catch (IOException e) {
			c.log.severe("Creation of file failed! Error:");
			e.printStackTrace();
		}
	}
}