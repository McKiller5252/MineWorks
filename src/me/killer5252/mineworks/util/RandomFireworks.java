package me.killer5252.mineworks.util;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;



public class RandomFireworks {
	
	private static RandomFireworks fireWorks = new RandomFireworks();
	
	Random rnd = new Random();
	
	public static RandomFireworks getManager(){
		return fireWorks;
	}
	private Color[] colors = { Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, 
			Color.GREEN, Color.LIME, Color.MAROON, Color.OLIVE, Color.ORANGE, 
			Color.PURPLE, Color.RED, Color.TEAL, Color.WHITE, Color.YELLOW };
	
	public void launchRandomFirework(Location loc){
		Firework fw = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fm = fw.getFireworkMeta();
        FireworkEffect.Builder effect = FireworkEffect.builder();
        effect.flicker(this.rnd.nextInt(10) > 2);
        effect.trail(this.rnd.nextInt(10) > 2);
        FireworkEffect.Type[] types = FireworkEffect.Type.values();
        effect.with(types[this.rnd.nextInt(types.length)]);
        int colorcnt = this.rnd.nextInt(3) + 2;
        for (int i = 0; i < colorcnt; i++) {
          effect.withColor(this.colors[this.rnd.nextInt(this.colors.length)]);
        }
        fm.addEffect(effect.build());
        int rp = rnd.nextInt(2) + 1;
		fm.setPower(rp);
        fw.setFireworkMeta(fm);
	}
}
