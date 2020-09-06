package br.com.drakenetwork.hub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener
{

	@EventHandler
	public void onDropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("earthsurvival.grupo.gerente")) {
			e.setCancelled(true);
		}
		return;
	}
}
