package br.com.drakenetwork.hub.listeners;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import br.com.drakenetwork.hub.itens.LobbySelectorItem;
import br.com.drakenetwork.hub.itens.PlayerVisibilityItem;
import br.com.drakenetwork.hub.itens.PreferencesItem;
import br.com.drakenetwork.hub.itens.ServerSelectorItem;

public class PlayerDamageListener implements Listener {

	private PlayerVisibilityItem visibility = new PlayerVisibilityItem();
	private LobbySelectorItem lobby = new LobbySelectorItem();
	private ServerSelectorItem server = new ServerSelectorItem();
	private PreferencesItem preferences = new PreferencesItem();
	public static HashMap<String, Boolean> pvp;
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getLocation().getY() < 0) {
				e.setCancelled(true);

				World world = p.getLocation().getWorld();
				double x = -26;
				double y = 138;
				double z = 22;
				float yaw = -130;
				float pitch = 1;
				
				Location spawn = new Location(world, x, y, z, yaw, pitch);
				p.teleport(spawn);
			}
		}
	}

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (!p.isOnline()) return;
            Player p2 = (Player) e.getDamager();
            if (!p2.isOnline()) return;
            if (pvp.get(p.getName()) == false || pvp.get(p2.getName()) == false) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
    	Player p = e.getPlayer();

    	PlayerDamageListener.pvp.remove(p.getName());
    	
		Inventory pinv = p.getInventory();
		
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		pinv.setItem(4, null);
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
    	e.getDrops().clear();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
    	Player p = e.getPlayer();
		boolean has = PlayerDamageListener.pvp.containsKey(p.getName());
		
		boolean pvp = (has ? PlayerDamageListener.pvp.get(p.getName()) : true);
		
		Inventory pinv = p.getInventory();
		
		if (pvp) {
			pinv.setItem(3, null);
			pinv.setItem(5, null);
			p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
			p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
			p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
			p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
			pinv.setItem(4, new ItemStack(Material.DIAMOND_SWORD));
			p.updateInventory();
			server.giveItem(p, 1);
			preferences.giveItem(p, 7);
			p.setAllowFlight(false);
			p.setFlying(false);
		} else {
			p.getInventory().setHelmet(null);
			p.getInventory().setChestplate(null);
			p.getInventory().setLeggings(null);
			p.getInventory().setBoots(null);
			pinv.setItem(4, null);
			server.giveItem(p, 1);
			lobby.giveItem(p, 5);
			visibility.giveItem(p, 3);
			preferences.giveItem(p, 7);
			p.setAllowFlight(true);
			p.setFlying(true);
		}
    }
}
