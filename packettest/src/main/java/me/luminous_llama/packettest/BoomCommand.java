package me.luminous_llama.packettest;



import java.lang.reflect.InvocationTargetException;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Play;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

public class BoomCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command");
			return true;
		}
		
		Player p = (Player) sender;
		
		ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		
		p.getLineOfSight(null, 50).stream()
		.forEach(block -> System.out.println(block.toString()));
//			.filter(block -> block.getType() != Material.AIR)
//			
//			.forEach(block -> {
//				
//				System.out.println(block.toString());
//				Location blockLocation = block.getLocation();
//				
//				PacketContainer packet = manager.createPacket(PacketType.Play.Server.EXPLOSION);
//				packet.getDoubles().write(0, blockLocation.getX()+0.5);
//				packet.getDoubles().write(1, blockLocation.getY()+0.5);
//				packet.getDoubles().write(2, blockLocation.getZ()+0.5);
//				
//				packet.getFloat().write(0, 5.0f);
//				packet.getFloat().write(2, 3.0f);
//				
//				try {
//					manager.sendServerPacket(p, packet);
//				} catch (InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
//			});
		
		return true;
	}
}
