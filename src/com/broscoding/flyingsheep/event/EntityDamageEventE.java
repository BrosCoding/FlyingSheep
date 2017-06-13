package com.broscoding.flyingsheep.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageEventE implements Listener {
	
	//Bare en backup / sketch
	
	@EventHandler
	public void sheep(EntityDamageByEntityEvent e) {
		LivingEntity entity = (LivingEntity) e.getEntity();
		if (entity.getHealth() - e.getDamage() < 1 ) {
			e.setCancelled(true);
			entity.setHealth(entity.getMaxHealth());
			entity.damage(0);
			
			if (e.getDamager() instanceof Player) {
				entity.setVelocity(e.getDamager().getLocation().getDirection().multiply(1).setY(0.7));
			}
		}
	}
}
