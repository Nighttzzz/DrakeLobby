package br.com.drakenetwork.hub.chat;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class ChatBuilder
{

	public void sendChat(Player p, String message)
	{
		if (p.hasPermission("drakenetwork.grupos.vip"))
		{
			String prefix = PermissionsEx.getUser(p).getPrefix();
			if (p.hasPermission("drakenetwork.grupos.gerente"))
			{
				Bukkit.broadcastMessage("");
			}
			Bukkit.broadcastMessage(prefix.replaceAll("&", "§") + "§f" + p.getName() + "§7: §f" + message);
			if (p.hasPermission("drakenetwork.grupos.gerente"))
			{
				Bukkit.broadcastMessage("");
			}
			return;
		} else {
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
			return;
		}
	}
}
