package me.luminous_llama.mongoplugin;

import java.util.function.Consumer;

import org.bson.Document;
import org.bukkit.plugin.java.JavaPlugin;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import java.lang.*;



public class Main extends JavaPlugin{

	@Override
	public void onEnable() {
		
		ConnectionString connectionString = new ConnectionString("mongodb+srv://Luminous_Llama:llama@spigotcluster.fudi7.mongodb.net/test?retryWrites=true&w=majority");
		MongoCredential credential = MongoCredential.createCredential("Luminous_Llama", "admin", "llama".toCharArray());
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).credential(credential).build();
		
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase("sample_training");
		MongoCollection<Document> col = database.getCollection("grades");
		
		Document filter = new Document("class_id",325);
		col.find(filter).forEach((Consumer<Document>) document -> {
			System.out.println(document.toJson());
		});
		System.out.println("Plugin has worked");
	}
	
}
