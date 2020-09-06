package br.com.drakenetwork.hub.itens;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;
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
import br.com.drakenetwork.hub.utils.ItemBuilder;

public class LobbySelectorItem implements Listener
{
	
	public void giveItem(Player p, int slot)
	{
		p.getInventory().setItem(slot, new ItemBuilder(Material.NETHER_STAR)
				.name("§aLobbies")
				.build());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	void useItem(PlayerInteractEvent e)
	{
		if (e.getPlayer().getItemInHand() != null && !e.getPlayer().getItemInHand().equals(new ItemBuilder(Material.NETHER_STAR)
				.name("§aLobbies")
				.build()))
		{
			return;
		}
		e.setCancelled(true);
		e.setUseInteractedBlock(Result.DENY);
		Inventory inv = Bukkit.createInventory(null, 3 * 9, "Lobbies");
		
		br.com.drakenetwork.hub.servers.lobby_1 lobby_1 = new br.com.drakenetwork.hub.servers.lobby_1();
		br.com.drakenetwork.hub.servers.lobby_2 lobby_2 = new br.com.drakenetwork.hub.servers.lobby_2();
		br.com.drakenetwork.hub.servers.lobby_3 lobby_3 = new br.com.drakenetwork.hub.servers.lobby_3();
		br.com.drakenetwork.hub.servers.lobby_4 lobby_4 = new br.com.drakenetwork.hub.servers.lobby_4();
		br.com.drakenetwork.hub.servers.lobby_5 lobby_5 = new br.com.drakenetwork.hub.servers.lobby_5();
		
		Server server = e.getPlayer().getServer();
		
		if (lobby_1.isSimilar(server.getIp(), server.getPort())) {
			inv.setItem(10, new ItemBuilder(Material.INK_SACK)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.durability((lobby_1.getStatus() ? 10 : 8))
					.name("§aLobby " + 1)
					.lore("§fJogadores: §7" +  + lobby_1.getPlayers() + "/" + lobby_1.getMaxPlayers(),
							"",
							(lobby_1.getStatus() ? (lobby_1.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		} else {
			inv.setItem(10, new ItemBuilder(Material.INK_SACK)
					.removeAttributes()
					.durability((lobby_1.getStatus() ? 10 : 8))
					.name("§aLobby " + 1)
					.lore("§fJogadores: §7" +  + lobby_1.getPlayers() + "/" + lobby_1.getMaxPlayers(),
							"",
							(lobby_1.getStatus() ? (lobby_1.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		}
		if (lobby_2.isSimilar(server.getIp(), server.getPort())) {
			inv.setItem(11, new ItemBuilder(Material.INK_SACK)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.durability((lobby_2.getStatus() ? 10 : 8))
					.name("§aLobby " + 2)
					.lore("§fJogadores: §7" +  + lobby_2.getPlayers() + "/" + lobby_2.getMaxPlayers(),
							"",
							(lobby_2.getStatus() ? (lobby_2.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		} else {
			inv.setItem(11, new ItemBuilder(Material.INK_SACK)
					.removeAttributes()
					.durability((lobby_2.getStatus() ? 10 : 8))
					.name("§aLobby " + 2)
					.lore("§fJogadores: §7" +  + lobby_2.getPlayers() + "/" + lobby_2.getMaxPlayers(),
							"",
							(lobby_2.getStatus() ? (lobby_2.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		}
		if (lobby_3.isSimilar(server.getIp(), server.getPort())) {
				inv.setItem(12, new ItemBuilder(Material.INK_SACK)
						.enchant(Enchantment.DURABILITY, 1)
						.removeAttributes()
						.durability((lobby_3.getStatus() ? 10 : 8))
						.name("§aLobby " + 3)
						.lore("§fJogadores: §7" +  + lobby_3.getPlayers() + "/" + lobby_3.getMaxPlayers(),
								"",
								(lobby_3.getStatus() ? (lobby_3.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
						.build());
		} else {
			inv.setItem(12, new ItemBuilder(Material.INK_SACK)
					.removeAttributes()
					.durability((lobby_3.getStatus() ? 10 : 8))
					.name("§aLobby " + 3)
					.lore("§fJogadores: §7" +  + lobby_3.getPlayers() + "/" + lobby_3.getMaxPlayers(),
							"",
							(lobby_3.getStatus() ? (lobby_3.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		}
		if (lobby_4.isSimilar(server.getIp(), server.getPort())) {
			inv.setItem(13, new ItemBuilder(Material.INK_SACK)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.durability((lobby_4.getStatus() ? 10 : 8))
					.name("§aLobby " + 4)
					.lore("§fJogadores: §7" +  + lobby_4.getPlayers() + "/" + lobby_4.getMaxPlayers(),
							"",
							(lobby_4.getStatus() ? (lobby_4.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		} else {
			inv.setItem(13, new ItemBuilder(Material.INK_SACK)
					.removeAttributes()
					.durability((lobby_4.getStatus() ? 10 : 8))
					.name("§aLobby " + 4)
					.lore("§fJogadores: §7" +  + lobby_4.getPlayers() + "/" + lobby_4.getMaxPlayers(),
							"",
							(lobby_4.getStatus() ? (lobby_4.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		}
		if (lobby_5.isSimilar(server.getIp(), server.getPort())) {
			inv.setItem(14, new ItemBuilder(Material.INK_SACK)
					.enchant(Enchantment.DURABILITY, 1)
					.removeAttributes()
					.durability((lobby_5.getStatus() ? 10 : 8))
					.name("§aLobby " + 5)
					.lore("§fJogadores: §7" +  + lobby_5.getPlayers() + "/" + lobby_5.getMaxPlayers(),
							"",
							(lobby_5.getStatus() ? (lobby_5.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		} else {
			inv.setItem(14, new ItemBuilder(Material.INK_SACK)
					.removeAttributes()
					.durability((lobby_5.getStatus() ? 10 : 8))
					.name("§aLobby " + 5)
					.lore("§fJogadores: §7" +  + lobby_5.getPlayers() + "/" + lobby_5.getMaxPlayers(),
							"",
							(lobby_5.getStatus() ? (lobby_5.isSimilar(server.getIp(), server.getPort()) ? "§eVocê está neste lobby!" :"§eClique para entrar") : "§cLobby offline!"))
					.build());
		}
		
		e.getPlayer().openInventory(inv);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	void clickInventory(InventoryClickEvent e)
	{
		Inventory inv = e.getInventory();
		Inventory clicked = e.getClickedInventory();
		ItemStack item = e.getCurrentItem();
		Player p = (Player)e.getWhoClicked();
		
		if (inv.getName().equalsIgnoreCase("Lobbies") && item != null && item.getTypeId() != 0)
		{
			e.setCancelled(true);
			e.setResult(Result.DENY);
			if (clicked.getName().equalsIgnoreCase("Lobbies") && item != null && item.getTypeId() != 0)
			{
				DHub.instance.getServer().getMessenger().registerOutgoingPluginChannel(DHub.plugin, "BungeeCord");
	            final ByteArrayOutputStream b = new ByteArrayOutputStream();
	            final DataOutputStream out = new DataOutputStream(b);

	    		br.com.drakenetwork.hub.servers.lobby_1 lobby_1 = new br.com.drakenetwork.hub.servers.lobby_1();
	    		br.com.drakenetwork.hub.servers.lobby_2 lobby_2 = new br.com.drakenetwork.hub.servers.lobby_2();
	    		br.com.drakenetwork.hub.servers.lobby_3 lobby_3 = new br.com.drakenetwork.hub.servers.lobby_3();
	    		br.com.drakenetwork.hub.servers.lobby_4 lobby_4 = new br.com.drakenetwork.hub.servers.lobby_4();
	    		br.com.drakenetwork.hub.servers.lobby_5 lobby_5 = new br.com.drakenetwork.hub.servers.lobby_5();
	    		
	            
	            switch (e.getRawSlot())
				{
				case 10:
					if (lobby_1.getStatus() == false) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
						return;
					}
					try {
						out.writeUTF("Connect");
						out.writeUTF("hub-1");
						p.closeInventory();
					} catch (Exception ex) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
					}
					p.sendPluginMessage(DHub.plugin, "BungeeCord", b.toByteArray());
					break;
				case 11:
					if (lobby_2.getStatus() == false) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
						return;
					}
					try {
						out.writeUTF("Connect");
						out.writeUTF("hub-2");
						p.closeInventory();
					} catch (Exception ex) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
					}
					p.sendPluginMessage(DHub.plugin, "BungeeCord", b.toByteArray());
					break;
				case 12:
					if (lobby_3.getStatus() == false) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
						return;
					}
					try {
						out.writeUTF("Connect");
						out.writeUTF("hub-3");
						p.closeInventory();
					} catch (Exception ex) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
					}
					p.sendPluginMessage(DHub.plugin, "BungeeCord", b.toByteArray());
					break;
				case 13:
					if (lobby_4.getStatus() == false) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
						return;
					}
					try {
						out.writeUTF("Connect");
						out.writeUTF("hub-4");
						p.closeInventory();
					} catch (Exception ex) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
					}
					p.sendPluginMessage(DHub.plugin, "BungeeCord", b.toByteArray());
					break;
				case 14:
					if (lobby_5.getStatus() == false) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
						return;
					}
					try {
						out.writeUTF("Connect");
						out.writeUTF("hub-5");
						p.closeInventory();
					} catch (Exception ex) {
						p.sendMessage("§cEste servidor está offline!");
						p.closeInventory();
					}
					p.sendPluginMessage(DHub.plugin, "BungeeCord", b.toByteArray());
					break;
				}
			}
		}
	}
}
