package br.com.drakenetwork.hub.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {

	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		boolean a = false;
		if (e.getMessage().toLowerCase().startsWith("/promote")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/demote")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/pex promote")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/pex demote")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/permissionsex:pex promote")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/shop")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/mineshop:")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/permissionsex:pex demote")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/permissionsex:demote")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/permissionsex:promote")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/perm")) {
			e.setCancelled(true); a = true;
		} else if (e.getMessage().toLowerCase().startsWith("/join ajuda")) {
			if (!e.getPlayer().hasPermission("asdasdsasadasasdass")) {
				e.setCancelled(true); a = true;
			}
		} else if (e.getMessage().toLowerCase().startsWith("/join listar")) {
			if (!e.getPlayer().hasPermission("asdasdsasadasasdass")) {
				e.setCancelled(true); a = true;
			}
		} else if (e.getMessage().toLowerCase().startsWith("/join prioridade")) {
			if (!e.getPlayer().hasPermission("asdasdsasadasasdass")) {
				e.setCancelled(true); a = true;
			}
		} else if (e.getMessage().toLowerCase().startsWith("/join versao")) {
			if (!e.getPlayer().hasPermission("asdasdsasadasasdass")) {
				e.setCancelled(true); a = true;
			}
		} else if (e.getMessage().toLowerCase().startsWith("/fila ajuda")) {
			if (!e.getPlayer().hasPermission("asdasdsasadasasdass")) {
				e.setCancelled(true); a = true;
			}
		} else if (e.getMessage().toLowerCase().startsWith("/fila listar")) {
			if (!e.getPlayer().hasPermission("asdasdsasadasasdass")) {
				e.setCancelled(true); a = true;
			}
		} else if (e.getMessage().toLowerCase().startsWith("/fila prioridade")) {
			if (!e.getPlayer().hasPermission("asdasdsasadasasdass")) {
				e.setCancelled(true); a = true;
			}
		} else if (e.getMessage().toLowerCase().startsWith("/fila versao")) {
			if (!e.getPlayer().hasPermission("asdasdsasadasasdass")) {
				e.setCancelled(true); a = true;
			}
		}
		if (a) {
			e.getPlayer().sendMessage("§cVocê não tem permissão para executar este comando.");
		}
	}
}
