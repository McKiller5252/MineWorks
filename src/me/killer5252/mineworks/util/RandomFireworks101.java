package me.killer5252.mineworks.util;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class RandomFireworks101 {
	
	private static RandomFireworks101 fireWorks = new RandomFireworks101();
	
	public static RandomFireworks101 getManager(){
		return fireWorks;
	}

	Random rnd = new Random();
	private Color[] colors = { Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, 
			Color.GREEN, Color.LIME, Color.MAROON, Color.OLIVE, Color.ORANGE, 
			Color.PURPLE, Color.RED, Color.TEAL, Color.WHITE, Color.YELLOW };
	
	public void launchRandomFirework(Location loc){
		Firework fw = loc.getWorld().spawn(loc, Firework.class);
		FireworkMeta fm = fw.getFireworkMeta();
                FireworkEffect.Builder effect = FireworkEffect.builder();
                FireworkEffect.Type[] types = FireworkEffect.Type.values();
        
         int colorcnt = rnd.nextInt(3) + 2;
         int rp = rnd.nextInt(4) + 1;
        
           effect.flicker(rnd.nextInt(10) > 2);
           effect.trail(rnd.nextInt(10) > 2);
           effect.with(types[rnd.nextInt(types.length)]);
       
        for (int i = 0; i < colorcnt; i++){
        	effect.withColor(colors[rnd.nextInt(colors.length)]);
        }
        
          fm.addEffect(effect.build());
          fm.setPower(rp);
          fw.setFireworkMeta(fm);
    }
}
