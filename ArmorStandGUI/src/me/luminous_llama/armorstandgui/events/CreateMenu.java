package me.luminous_llama.armorstandgui.events;

import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.luminous_llama.armorstandgui.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class CreateMenu implements Listener {

public CreateMenu() {
		
	}


	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		// menu list
		final String CREATE_MENU = ChatColor.GREEN + "Create a armorstand";

		if (e.getView().getTitle().equals(CREATE_MENU)) {

			switch (e.getCurrentItem().getType()) {
			case ARMOR_STAND:
				Utils.openConfirmMenu(p, Material.ARMOR_STAND);
				break;
			case BEACON:
				Utils.openConfirmMenu(p, Material.BEACON);
				break;
			case LEATHER_CHESTPLATE:
				Utils.openArmorMenu(p);
				break;
			case STONE_SLAB:
				Utils.openConfirmMenu(p, Material.STONE_SLAB);
				break;
			case DIAMOND_SWORD:
				Utils.openWeaponMenu(p);
				break;
			case GREEN_WOOL:
				Utils.createArmorStand(p);
				p.closeInventory();
				break;
			case RED_WOOL:
				//stand = plugin.armorstands.get(p);
				//stand.remove();
				//plugin.armorstands.remove(p);
				p.closeInventory();
				break;
			default: 
				break;
			}
			e.setCancelled(true);
		}
	}
}
