package me.killer5252.mineworks.command;

import me.killer5252.mineworks.MineWorks;
import me.killer5252.mineworks.config.ConfigManager;
import me.killer5252.mineworks.util.Cooldowns;
import me.killer5252.mineworks.util.RandomFireworks;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FireworkCommand implements CommandExecutor {
	
	MineWorks plugin;
	
	FileConfiguration config = ConfigManager.get("mineworks.yml");
	
	String prefix = ChatColor.DARK_PURPLE + "[" + ChatColor.GOLD + "MineWorks" + ChatColor.DARK_PURPLE + "] ";
	
	public FireworkCommand(MineWorks instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Must be a player.");
        }
		
		if(cmd.getName().equalsIgnoreCase("fw") && p.hasPermission("MineWorks.firework")){
			String launchmsg = config.getString("LaunchMessage").replaceAll("&", "\u00A7");
			Location loc = p.getLocation();
			
			if(config.getBoolean("FireworkCooldown") == true){
				if(Cooldowns.tryCooldown(p, "Firework", config.getInt("CooldownTime"))){
					RandomFireworks.getManager().launchRandomFirework(loc);
					if(config.getBoolean("LaunchChatMessage") == true){
						p.sendMessage(prefix + launchmsg);
					}
				}else{
					p.sendMessage(prefix + ChatColor.RED + "You must wait " + ChatColor.BLACK + "[" +  ChatColor.GOLD + (Cooldowns.getCooldown(p, "Firework") / 1000) + ChatColor.BLACK + "]" + ChatColor.RED + " seconds before you can launch again.");  
				}
			}else if(config.getBoolean("FireworkCooldown") == false){
				
				RandomFireworks.getManager().launchRandomFirework(loc);
				
				if(config.getBoolean("LaunchChatMessage") == true){
					p.sendMessage(prefix + launchmsg);
				}
			}
		}else{
			p.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
		}
		return false;
	}
}
