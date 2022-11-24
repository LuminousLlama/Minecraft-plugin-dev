package me.luminous_llama.quartermaster;

import java.io.File;
import java.util.HashMap;

import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import me.luminous_llama.quartermaster.commands.ListCommand;
import me.luminous_llama.quartermaster.commands.LockCommand;
import me.luminous_llama.quartermaster.listeners.ChestListeners;
import me.luminous_llama.quartermaster.listeners.MenuListener;
import me.luminous_llama.quartermaster.utils.LockMenuSystem;

public class QuartermasterMain extends JavaPlugin {
	private ConnectionString connectionString;
	private MongoCredential credential;
	private MongoClientSettings settings;
	private MongoClient mongoClient;
	private MongoDatabase database;
	private static MongoCollection<Document> col;


	private static HashMap<Player, LockMenuSystem> lockMenuSystemHashMap = new HashMap<>();

	@Override
	public void onEnable() {
		connectionString = new ConnectionString("mongodb+srv://Luminous_Llama:llama@spigotcluster.fudi7.mongodb.net/Mongodb?retryWrites=true&w=majority");
		credential = MongoCredential.createCredential("Luminous_Llama", "admin", "llama".toCharArray());
		settings = MongoClientSettings.builder().applyConnectionString(connectionString).credential(credential).build();
		mongoClient = MongoClients.create(settings);

		database = mongoClient.getDatabase("quartermaster");
		col = database.getCollection("locks");

		getCommand("lock").setExecutor(new LockCommand());
		getCommand("list2").setExecutor(new ListCommand());
		Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
		Bukkit.getPluginManager().registerEvents(new ChestListeners(), this);
	}
	
	public static MongoCollection<Document> getDatabaseCollection(){ return col; }
	
	public static LockMenuSystem getPlayerMenuSystem(Player p) {
		if(lockMenuSystemHashMap.containsKey(p)) {
			return lockMenuSystemHashMap.get(p);
		}else {
			LockMenuSystem lockMenuSystem = new LockMenuSystem(p);
			lockMenuSystemHashMap.put(p, lockMenuSystem);
			
			return lockMenuSystem;
		}
	}
}
