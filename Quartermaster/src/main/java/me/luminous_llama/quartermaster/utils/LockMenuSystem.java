package me.luminous_llama.quartermaster.utils;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Consumer;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.luminous_llama.quartermaster.QuartermasterMain;
import net.md_5.bungee.api.ChatColor;

public class LockMenuSystem {

	Player p;

	private static Inventory menu;

	private Block lockBlock;
	private String lockID;
	private Player playerToGiveAccess;
	private Player playerToRemoveAccess;
	

	

	public LockMenuSystem(Player p) {
		this.p = p;
	}

	public void openAskGUI() {
		menu = Bukkit.createInventory(p, 9, ChatColor.DARK_AQUA + "Lock Detected : Lock Chest?");

		ItemStack yes = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta yes_meta = yes.getItemMeta();
		yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
		yes.setItemMeta(yes_meta);

		ItemStack no = new ItemStack(Material.BARRIER);
		ItemMeta no_meta = no.getItemMeta();
		no_meta.setDisplayName(ChatColor.RED + "No");
		no.setItemMeta(no_meta);

		menu.setItem(3, yes);
		menu.setItem(5, no);
		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}

	public void openLocksListGUI() {

		menu = Bukkit.createInventory(p, 54, ChatColor.DARK_AQUA + "Your Locks");

		String uuid = p.getUniqueId().toString();
		Document filter = new Document("uuid", uuid);
		QuartermasterMain.getDatabaseCollection().find(filter).forEach((Consumer<Document>) document -> {
			ItemStack lock = new ItemStack(Material.CHEST);
			ItemMeta lock_meta = lock.getItemMeta();
			lock_meta.setDisplayName(ChatColor.GREEN + "Chest Lock");
			ArrayList<String> lore = new ArrayList<>();
			lore.add(ChatColor.GOLD + "-------------------");
			lore.add(ChatColor.YELLOW + "Location:");

			Document location = (Document) document.get("location");

			lore.add(ChatColor.AQUA + "X:" + ChatColor.GREEN + location.getInteger("x"));
			lore.add(ChatColor.AQUA + "Y:" + ChatColor.GREEN + location.getInteger("y"));
			lore.add(ChatColor.AQUA + "Z:" + ChatColor.GREEN + location.getInteger("z"));
			lore.add(ChatColor.DARK_BLUE + "Date Created: " + document.getDate("creation-date").toString());
			lore.add(ChatColor.GOLD + "-------------------");
			lore.add(ChatColor.DARK_GRAY + document.getObjectId("_id").toString()); // CHAT COLOR MIGHT HAVE BROKE IT

			lock_meta.setLore(lore);
			lock.setItemMeta(lock_meta);
			menu.addItem(lock);
			System.out.println(StringUtils.replace((document.getObjectId("_id").toString()), "id: ", " ").trim());
		});
		menu = fillEmptySlots(menu);
		p.openInventory(menu);

	}

	public void openLockManagerGUI() {
		menu = Bukkit.createInventory(p, 9, ChatColor.GOLD + "Lock Manager");

		ItemStack manage_access = createMenuItemStack(Material.ARMOR_STAND, ChatColor.YELLOW + "Access Manager",
				ChatColor.GREEN + "Access who has access to this chest");
		ItemStack delete_lock = createMenuItemStack(Material.WITHER_ROSE, ChatColor.DARK_RED + "Delete Lock",
				ChatColor.GREEN + "Deleting the lock will", ChatColor.GREEN + "Leave your chest unprotected");
		ItemStack close_menu = createMenuItemStack(Material.BARRIER, ChatColor.RED + "Close");
		
		Document lock = LockUtils.getLock(this.lockID);
		Document location = (Document) lock.get("location");
		ItemStack lock_info = createMenuItemStack(Material.WRITABLE_BOOK, ChatColor.LIGHT_PURPLE + "Lock info",
				ChatColor.GOLD + "-------------------", ChatColor.YELLOW + "Location:",
				ChatColor.AQUA + "X:" + ChatColor.GREEN + location.getInteger("x"),
				ChatColor.AQUA + "Y:" + ChatColor.GREEN + location.getInteger("y"),
				ChatColor.AQUA + "Z:" + ChatColor.GREEN + location.getInteger("z"),
				ChatColor.DARK_BLUE + "Date Created: " + lock.getDate("creation-date").toString(),
				ChatColor.GOLD + "-------------------", ChatColor.DARK_GRAY + lock.getObjectId("_id").toString());

		menu.setItem(0, manage_access);
		menu.setItem(1, delete_lock);
		menu.setItem(7, lock_info);
		menu.setItem(8, close_menu);

		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}

	public void openConfirmDeleteMenu() {
		menu = Bukkit.createInventory(p, 9, ChatColor.RED + "Confirm: Delete Lock");

		ItemStack yes = createMenuItemStack(Material.EMERALD, ChatColor.GREEN + "Yes");
		ItemStack no = createMenuItemStack(Material.BARRIER, ChatColor.RED + "No");

		menu.setItem(3, yes);
		menu.setItem(5, no);

		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}

	public void openAccessManagerMenu() {
		menu = Bukkit.createInventory(p, 9, ChatColor.DARK_GREEN + "Access Manager");

		ItemStack addPlayer = createMenuItemStack(Material.ENDER_EYE, ChatColor.DARK_PURPLE + "Add Player",
				ChatColor.GREEN + "Add players who", ChatColor.GREEN + " have access");
		ItemStack removePlayer = createMenuItemStack(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "Remove Player",
				ChatColor.GREEN + "Remove players who", ChatColor.GREEN + "have access");
		ItemStack viewPlayers = createMenuItemStack(Material.PLAYER_HEAD, ChatColor.AQUA + "View Players",
				ChatColor.GREEN + "Show everyone who", ChatColor.GREEN + "has access");
		ItemStack close = createMenuItemStack(Material.BARRIER, ChatColor.RED + "Close");

		menu.setItem(0, addPlayer);
		menu.setItem(8, close);
		menu.setItem(2, removePlayer);
		menu.setItem(6, viewPlayers);

		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}

	public void openPlayerWithAccessMenu() {
		menu = Bukkit.createInventory(p, 45, ChatColor.YELLOW + "Players With Access to Lock");
		
		@SuppressWarnings("unchecked")
		ArrayList<String> accessList = (ArrayList<String>) LockUtils.getLock(lockID).get("access");
	
		for(int i=0;i<accessList.size();i++) {
			UUID uuid = UUID.fromString(accessList.get(i));
			Player playerWithAccess = Bukkit.getPlayer(uuid);
			
			ItemStack playerItemStack = createMenuItemStack(Material.PLAYER_HEAD, playerWithAccess.getDisplayName());
			menu.addItem(playerItemStack);
		}
		ItemStack close = createMenuItemStack(Material.BARRIER,ChatColor.RED + "Close");
		menu.setItem(44, close);
		
		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}
	
	public void openAddPlayersToAddMenu() {
		menu = Bukkit.createInventory(p, 54,ChatColor.GREEN + "Choose Player to add");
	
		for(Player player : Bukkit.getOnlinePlayers()) {
			ItemStack playerHead = createMenuItemStack(Material.PLAYER_HEAD, player.getDisplayName());
			if(playerHead.getItemMeta().getDisplayName().equals(p.getDisplayName())) { continue; }
			menu.addItem(playerHead);
		}
		ItemStack close = createMenuItemStack(Material.BARRIER,ChatColor.RED + "Close");
		menu.setItem(53, close);
		
		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}
	
	public void openRemovePlayerAccessToLockMenu() {
		menu = Bukkit.createInventory(p, 45, ChatColor.RED + "Remove Player Access to Lock");
		
		@SuppressWarnings("unchecked")
		ArrayList<String> accessList = (ArrayList<String>) LockUtils.getLock(lockID).get("access");
	
		for(int i=0;i<accessList.size();i++) {
			UUID uuid = UUID.fromString(accessList.get(i));
			Player playerWithAccess = Bukkit.getPlayer(uuid);
			
			ItemStack playerItemStack = createMenuItemStack(Material.PLAYER_HEAD, playerWithAccess.getDisplayName());
			menu.addItem(playerItemStack);
		}
		ItemStack close = createMenuItemStack(Material.BARRIER,ChatColor.RED + "Close");
		menu.setItem(44, close);
		
		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}
	
	public void openAddPlayerConfirmMenu() {
		menu = Bukkit.createInventory(p, 9,ChatColor.GREEN + "Confirm: Add Player");
		
		ItemStack yes = createMenuItemStack(Material.EMERALD, ChatColor.GREEN + "Yes", ChatColor.AQUA + "Add this player" + ChatColor.AQUA + "to this lock");
		ItemStack no = createMenuItemStack(Material.BARRIER, ChatColor.RED + "No");
		
		menu.setItem(3, yes);
		menu.setItem(5, no);
		
		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}
	
	public void openRemovePlayerConfirmMenu() {
		menu = Bukkit.createInventory(p, 9,ChatColor.RED + "Confirm: Remove Player");
		
		ItemStack yes = createMenuItemStack(Material.EMERALD, ChatColor.GREEN + "Yes",ChatColor.AQUA + "Remove this player", ChatColor.AQUA + "from this lock");
		ItemStack no = createMenuItemStack(Material.BARRIER, ChatColor.RED + "No");
		
		menu.setItem(3, yes);
		menu.setItem(5, no);
		
		menu = fillEmptySlots(menu);
		p.openInventory(menu);
	}
	// ---------------------------------------------------------------------------------------------------

	public ItemStack createMenuItemStack(Material material, String displayName, String... lore) {
		ItemStack stack = new ItemStack(material);
		ItemMeta stack_meta = stack.getItemMeta();
		stack_meta.setDisplayName(displayName);
		ArrayList<String> stack_lore = new ArrayList<>();
		for (int i = 0; i < lore.length; i++) {
			stack_lore.add(lore[i]);
		}
		stack_meta.setLore(stack_lore);
		stack.setItemMeta(stack_meta);

		return stack;

	}

	public Inventory fillEmptySlots(Inventory inv) {
		for (int i = 0; i < inv.getSize(); i++) {
			if (inv.getItem(i) == null) {
				inv.setItem(i, createMenuItemStack(Material.GRAY_STAINED_GLASS_PANE, " "));
			}
		}
		return inv;
	}

	public Inventory getLocksListGUI() {
		return menu;
	}

	public Block getLockBlock() {
		return lockBlock;
	}

	public void setLockBlock(Block lockBlock) {
		this.lockBlock = lockBlock;
	}
	
	public String getLockID() {
		return lockID;
	}

	public void setLockID(String lockID) {
		this.lockID = lockID;
	}
	
	public Player getPlayerToGiveAccess() {
		return playerToGiveAccess;
	}

	public void setPlayerToGiveAccess(Player playerToGiveAccess) {
		this.playerToGiveAccess = playerToGiveAccess;
	}
	
	public Player getPlayerToRemoveAccess() {
		return playerToRemoveAccess;
	}

	public void setPlayerToRemoveAccess(Player playerToRemoveAccess) {
		this.playerToRemoveAccess = playerToRemoveAccess;
	}
}