package br.com.drakenetwork.hub.itens;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.player.PlayerInteractEvent;

import br.com.drakenetwork.hub.DHub;
import br.com.drakenetwork.hub.utils.ItemBuilder;

public class PlayerVisibilityItem implements Listener {

	private static Map<String, Double> delay = new HashMap<>();

	public void giveItem(Player p, int slot) {
		p.getInventory().setItem(slot,
				new ItemBuilder(Material.INK_SACK).durability((DHub.isToogle(p.getName()) ? 8 : 10))
						.name("§fJogadores: " + (DHub.isToogle(p.getName()) ? "§cOFF" : "§aON"))
						.build());
	}

	public static void wait(Player player, boolean hide) {
		if (delay.containsKey(player.getName())) {
			double then = delay.get(player.getName());
			Date dateNow = new Date(Calendar.getInstance().getTimeInMillis());
			double now = dateNow.getTime();
			int timeDelay = 3 * 1000;
			if ((now - then) > timeDelay) {
				if (hide) {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						if (players.hasPermission("earthsurvival.grupos.youtuber")
								|| players.hasPermission("earthsurvival.grupos.staff")) {
							continue;
						} else {
							player.hidePlayer(players);
						}
					}
					DHub.toggle(player.getName(), false);
					player.sendMessage("§eOs jogadores não estão mais visíveis.");
					player.getInventory().setItem(3,
							new ItemBuilder(Material.INK_SACK)
									.durability((DHub.isToogle(player.getName()) ? 8 : 10))
									.name("§fJogadores: " + (DHub.isToogle(player.getName()) ? "§cOFF" : "§aON"))
									.build());
					player.updateInventory();
				} else {
					for (Player players : Bukkit.getServer().getOnlinePlayers()) {
						player.showPlayer(players);
					}
					DHub.toggle(player.getName(), true);
					player.sendMessage("§eOs jogadores estão novamente visíveis.");
					player.getInventory().setItem(3,
							new ItemBuilder(Material.INK_SACK)
									.durability((DHub.isToogle(player.getName()) ? 8 : 10))
									.name("§fJogadores: " + (DHub.isToogle(player.getName()) ? "§cOFF" : "§aON"))
									.build());
					player.updateInventory();
				}
				delay.put(player.getName(), now);
			} else if ((now - then) < timeDelay) {
				return;
			}
		} else {
			Date now = new Date(Calendar.getInstance().getTimeInMillis());
			delay.put(player.getName(), (double) now.getTime());
			if (hide) {
				for (Player players : Bukkit.getServer().getOnlinePlayers()) {
					player.hidePlayer(players);
				}
				DHub.toggle(player.getName(), false);
				player.sendMessage("§eOs jogadores não estão mais visíveis.");
				player.getInventory().setItem(3, new ItemBuilder(Material.INK_SACK)
						.durability((DHub.isToogle(player.getName()) ? 8 : 10))
						.name("§fJogadores: " + (DHub.isToogle(player.getName()) ? "§cOFF" : "§aON"))
						.build());
				player.updateInventory();
			} else {
				for (Player players : Bukkit.getServer().getOnlinePlayers()) {
					player.showPlayer(players);
				}
				DHub.toggle(player.getName(), true);
				player.sendMessage("§eOs jogadores estão novamente visíveis.");
				player.getInventory().setItem(3, new ItemBuilder(Material.INK_SACK)
						.durability((DHub.isToogle(player.getName()) ? 8 : 10))
						.name("§fJogadores: " + (DHub.isToogle(player.getName()) ? "§cOFF" : "§aON"))
						.build());
				player.updateInventory();
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	void useItem(PlayerInteractEvent e) {
		if (e.getPlayer().getItemInHand() != null && !e.getPlayer().getItemInHand()
				.equals(new ItemBuilder(Material.INK_SACK)
						.durability((DHub.isToogle(e.getPlayer().getName()) ? 8 : 10))
						.name("§fJogadores: "
								+ (DHub.isToogle(e.getPlayer().getName()) ? "§cOFF" : "§aON"))
						.build())) {
			return;
		}
		e.setCancelled(true);
		e.setUseInteractedBlock(Result.DENY);
		if (DHub.isToogle(e.getPlayer().getName())) {
			wait(e.getPlayer(), false);
			return;
		} else {
			wait(e.getPlayer(), true);
		}
	}
}
