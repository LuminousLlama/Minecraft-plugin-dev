package me.luminous_llama.armorstandgui.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Utils {

	
	
	
	
	public static void openMainMenu(Player player) {
		
		Player p = (Player) player;
		Inventory main_menu = Bukkit.createInventory(p, 9,ChatColor.BLUE + "Armor Stand GUI");
		
		//Options for main menu
		
		//Create armorstand option
		ItemStack create = new ItemStack(Material.ARMOR_STAND);
		ItemMeta create_meta = create.getItemMeta();
		create_meta.setDisplayName(ChatColor.GREEN + "Create");
		ArrayList<String> create_lore = new ArrayList<>();
		create_lore.add(ChatColor.DARK_PURPLE + "Create new armor stand");
		create_meta.setLore(create_lore);
		create.setItemMeta(create_meta);
		
		//exit GUI option
		ItemStack close = new ItemStack(Material.BARRIER);
		ItemMeta close_meta = close.getItemMeta();
		close_meta.setDisplayName(ChatColor.RED + "Close");
		close.setItemMeta(close_meta);
		
		//Add options to GUI
		main_menu.setItem(0,create);
		main_menu.setItem(8, close);
		
		p.openInventory(main_menu);
	}
	
	public static void openCreateMenu(Player p) {
		Inventory create_menu = Bukkit.createInventory(p, 9,ChatColor.GREEN + "Create a armorstand");
	
		//create items for slots 
		ItemStack arms = new ItemStack(Material.ARMOR_STAND);
		ItemStack glow = new ItemStack(Material.BEACON);
		ItemStack armor = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack weapon = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack base = new ItemStack(Material.STONE_SLAB);
		ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
		ItemStack cancle = new ItemStack(Material.RED_WOOL);
		
		//give metadata to items 
		ItemMeta arms_meta = arms.getItemMeta();
		arms_meta.setDisplayName(ChatColor.YELLOW + "Arms");
		ItemMeta glow_meta = glow.getItemMeta();
		glow_meta.setDisplayName("Glow");
		ItemMeta armor_meta = armor.getItemMeta();
		armor_meta.setDisplayName(ChatColor.AQUA + "Armor");
		ItemMeta weapon_meta = weapon.getItemMeta();
		weapon_meta.setDisplayName(ChatColor.DARK_PURPLE + "Weapon");
		ItemMeta base_meta = base.getItemMeta();
		base_meta.setDisplayName(ChatColor.GRAY + "Base");
		ItemMeta confirm_meta = confirm.getItemMeta();
		confirm_meta.setDisplayName(ChatColor.GREEN + "Confirm");
		ItemMeta cancle_meta = cancle.getItemMeta();
		cancle_meta.setDisplayName(ChatColor.RED + "Cancle");
		
		arms.setItemMeta(arms_meta);
		glow.setItemMeta(glow_meta);
		armor.setItemMeta(armor_meta);
		base.setItemMeta(base_meta);
		confirm.setItemMeta(confirm_meta);
		cancle.setItemMeta(cancle_meta);
		
		//add items to slots 
		create_menu.setItem(0,arms);
		create_menu.setItem(1,glow);
		create_menu.setItem(2,base);
		create_menu.setItem(3,armor);
		create_menu.setItem(4,weapon);
		create_menu.setItem(7,confirm);
		create_menu.setItem(8,cancle);
	
		p.openInventory(create_menu);
		
	}
	
	public static void openConfirmMenu(Player p,Material option) {
		Inventory confirm_menu = Bukkit.createInventory(p, 36,ChatColor.GREEN + "Confirm Action");
		
		ItemStack option_item = new ItemStack(option);
		ItemMeta option_meta = option_item.getItemMeta();
		
		if(option == Material.ARMOR_STAND) {
			option_meta.setDisplayName(ChatColor.YELLOW + "Give Arms?");
			option_item.setItemMeta(option_meta);
		}else if(option == Material.BEACON) {
			option_meta.setDisplayName(ChatColor.YELLOW + "Add glow?");
			option_item.setItemMeta(option_meta);
		}else if(option == Material.STONE_SLAB) {
			option_meta.setDisplayName(ChatColor.YELLOW + "Add base?");
			option_item.setItemMeta(option_meta);
		}
		
		ItemStack yes = new ItemStack(Material.GREEN_WOOL);
		ItemMeta yes_meta = yes.getItemMeta();
		yes_meta.setDisplayName(ChatColor.GREEN + "YES");
		yes.setItemMeta(yes_meta);
		ItemStack no = new ItemStack(Material.RED_WOOL);
		ItemMeta no_meta = no.getItemMeta();
		no_meta.setDisplayName(ChatColor.RED + "NO");
		no.setItemMeta(no_meta);
		
		confirm_menu.setItem(13, option_item);
		confirm_menu.setItem(21, yes);
		confirm_menu.setItem(23, no);
		p.openInventory(confirm_menu);
		
	}
	
	public static void openWeaponMenu(Player p) {
		Inventory weaponMenu = Bukkit.createInventory(p, 54,ChatColor.GREEN + "Add Weapon");
		
		ItemStack netherite_sword = new ItemStack(Material.NETHERITE_SWORD);
		ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack golden_sword = new ItemStack(Material.GOLDEN_SWORD);
		ItemStack iron_sword = new ItemStack(Material.IRON_SWORD);
		ItemStack stone_sword = new ItemStack(Material.STONE_SWORD);
		ItemStack wooden_sword = new ItemStack(Material.WOODEN_SWORD);
		
		
		p.openInventory(weaponMenu);
		
	}
	
	public static void openArmorMenu(Player p) {
		
		
		Inventory armorMenu = Bukkit.createInventory(p, 54,ChatColor.GREEN + "Add Armor");
	
	
		ItemStack diamond_head = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack diamond_chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack diamond_legs = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack diamond_boots = new ItemStack(Material.DIAMOND_BOOTS);
	
		ItemStack netherite_head = new ItemStack(Material.NETHERITE_HELMET);
		ItemStack netherite_chest = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemStack netherite_legs = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemStack netherite_boots = new ItemStack(Material.NETHERITE_BOOTS);
		
		ItemStack golden_head = new ItemStack(Material.GOLDEN_HELMET);
		ItemStack golden_chest = new ItemStack(Material.GOLDEN_CHESTPLATE);
		ItemStack golden_legs = new ItemStack(Material.GOLDEN_LEGGINGS);
		ItemStack golden_boots = new ItemStack(Material.GOLDEN_BOOTS);
		
		ItemStack iron_head = new ItemStack(Material.IRON_HELMET);
		ItemStack iron_chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack iron_legs = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack iron_boots = new ItemStack(Material.IRON_BOOTS);
		
		ItemStack chainmail_head = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack chainmail_chest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack chainmail_legs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack chainmail_boots = new ItemStack(Material.CHAINMAIL_BOOTS);
		
		ItemStack leather_head = new ItemStack(Material.LEATHER_HELMET);
		ItemStack leather_chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leather_legs = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack leather_boots = new ItemStack(Material.LEATHER_BOOTS);
	
		ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
		ItemMeta confirm_meta = confirm.getItemMeta();
		confirm_meta.setDisplayName(ChatColor.GREEN + "Done");
		confirm.setItemMeta(confirm_meta);
		
		armorMenu.setItem(10, diamond_head);
		armorMenu.setItem(19, diamond_chest);
		armorMenu.setItem(28, diamond_legs);
		armorMenu.setItem(37, diamond_boots);
		
		armorMenu.setItem(9,  netherite_head);
		armorMenu.setItem(18, netherite_chest);
		armorMenu.setItem(27, netherite_legs);
		armorMenu.setItem(36, netherite_boots);

		armorMenu.setItem(11, golden_head);
		armorMenu.setItem(20, golden_chest);
		armorMenu.setItem(29, golden_legs);
		armorMenu.setItem(38, golden_boots);
		
		armorMenu.setItem(15, iron_head);
		armorMenu.setItem(24, iron_chest);
		armorMenu.setItem(33, iron_legs);
		armorMenu.setItem(42, iron_boots);
		
		armorMenu.setItem(16, chainmail_head);
		armorMenu.setItem(25, chainmail_chest);
		armorMenu.setItem(34, chainmail_legs);
		armorMenu.setItem(43, chainmail_boots);
		
		armorMenu.setItem(17, leather_head);
		armorMenu.setItem(26, leather_chest);
		armorMenu.setItem(35, leather_legs);
		armorMenu.setItem(44, leather_boots);
		
		armorMenu.setItem(13, ArmorstandParameters.helmet);
		armorMenu.setItem(22, ArmorstandParameters.chestplate);
		armorMenu.setItem(31, ArmorstandParameters.legs);
		armorMenu.setItem(40, ArmorstandParameters.boots);
		
		if(ArmorstandParameters.helmet == null || ArmorstandParameters.helmet == new ItemStack(Material.AIR)) {
			armorMenu.setItem(12, new ItemStack(Material.RED_STAINED_GLASS));
			armorMenu.setItem(14, new ItemStack(Material.RED_STAINED_GLASS));
		}else {
			p.sendMessage("helmet else green");
			armorMenu.setItem(12, new ItemStack(Material.GREEN_STAINED_GLASS));
			armorMenu.setItem(14, new ItemStack(Material.GREEN_STAINED_GLASS));
		}
		if(ArmorstandParameters.chestplate == null) {
			armorMenu.setItem(21, new ItemStack(Material.RED_STAINED_GLASS));
			armorMenu.setItem(23, new ItemStack(Material.RED_STAINED_GLASS));
		}else {
			armorMenu.setItem(21, new ItemStack(Material.GREEN_STAINED_GLASS));
			armorMenu.setItem(23, new ItemStack(Material.GREEN_STAINED_GLASS));
		}
		if(ArmorstandParameters.legs == null) {
			armorMenu.setItem(30, new ItemStack(Material.RED_STAINED_GLASS));
			armorMenu.setItem(32, new ItemStack(Material.RED_STAINED_GLASS));
		}else {
			armorMenu.setItem(30, new ItemStack(Material.GREEN_STAINED_GLASS));
			armorMenu.setItem(32, new ItemStack(Material.GREEN_STAINED_GLASS));
		}
		if(ArmorstandParameters.boots == null) {
			armorMenu.setItem(39, new ItemStack(Material.RED_STAINED_GLASS));
			armorMenu.setItem(41, new ItemStack(Material.RED_STAINED_GLASS));
		}else {
			armorMenu.setItem(39, new ItemStack(Material.GREEN_STAINED_GLASS));
			armorMenu.setItem(41, new ItemStack(Material.GREEN_STAINED_GLASS));
		}
		armorMenu.setItem(49, confirm);
		
		p.openInventory(armorMenu);
		
	}
	
	public static void setArmor (Material armor_piece, String armorType) {
		switch(armorType) {
		case "helmet":
			ItemStack helmet = new ItemStack(armor_piece);
			ArmorstandParameters.helmet = helmet;  
			break;
		case "chestplate":
			ItemStack chestplate = new ItemStack(armor_piece);
			ArmorstandParameters.chestplate = chestplate;
			break;
		case "legs":
			ItemStack legs = new ItemStack(armor_piece);
			ArmorstandParameters.legs = legs;
			break;
		case "boots":
			ItemStack boots = new ItemStack(armor_piece);
			ArmorstandParameters.boots = boots;
			break;
		}
	}
	
	public static String returnArmorType(Material armor_piece) {
		if(armor_piece == Material.NETHERITE_HELMET || armor_piece == Material.DIAMOND_HELMET || armor_piece == Material.GOLDEN_HELMET || armor_piece == Material.IRON_HELMET || armor_piece == Material.CHAINMAIL_HELMET || armor_piece == Material.LEATHER_HELMET) {
			return "helmet";
		}else if(armor_piece == Material.NETHERITE_CHESTPLATE || armor_piece == Material.DIAMOND_CHESTPLATE || armor_piece == Material.GOLDEN_CHESTPLATE || armor_piece == Material.IRON_CHESTPLATE || armor_piece == Material.CHAINMAIL_CHESTPLATE || armor_piece == Material.LEATHER_CHESTPLATE) {
			return "chestplate";
		}else if(armor_piece == Material.NETHERITE_LEGGINGS || armor_piece == Material.DIAMOND_LEGGINGS || armor_piece == Material.GOLDEN_LEGGINGS || armor_piece == Material.IRON_LEGGINGS || armor_piece == Material.CHAINMAIL_LEGGINGS || armor_piece == Material.LEATHER_LEGGINGS) {
			return "legs";
		}else if(armor_piece == Material.NETHERITE_BOOTS || armor_piece == Material.DIAMOND_BOOTS || armor_piece == Material.GOLDEN_BOOTS || armor_piece == Material.IRON_BOOTS || armor_piece == Material.CHAINMAIL_BOOTS || armor_piece == Material.LEATHER_BOOTS) {
			return "boots";
		}
		return null;
	
		
	}
	
	public static boolean checkRemoveArmorClick(int num) {
		if (num == 13) {
			//p.sendMessage("helmet clicked");
			ArmorstandParameters.helmet = null;
			return true;
		}
		if (num == 22) {
			ArmorstandParameters.chestplate = null;
			return true;
		}
		if (num == 31) {
			ArmorstandParameters.legs = null;
			return true;
		}
		if (num == 40) {
			ArmorstandParameters.boots = null;
			return true;
		}

		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static void createArmorStand(Player p) {
		ArmorStand stand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
		stand.setArms(ArmorstandParameters.arms);
		stand.setBasePlate(ArmorstandParameters.base);
		stand.setGlowing(ArmorstandParameters.glow);
		
		
		stand.setItemInHand(ArmorstandParameters.weapon);
		stand.setHelmet(ArmorstandParameters.helmet);
		stand.setChestplate(ArmorstandParameters.chestplate);
		stand.setLeggings(ArmorstandParameters.legs);
		stand.setBoots(ArmorstandParameters.boots);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
