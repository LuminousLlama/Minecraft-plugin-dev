package me.luminous_llama.titlepacket;

import org.bukkit.plugin.java.JavaPlugin;

import me.luminous_llama.titlepacket.commands.PacketTitle;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		getCommand("packetTitle").setExecutor(new PacketTitle());
	}
}
