package br.com.drakenetwork.hub.tags;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import br.com.drakenetwork.hub.DHub;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("drakenetwork.grupos.vip")) {
			p.setAllowFlight(true);
		}
		DHub.toggle.put(p.getName(), false);
	}
}
