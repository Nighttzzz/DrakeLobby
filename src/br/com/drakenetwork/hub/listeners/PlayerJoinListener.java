package br.com.drakenetwork.hub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.drakenetwork.hub.DHub;
import br.com.drakenetwork.hub.itens.LobbySelectorItem;
import br.com.drakenetwork.hub.itens.PlayerVisibilityItem;
import br.com.drakenetwork.hub.itens.PreferencesItem;
import br.com.drakenetwork.hub.itens.ServerSelectorItem;
import br.com.drakenetwork.hub.packetwrapper.TabListPacket;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerJoinListener implements Listener
{

	private TabListPacket tablist = new TabListPacket();
	private LobbySelectorItem lobby = new LobbySelectorItem();
	private ServerSelectorItem server = new ServerSelectorItem();
	private PreferencesItem preferences = new PreferencesItem();
	private PlayerVisibilityItem visibility = new PlayerVisibilityItem();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		DHub.toggle.put(p.getName(), false);
		p.getInventory().clear();
		p.getInventory().setHeldItemSlot(0);
		tablist.sendTablist(p);
		server.giveItem(p, 1);
		lobby.giveItem(p, 5);
		visibility.giveItem(p, 3);
		preferences.giveItem(p, 7);
		p.setAllowFlight(true);
		p.setFlying(true);
		PlayerDamageListener.pvp.put(p.getName(), false);
		
		PermissionUser user = PermissionsEx.getUser(p);
		if (!user.inGroup("Membro")) user.addGroup("Membro");
	}
	

	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		e.setQuitMessage(null);
	}
}
