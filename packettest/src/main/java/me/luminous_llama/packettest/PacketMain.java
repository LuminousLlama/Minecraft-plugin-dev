package me.luminous_llama.packettest;



import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

public class PacketMain extends JavaPlugin{

	@Override
	public void onEnable() {
		
		getCommand("boom").setExecutor(new BoomCommand());
		
		ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		
//		manager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Client.POSITION) {
//			
//			@Override
//			public void onPacketReceiving(PacketEvent e) {
//				PacketContainer packet = e.getPacket();
//				Player p = e.getPlayer();
//				
//				double x =packet.getDoubles().read(0);
//				double y =packet.getDoubles().read(1);
//				double z =packet.getDoubles().read(2);
//				boolean isOnGround = packet.getBooleans().read(0);
//				
//				//p.sendMessage("INCOMMING PACKET:" + " x: " + x + " y: " +y+ " z: " + z + " isOnGround: " + isOnGround);
//			}
//		});
//		
//		manager.addPacketListener(new PacketAdapter(this, PacketType.Play.Server.REL_ENTITY_MOVE){
//			
//			@Override
//			public void onPacketSending(PacketEvent e) {
//				PacketContainer packet = e.getPacket();
//				Player p= e.getPlayer();
//				
//				short x = packet.getShorts().read(0);
//				short y = packet.getShorts().read(1);
//				short z = packet.getShorts().read(2);
//				int entityID = packet.getIntegers().read(0);
//				
//				Entity entity = manager.getEntityFromID(p.getWorld(), entityID);
//				entity.teleport(p.getLocation());
//				
//				p.sendMessage("INCOMMING PACKET:" + " x: " + x + " y: " +y+ " z: " + z );
//			}
//			
//		});
		
//		manager.addPacketListener(new PacketAdapter(this, PacketType.Play.Client.CHAT) {
//			@Override 
//			public void onPacketReceiving(PacketEvent e) {
//				e.setCancelled(true);
//			}
//		});
		
	}
}
