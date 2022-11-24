package me.luminous_llama.armorstandgui.events;

import org.bukkit.event.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.inventory.InventoryClickEvent;

import me.luminous_llama.armorstandgui.utils.ArmorstandParameters;
import me.luminous_llama.armorstandgui.utils.Utils;
import net.md_5.bungee.api.ChatColor;

public class ConfirmMenu implements Listener {

public ConfirmMenu() {
		
	}

	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		// menu list

		final String CONFIRM_MENU = ChatColor.GREEN + "Confirm Action";

		if (e.getView().getTitle().equals(CONFIRM_MENU)) {
			if (e.getClickedInventory().contains(Material.ARMOR_STAND)) {
				switch (e.getCurrentItem().getType()) {
				case GREEN_WOOL:
					ArmorstandParameters.arms = true;
					Utils.openCreateMenu(p);
					break;
				case RED_WOOL:
					ArmorstandParameters.arms = false;
					Utils.openCreateMenu(p);
					break;
				default:
					break;
				}
			} else if (e.getClickedInventory().contains(Material.BEACON)) {
				switch (e.getCurrentItem().getType()) {
				case GREEN_WOOL:
					ArmorstandParameters.glow = true;
					Utils.openCreateMenu(p);
					break;
				case RED_WOOL:
					ArmorstandParameters.glow = false;
					Utils.openCreateMenu(p);
					break;
				default:
					break;
				}
			} else if (e.getClickedInventory().contains(Material.STONE_SLAB)) {
				switch (e.getCurrentItem().getType()) {
				case GREEN_WOOL:
					ArmorstandParameters.base = true;
					Utils.openCreateMenu(p);
					break;
				case RED_WOOL:
					ArmorstandParameters.base = false;
					Utils.openCreateMenu(p);
					break;
				default:
					break;
				}
			}
			e.setCancelled(true);
		}
	}
}
