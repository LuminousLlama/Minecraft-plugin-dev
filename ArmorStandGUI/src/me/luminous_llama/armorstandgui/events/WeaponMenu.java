package me.luminous_llama.armorstandgui.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.md_5.bungee.api.ChatColor;

public class WeaponMenu implements Listener {

public WeaponMenu() {
		
	}


	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		// menu list
		final String MAIN_MENU = ChatColor.BLUE + "Armor Stand GUI";
		final String CREATE_MENU = ChatColor.GREEN + "Create a armorstand";
		final String CONFIRM_MENU = ChatColor.GREEN + "Confirm Action";

	}
}