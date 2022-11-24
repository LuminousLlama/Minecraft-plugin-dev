package me.luminous_llama.randomtp;

import org.bukkit.plugin.java.JavaPlugin;

import me.luminous_llama.randomtp.Utils.TPUtils;
import me.luminous_llama.randomtp.commands.RandomTPCommand;

public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		
		TPUtils tpUtils = new TPUtils(this);
		getCommand("rtp").setExecutor(new RandomTPCommand());
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
	}
}
