package me.luminous_llama.armorstandgui.events;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.luminous_llama.armorstandgui.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class MainMenu implements Listener {

	public MainMenu() {
		
	}





	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		final String MAIN_MENU = ChatColor.BLUE + "Armor Stand GUI";

		if (e.getView().getTitle().equals(MAIN_MENU)) {
			switch (e.getCurrentItem().getType()) {
			case ARMOR_STAND:
				p.closeInventory();
				Utils.openCreateMenu(p);
				break;
			case BARRIER:
				p.closeInventory();
				break;
			default:
				break;
			}
			e.setCancelled(true);

		}
	}
}