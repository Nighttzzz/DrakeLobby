package br.com.drakenetwork.hub.itens;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import br.com.drakenetwork.hub.DHub;
import br.com.drakenetwork.hub.listeners.PlayerDamageListener;
import br.com.drakenetwork.hub.utils.ItemBuilder;

public class PreferencesItem implements Listener 
{

	private PlayerVisibilityItem visibility = new PlayerVisibilityItem();
	private LobbySelectorItem lobby = new LobbySelectorItem();
	
	public void giveItem(Player p, int slot)
	{
		p.getInventory().setItem(slot, new ItemBuilder(Material.REDSTONE_COMPARATOR)
				.name("§cPreferências")
				.build());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	void useItem(PlayerInteractEvent e)
	{
		if (e.getPlayer().getItemInHand() != null && !e.getPlayer().getItemInHand().equals(new ItemBuilder(Material.REDSTONE_COMPARATOR)
				.name("§cPreferências")
				.build()))
		{
			return;
		}
		e.setCancelled(true);
		e.setUseInteractedBlock(Result.DENY);
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "Preferências");
		Player p = e.getPlayer();
		
		inv.setItem(3, new ItemBuilder(Material.WATCH)
				.name("§aVisibilidade")
				.lore("§7Ver outros jogadores nos lobbies.")
				.build());
		inv.setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE)
				.name("§aVisibilidade")
				.durability((DHub.isToogle(p.getName()) ? 14 : 5))
				.lore("§7Estado: " + (DHub.isToogle(p.getName()) ? "§cDesligado" : "§aLigado"))
				.build());
		inv.setItem(4, new ItemBuilder(Material.DIAMOND_SWORD)
				.name("§aPvP No Hub")
				.lore("§7Ativar o pvp contra outros jogadores no lobby.")
				.build());
		
		boolean has = PlayerDamageListener.pvp.containsKey(p.getName());
		
		boolean pvp = (has ? PlayerDamageListener.pvp.get(p.getName()) : false);
		
		inv.setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE)
				.name("§aPvP No hub")
				.durability((pvp ? 5 : 14))
				.lore("§7Estado: " + (pvp ? "§aLigado" : "§cDesligado"))
				.build());
		
		p.openInventory(inv);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	void clickInventory(InventoryClickEvent e)
	{
		Inventory inv = e.getInventory();
		Inventory clicked = e.getClickedInventory();
		ItemStack item = e.getCurrentItem();
		Player p = (Player)e.getWhoClicked();
		
		if (inv.getName().equalsIgnoreCase("Preferências") && item != null && item.getTypeId() != 0)
		{
			e.setCancelled(true);
			e.setResult(Result.DENY);
			if (clicked.getName().equalsIgnoreCase("Preferências") && item != null && item.getTypeId() != 0)
			{
				switch (e.getRawSlot()) {
				case 12:
					if (DHub.isToogle(p.getName())) {
						DHub.toggle(p.getName(), false);
					} else {
						DHub.toggle(p.getName(), true);
					}
					clicked.setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE)
							.name("§aVisibilidade")
							.durability((DHub.isToogle(p.getName()) ? 14 : 5))
							.lore("§7Estado: " + (DHub.isToogle(p.getName()) ? "§cDesligado" : "§aLigado"))
							.build());
					for (Player on : Bukkit.getOnlinePlayers()) {
						if (DHub.isToogle(p.getName())) {
							p.hidePlayer(on);
						} else {
							p.showPlayer(on);
						}
					}
					visibility.giveItem(p, 3);
					break;
				case 13:
					boolean has = PlayerDamageListener.pvp.containsKey(p.getName());
					
					boolean pvp = (has ? PlayerDamageListener.pvp.get(p.getName()) : true);
					
					if (pvp) {
						PlayerDamageListener.pvp.put(p.getName(), false);
						p.setAllowFlight(true);
						p.setFlying(true);
					} else {
						PlayerDamageListener.pvp.put(p.getName(), true);
						p.setAllowFlight(false);
						p.setFlying(false);
					}
					
					pvp = (has ? PlayerDamageListener.pvp.get(p.getName()) : true);
					
					Inventory pinv = p.getInventory();
					
					inv.setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE)
							.name("§aPvP no hub")
							.durability((pvp ? 5 : 14))
							.lore("§7Estado: " + (pvp ? "§aLigado" : "§cDesligado"))
							.build());

					if (pvp) {
						pinv.setItem(3, null);
						pinv.setItem(5, null);
						p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
						p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
						p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
						p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
						pinv.setItem(4, new ItemStack(Material.DIAMOND_SWORD));
						p.updateInventory();
						p.setGameMode(GameMode.ADVENTURE);
						p.setAllowFlight(false);
						p.setFlying(false);
					} else {
						p.getInventory().setHelmet(null);
						p.getInventory().setChestplate(null);
						p.getInventory().setLeggings(null);
						p.getInventory().setBoots(null);
						pinv.setItem(4, null);
						p.setGameMode(GameMode.SURVIVAL);
						visibility.giveItem(p, 3);
						lobby.giveItem(p, 5);
						p.setAllowFlight(true);
						p.setFlying(true);
					}
					break;
				}
			}
		}
	}
}
