package me.luminous_llama.quartermaster.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.luminous_llama.quartermaster.QuartermasterMain;

public class LockUtils {

	public static void createNewLock(Player p, Block block) {
		Document lock = new Document("uuid", p.getUniqueId().toString())
				.append("type", "chest")
				.append("location", new Document("x", block.getX()).append("y", block.getY()).append("z", block.getZ()))
				.append("creation-date", new Date())
				.append("access", new ArrayList<String>());

		QuartermasterMain.getDatabaseCollection().insertOne(lock);
		System.out.println("created new lock");
		p.closeInventory();
	}

	public static Boolean isCurrentlyLocked(Block b) {
		int x = b.getX();
		int y = b.getY();
		int z = b.getZ();
		Document filter = new Document("location", new Document("x", x).append("y", y).append("z", z));

		if (QuartermasterMain.getDatabaseCollection().countDocuments(filter) == 1) {
			return true;
		}
		return false;
	}

	public static OfflinePlayer getWhoLocked(Block b) {
		
		Document filter = createBlockFilter(b.getX(), b.getY(), b.getZ());

		String uuidString = QuartermasterMain.getDatabaseCollection().find(filter).first().getString("uuid");
		UUID uuid = UUID.fromString(uuidString);

		// OfflinePlayer of = Bukkit.getOfflinePlayer(uuid);

		return Bukkit.getOfflinePlayer(uuid);
	}


	public static void deleteLock(Block b) {
		Document filter = createBlockFilter(b.getX(), b.getY(), b.getZ());
		QuartermasterMain.getDatabaseCollection().deleteOne(filter);
	
	}
	
	public static void deleteLock(String lockID) {
		Document filter = getLock(lockID);
		QuartermasterMain.getDatabaseCollection().deleteOne(filter);
	}

	
	public static Document createBlockFilter(int x,int y,int z) {
		return new Document("location", new Document("x", x).append("y", y).append("z", z));
	}
	
	public static Document getLock(String id) {
		Document filter = new Document(new Document("_id", new ObjectId(id)));
		return QuartermasterMain.getDatabaseCollection().find(filter).first();
	}
	
	public static void addPlayerToLock(String lockID,Player playerToAdd) {
		Document lock = LockUtils.getLock(lockID);
		
		@SuppressWarnings("unchecked")
		ArrayList<String> accessList = (ArrayList<String>) lock.get("access");
		accessList.add(playerToAdd.getUniqueId().toString());
		
		Document newDoc = new Document("access",accessList);
		Document newDoc2 = new Document("$set",newDoc);
		
		Document filter = new Document(new Document("_id",new ObjectId(lockID)));
		
		QuartermasterMain.getDatabaseCollection().updateOne(filter, newDoc2);
		
		
	}
	
	public static void removePlayerFromLock(String lockID,Player playerToRemove) {
		Document lock = LockUtils.getLock(lockID);
		
		@SuppressWarnings("unchecked")
		ArrayList<String> accessList = (ArrayList<String>) lock.get("access");
		accessList.remove(playerToRemove.getUniqueId().toString());
		
		Document newDoc = new Document("access",accessList);
		Document newDoc2 = new Document("$set",newDoc);
		
		Document filter = new Document(new Document("_id",new ObjectId(lockID)));
		
		QuartermasterMain.getDatabaseCollection().updateOne(filter, newDoc2);
			
	}
	
	
	
	
	
	
	
}
