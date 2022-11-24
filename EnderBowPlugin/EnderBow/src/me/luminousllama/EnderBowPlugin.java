package me.luminousllama;



import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server.Spigot;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.util.EnumUtils;

import me.luminousllama.commands.EnderBowCommand;
import me.luminousllama.config.Config;
import me.luminousllama.events.EnderBowShootEvent;
import me.luminousllama.utils.EnderBowUtils;

public class EnderBowPlugin extends JavaPlugin{
    private final Logger logger = Logger.getLogger("EnderBow");

    private static Config config;

    //make config class 

    private static EnderBowPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;  
        config = new Config();

        Bukkit.getPluginManager().registerEvents(new EnderBowShootEvent(), this);
        this.getCommand("enderbow").setExecutor(new EnderBowCommand());

        EnderBowUtils.registerEnderBowRecipe();
        EnderBowUtils.registerPermissions();
    }
    
    @Override
    public void onDisable() {
        EnderBowUtils.unregisterEnderBowRecipe();
        EnderBowUtils.unregisterPermissions();
    }

    public static EnderBowPlugin getInstance(){
        return plugin;
    }

    public static Config getInternalConfig(){
        return config;
    }
   
}
