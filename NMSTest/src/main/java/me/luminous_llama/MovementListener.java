package me.luminous_llama;

import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class MovementListener implements Listener{

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		CraftPlayer craftplayer = (CraftPlayer) p;
		ServerPlayer serverPlayer = craftplayer.getHandle();
		
		ServerGamePacketListenerImpl listener = serverPlayer.connection;
		
	}
}
