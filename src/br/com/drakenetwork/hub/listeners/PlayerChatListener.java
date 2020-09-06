package br.com.drakenetwork.hub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import br.com.drakenetwork.hub.chat.ChatBuilder;

public class PlayerChatListener implements Listener
{

	private ChatBuilder chat = new ChatBuilder();
	
	@EventHandler(ignoreCancelled = false)
	public void onChat(AsyncPlayerChatEvent e) {
		if (e.isCancelled()) return;
		Player p = e.getPlayer();
		String message = e.getMessage();
		if (!p.hasPermission("earthsurvival.grupo.gerente")) {
			e.setCancelled(true);
		}
		e.setCancelled(true);
		chat.sendChat(p, message);
	}
}
