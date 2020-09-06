package br.com.drakenetwork.hub;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import br.com.drakenetwork.hub.itens.LobbySelectorItem;
import br.com.drakenetwork.hub.itens.PlayerVisibilityItem;
import br.com.drakenetwork.hub.itens.PreferencesItem;
import br.com.drakenetwork.hub.itens.ServerSelectorItem;
import br.com.drakenetwork.hub.listeners.DropItemListener;
import br.com.drakenetwork.hub.listeners.InventoryClickListener;
import br.com.drakenetwork.hub.listeners.PlayerChatListener;
import br.com.drakenetwork.hub.listeners.PlayerCommandPreprocessListener;
import br.com.drakenetwork.hub.listeners.PlayerDamageListener;
import br.com.drakenetwork.hub.listeners.PlayerJoinListener;
import br.com.drakenetwork.hub.listeners.WeatherChangeListener;
import br.com.drakenetwork.hub.tags.PlayerJoin;

public class DHub extends JavaPlugin
{
	
	public static Plugin plugin;
	public static DHub instance;
    public static ProtocolManager protocolManager;
    public String host, user, password, database;
    public static HashMap<String, Boolean> toggle;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		instance = this;
		saveDefaultConfig();
		protocolManager = ProtocolLibrary.getProtocolManager();

		this.host = this.getConfig().getString("mysql.host");
		this.user = this.getConfig().getString("mysql.user");
		this.password = this.getConfig().getString("mysql.password");
		this.database = this.getConfig().getString("mysql.database");
		
		PlayerDamageListener.pvp = new HashMap<>();
		
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new ServerSelectorItem(), this);
		Bukkit.getPluginManager().registerEvents(new LobbySelectorItem(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerVisibilityItem(), this);
		Bukkit.getPluginManager().registerEvents(new PreferencesItem(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new DropItemListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerDamageListener.pvp.put(p.getName(), false);
		}
		toggle = new HashMap<>();
	}

	@Override
	public void onDisable() {
		
	}

	public static boolean isToogle(String nick) {
		return toggle.get(nick);
	}
	
	public static boolean toggle(String nick, boolean toggle1) {
		return toggle.put(nick, toggle1);
	}
}
