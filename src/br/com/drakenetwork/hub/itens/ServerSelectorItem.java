package br.com.drakenetwork.hub.itens;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import br.com.drakenetwork.hub.DHub;
import br.com.drakenetwork.hub.servers.*;
import br.com.drakenetwork.hub.utils.ItemBuilder;

public class ServerSelectorItem implements Listener {

	public void giveItem(Player p, int slot) {
		p.getInventory().setItem(slot, new ItemBuilder(Material.COMPASS)
				.name("§bServidores")
				.build());
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	void useItem(PlayerInteractEvent e) {

		old old = new old();
		wither wither = new wither();
		glass glass = new glass();
		
		if (e.getPlayer().getItemInHand() != null && !e.getPlayer().getItemInHand().equals(new ItemBuilder(Material.COMPASS)
				.name("§bServidores")
				.build()))
		{
			return;
		}
		e.setCancelled(true);
		e.setUseInteractedBlock(Result.DENY);
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "Servidores");
		

		Bukkit.getScheduler().scheduleAsyncRepeatingTask(DHub.plugin, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(11, new ItemBuilder(Material.TNT)
						.name("§6Factions Classic")
						.lore("§r",
								"§e - §fArena de batalha;",
								"§e - §fMundo customizado;",
								"§e - §fCaixas Misteriosas;",
								"§r",
								"§fJogadores: §7" + old.getPlayers() + "/" + old.getMaxPlayers(),
								(old.getStatus() ? "§eClique para entrar!" : "§cServidor reiniciando!"))
				.build());
			}
		}, 0L, 10*20L);
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(DHub.plugin, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(13, new ItemBuilder(Material.DIAMOND_CHESTPLATE)
						.glow()
						.name("§6Factions OP")
						.lore("§r",
								"§e - §fSpawners;",
								"§e - §fMundo 5.000 x 5.000;",
								"§e - §fMundo customizado;",
								"§e - §fCaixas Misteriosas;",
								"§e - §fArena de batalha;",
								"§e - §fArena X1 no spawn;",
								"§e",
								"§c * Focado em batalhas!",
								"§r",
								"§fJogadores: §7" + glass.getPlayers() + "/" + glass.getMaxPlayers(),
								(glass.getStatus() ? "§eClique para entrar!" : "§cServidor reiniciando!"))
				.build());
			}
		}, 0L, 10*20L);
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(DHub.plugin, new Runnable() {
			
			@Override
			public void run() {
				inv.setItem(15, new ItemBuilder(Material.SKULL_ITEM)
						.durability(1)
						.name("§6Factions Island")
						.lore("§r",
								"§e - §fMundo customizado;",
								"§e - §fCaixas Misteriosas;",
								"§e - §fSistema de habilidades;",
								"§e - §fSistema de x1;",
								"§e - §fSistema de rankup;",
								"§e - §fSistema de efeitos exclusivo;",
								"§r",
								"§fJogadores: §7" + wither.getPlayers() + "/" + wither.getMaxPlayers(),
								(wither.getStatus() ? "§eClique para entrar!" : "§cServidor reiniciando!"))
				.build());
			}
		}, 0L, 10*20L);
		e.getPlayer().openInventory(inv);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	void clickInventory(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Inventory clicked = e.getClickedInventory();
		ItemStack item = e.getCurrentItem();
		Player p = (Player)e.getWhoClicked();
		
		if (inv.getName().equalsIgnoreCase("Servidores") && item != null && item.getTypeId() != 0) {
			e.setCancelled(true);
			e.setResult(Result.DENY);
			if (clicked.getName().equalsIgnoreCase("Servidores") && item != null && item.getTypeId() != 0) {
				switch (e.getRawSlot()) {
				case 11:
					p.chat("/join entrar factions-1");
					break;
				case 13:
					p.chat("/join entrar factions-3");
					break;
				case 15:
					p.chat("/join entrar factions-2");
					break;
				}
			}
		}
	}
}
