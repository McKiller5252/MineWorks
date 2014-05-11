package me.killer5252.mineworks.listener;



import me.killer5252.mineworks.MineWorks;
import me.killer5252.mineworks.config.ConfigManager;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LaunchListener implements Listener {
	
	MineWorks plug;
	
	FileConfiguration config = ConfigManager.get("mineworks.yml");

    public LaunchListener(MineWorks instance) {
		plug = instance;
	}
    
    @EventHandler
    public void join(PlayerJoinEvent event){
    	Player p = event.getPlayer();
    	
    	if(config.getBoolean("GiveFirework") == true){
    		spawnFirework(p);
    		
    	}
    	
    }

	private void spawnFirework(Player p) {
		String fwname = config.getString("FireworkName").replaceAll("&", "\u00A7");
		if(!p.getInventory().contains(Material.FIREWORK)){
			ItemStack spawnItem = new ItemStack(Material.FIREWORK);
			 ItemMeta im =  spawnItem.getItemMeta();
			 im.setDisplayName(fwname);
			 spawnItem.setItemMeta(im);
			 p.getInventory().setItem(config.getInt("FireworkSlot"), spawnItem);
		}
	}

	@EventHandler
    public void onInteract(PlayerInteractEvent event) {
    	Player p = event.getPlayer();
    	 if (p.getItemInHand().getType() == Material.FIREWORK) {
             if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                 event.setCancelled(true);
             }
             if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK && p.hasPermission("MineWorks.firework")){
            	 p.performCommand("fw");
             }
    	 }
    }
}
