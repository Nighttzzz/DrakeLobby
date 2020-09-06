package br.com.drakenetwork.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener
{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void click(InventoryClickEvent e) {
		if (e.getClickedInventory() == e.getWhoClicked().getInventory() && e.getCurrentItem() != null && e.getCurrentItem().getTypeId() !=0)
		{
			Player p = (Player) e.getWhoClicked();
			if (!p.hasPermission("earthsurvival.grupo.gerente")) {
				e.setCancelled(true);
				e.setResult(Result.DENY);
			}
			return;
		}
	}
}
