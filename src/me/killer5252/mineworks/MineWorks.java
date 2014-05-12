package me.killer5252.mineworks;

import me.killer5252.mineworks.command.FireworkCommand;
import me.killer5252.mineworks.config.ConfigManager;
import me.killer5252.mineworks.listener.LaunchListener;
import me.killer5252.mineworks.util.RandomFireworks;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MineWorks extends JavaPlugin implements Listener {
	
	MineWorks plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
		try{
			ConfigManager.load(this, "mineworks.yml");
			
			RandomFireworks.getManager().addColors();
			RandomFireworks.getManager().addTypes();
			
			getServer().getPluginManager().registerEvents(new LaunchListener(this), this);
			getCommand("fw").setExecutor(new FireworkCommand(this));
			
			}catch(Exception ex){
				ex.printStackTrace();
				this.getServer().getPluginManager().disablePlugin(this);
				this.consoleOut("There has been an error with the plugin. Due to that, the plugin has been disabled.");
			}
		this.consoleOut("Enabled! Enjoy all the random fireworks! :)");
	}
	
	@Override
	public void onDisable(){
		this.consoleOut("Disabled... Why you have to leave so soon? :(");
	}
	
	
	private void consoleOut(String msg) {
		System.out.println("[MineWorks]" + msg);
		
	}

}
