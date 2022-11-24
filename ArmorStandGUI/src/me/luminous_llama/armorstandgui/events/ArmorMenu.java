package me.luminous_llama.armorstandgui.events;

import java.net.http.WebSocket.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.luminous_llama.armorstandgui.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class ArmorMenu implements Listener {

	public ArmorMenu() {

	}

	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		// menu list
		final String ARMOR_MENU = ChatColor.GREEN + "Add Armor";

		if (e.getView().getTitle().equals(ARMOR_MENU)) {
			if(Utils.checkRemoveArmorClick(e.getSlot())) { 
				Utils.openArmorMenu(p); 
			} else if (e.getCurrentItem().getType() == Material.GREEN_WOOL) {
				Utils.openCreateMenu(p);
			} else{
				Utils.setArmor(e.getCurrentItem().getType(), Utils.returnArmorType(e.getCurrentItem().getType()));
				Utils.openArmorMenu(p);
			}

			e.setCancelled(true);
		}
	}
}
