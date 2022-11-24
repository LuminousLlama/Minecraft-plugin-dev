package me.luminous_llama.titlepacket.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.luminous_llama.titlepacket.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutTileEntityData;
import net.minecraft.server.network.PlayerConnection;

public class PacketTitle implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		if(args.length ==0) {
//			Utils.tell(sender, ChatColor.RED + "Usage: /packetTitle {players");
//		}
//		Player p = (Player) sender;
//		if(args.length >1) {
//			Player target = Bukkit.getPlayer(args[0]);
//			if(target == null) {
//				p.sendMessage(ChatColor.RED + "Player not found");
//			}else {
//				StringBuilder sb = new StringBuilder();
//				for(int i=1;i<args.length;i++) {
//					sb.append(args[i] + " ");
//				}
//				String title = Utils.chat(sb.toString().trim());
//				PlayerConnection connection = ((CraftPlayer) target.getPlayer()).getHandle().b;
//				IChatBaseComponent text = IChatBaseComponent.ChatSerializer.a("{'text': '" + title + "'}");
//				PacketPlayOutTileEntityData packet = new PacketPlayOutTileEntityData(PacketPlayOutTileEntityData.e);
//			}
//		}
		return false;
	}
}
