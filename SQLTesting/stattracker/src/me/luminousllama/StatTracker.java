package me.luminousllama;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.luminousllama.database.Database;
import me.luminousllama.listeners.StatUpdateListeners;

public class StatTracker extends JavaPlugin {

    StatTracker plugin;
    Database db;

    @Override
    public void onEnable() {
        this.plugin = this;
        this.db = new Database();

        Bukkit.getPluginManager().registerEvents(new StatUpdateListeners(), plugin);

        try {
            db.initializeDatabase();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

    }

    public Database getDb() {
        return db;
    }

    public StatTracker getPlugin() {
        return plugin;
    }
}
