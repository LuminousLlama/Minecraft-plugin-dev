package me.luminous_llama.armorstandgui;


import org.bukkit.plugin.java.JavaPlugin;

import me.luminous_llama.armorstandgui.commands.ArmorStandCommand;
import me.luminous_llama.armorstandgui.events.ArmorMenu;
import me.luminous_llama.armorstandgui.events.ConfirmMenu;
import me.luminous_llama.armorstandgui.events.CreateMenu;
import me.luminous_llama.armorstandgui.events.MainMenu;
import me.luminous_llama.armorstandgui.events.WeaponMenu;

public class Main extends JavaPlugin{

	
	
	
	@Override
	public void onEnable() {
		System.out.println("Plugin has started");
		
		getCommand("armorstand").setExecutor(new ArmorStandCommand());
		
		//getServer().getPluginManager().registerEvents(new MenuHandler(this), this);
		getServer().getPluginManager().registerEvents(new ArmorMenu(), this);
		getServer().getPluginManager().registerEvents(new ConfirmMenu(), this);
		getServer().getPluginManager().registerEvents(new CreateMenu(), this);
		getServer().getPluginManager().registerEvents(new MainMenu(), this);
		getServer().getPluginManager().registerEvents(new WeaponMenu(), this);
	}
}
