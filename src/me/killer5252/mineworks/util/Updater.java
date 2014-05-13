package me.killer5252.mineworks.util;

import me.killer5252.mineworks.MineWorks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

public class Updater {
	MineWorks plugin;
	
	public String version = "1.0.1";
	public String updatemsg = "There was an error retrieving update data.";
	
	public Updater(MineWorks plugin) {
		this.plugin = plugin;
		currentVersion = version;
	}
	
	public String currentVersion;
	private String readurl = "";
	
	public void startUpdateCheck() {
		if (plugin.getConfig().getBoolean("update-checker")) {
			Logger log = plugin.getLogger();
			try {
				log.info("Checking for a new version...");
				URL url = new URL(readurl);
				BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
				String str;
				while ((str = br.readLine()) != null) {
					String line = str;
					if (line.charAt(0) == '1' && line.charAt(2) == '3') {
						updatemsg = line.substring(5);
						log.info(updatemsg);
					}
				}
				br.close();
			} catch (IOException e) {
				log.severe("The UpdateChecker URL is invalid! Please let me know!");
				
			}
		}
	}
}