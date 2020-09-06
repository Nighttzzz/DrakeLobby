package br.com.drakenetwork.hub.packetwrapper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import br.com.drakenetwork.hub.DHub;

public class TabListPacket
{

	public void sendTablist(Player p)
	{
		PacketContainer pc = DHub.protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
		pc.getChatComponents()
			.write(0, WrappedChatComponent.fromText("\n§6§lDRAKE NETWORK"
				+ "\n"
				+ "§6    §edrakenetwork.com.br"
				+ "\n§r"))
			.write(1,
				WrappedChatComponent.fromText("\n§6Fórum: §eforum.drakenetwork.com.br/forum"
				+ "\n§6Team Speak 3: §ets.drakenetwork.com.br"
				+ "\n§6Twitter: §etwitter.com/RedeDrake"
				+ "\n§r"
				+ "\n§6    §6    §6    §6    §6Adquira VIP e CASH acessando: §eloja.drakenetwork.com.br    §6    §6    §6    §6"
				+ "\n§r"));
		try {
			DHub.protocolManager.sendServerPacket(p, pc);
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§c§lERRO: §r§c" + e.getMessage());
		}
	}
}
