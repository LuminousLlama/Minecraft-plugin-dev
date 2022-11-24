//package me.luminous_llama.armorstandgui.commands;
//
//import org.bukkit.Material;
//import org.bukkit.entity.ArmorStand;
//import org.bukkit.entity.EntityType;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.inventory.ItemStack;
//
//import me.luminous_llama.armorstandgui.Main;
//import me.luminous_llama.armorstandgui.utils.Utils;
//import net.md_5.bungee.api.ChatColor;
//
//public class MenuHandler implements Listener {
//
//	Main plugin;
//	
//	public MenuHandler(Main plugin){
//		this.plugin = plugin;
//	}
//	
//	@EventHandler
//	public void onMenuClick(InventoryClickEvent e) {
//		Player p = (Player) e.getWhoClicked();
//
//		// menu list
//		final String MAIN_MENU = ChatColor.BLUE + "Armor Stand GUI";
//		final String CREATE_MENU = ChatColor.GREEN + "Create a armorstand";
//		final String CONFIRM_MENU = ChatColor.GREEN + "Confirm Action";
//		
//		if (e.getView().getTitle().equals(MAIN_MENU)) {
//			switch (e.getCurrentItem().getType()) {
//			case ARMOR_STAND:
//				p.closeInventory();
//				Utils.openCreateMenu(p);
//				break;
//			case BARRIER:
//				p.closeInventory();
//				break;
//			}
//			e.setCancelled(true);
//		}else if(e.getView().getTitle().equals(CREATE_MENU)){
//			if(!plugin.armorstands.containsKey(p)) {
//				ArmorStand stand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
//				stand.setVisible(false);
//				plugin.armorstands.put(p, stand);
//			}
//			
//			switch (e.getCurrentItem().getType()) {
//			case ARMOR_STAND:
//				Utils.openConfirmMenu(p, Material.ARMOR_STAND);
//				break;
//			case BEACON:
//				Utils.openConfirmMenu(p, Material.BEACON);
//				break;
//			case LEATHER_CHESTPLATE:
//				Utils.openArmorMenu(p);
//				break;
//			case STONE_SLAB:
//				Utils.openConfirmMenu(p, Material.STONE_SLAB);
//				break;
//			case GREEN_WOOL:
//				ArmorStand stand = plugin.armorstands.get(p);
//				stand.setVisible(true);
//				plugin.armorstands.remove(p);
//				p.closeInventory();
//				break;
//			case RED_WOOL:
//				stand = plugin.armorstands.get(p);
//				stand.remove();
//				plugin.armorstands.remove(p);
//				p.closeInventory();
//				break;
//			}
//			e.setCancelled(true);
//		}else if(e.getView().getTitle().equals(CONFIRM_MENU)) {
//			if(e.getClickedInventory().contains(Material.ARMOR_STAND)) {
//				switch(e.getCurrentItem().getType()) {
//				case GREEN_WOOL:
//					ArmorStand stand = plugin.armorstands.get(p);
//					stand.setArms(true);
//					Utils.openCreateMenu(p);
//					break;
//				case RED_WOOL:
//					stand = plugin.armorstands.get(p);
//					stand.setArms(false);
//					Utils.openCreateMenu(p);
//					break;
//				}
//			}else if(e.getClickedInventory().contains(Material.BEACON)) {
//				switch(e.getCurrentItem().getType()) {
//				case GREEN_WOOL:
//					ArmorStand stand = plugin.armorstands.get(p);
//					stand.setGlowing(true);
//					Utils.openCreateMenu(p);
//					break;
//				case RED_WOOL:
//					stand = plugin.armorstands.get(p);
//					stand.setGlowing(false);
//					Utils.openCreateMenu(p);
//					break;
//				}
//			}else if(e.getClickedInventory().contains(Material.STONE_SLAB)) {
//				switch(e.getCurrentItem().getType()) {
//				case GREEN_WOOL:
//					ArmorStand stand = plugin.armorstands.get(p);
//					stand.setBasePlate(true);
//					Utils.openCreateMenu(p);
//					break;
//				case RED_WOOL:
//					stand = plugin.armorstands.get(p);
//					stand.setBasePlate(false);
//					Utils.openCreateMenu(p);
//					break;
//				}
//			}
//			e.setCancelled(true);
//			
//		}else if(e.getView().getTitle().equals(ChatColor.GREEN + "Add Armor")) {
//			ArmorStand stand = plugin.armorstands.get(p);
//			switch(e.getCurrentItem().getType()) {
//			case DIAMOND_HELMET:
//				if(stand.getHelmet().getType() == Material.DIAMOND_HELMET) {
//					stand.setHelmet(null);
//				}else {
//					stand.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
//				}
//				break;
//			case DIAMOND_CHESTPLATE:
//				if(stand.getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {
//					stand.setChestplate(null);
//				}else {
//					stand.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
//				}
//				break;
//			case DIAMOND_LEGGINGS:
//				if(stand.getLeggings().getType() == Material.DIAMOND_LEGGINGS) {
//					stand.setLeggings(null);
//				}else {
//					stand.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
//				}
//				break;
//			case DIAMOND_BOOTS:
//				if(stand.getBoots().getType() == Material.DIAMOND_BOOTS) {
//					stand.setBoots(null);
//				}else {
//					stand.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
//				}
//				break;
//			case GREEN_WOOL:
//				Utils.openCreateMenu(p);
//				break;
//					
//			}
//			e.setCancelled(true);
//		}
//				
//		
//	}
//}
package me.luminous_llama.armorstandgui.commands;

public class Temp {

}
