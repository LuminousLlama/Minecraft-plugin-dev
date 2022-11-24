package me.luminous_llama.quartermaster.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.luminous_llama.quartermaster.QuartermasterMain;
import me.luminous_llama.quartermaster.utils.LockMenuSystem;
import me.luminous_llama.quartermaster.utils.LockUtils;
import net.md_5.bungee.api.ChatColor;

public class MenuListener implements Listener {

	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();
		LockMenuSystem lockMenuSystem = QuartermasterMain.getPlayerMenuSystem(p);

		if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Lock Detected : Lock Chest?")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(null)) {
				return;
			}
			if (e.getCurrentItem().getType().equals(Material.TRIPWIRE_HOOK)) {
				p.sendMessage("Creating new lock");
				LockUtils.createNewLock(p, lockMenuSystem.getLockBlock());

			} else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				p.closeInventory();
			}
		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "Your Locks")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.CHEST)) {
				lockMenuSystem.setLockID(ChatColor.stripColor(
						lockMenuSystem.getLocksListGUI().getItem(e.getSlot()).getItemMeta().getLore().get(7)));

				lockMenuSystem.openLockManagerGUI();
			}
		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Lock Manager")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				lockMenuSystem.openLocksListGUI();
			} else if (e.getCurrentItem().getType().equals(Material.WITHER_ROSE)) {
				// open confirm menu to delete lock
				lockMenuSystem.openConfirmDeleteMenu();
			} else if (e.getCurrentItem().getType().equals(Material.ARMOR_STAND)) {
				// open access menu
				lockMenuSystem.openAccessManagerMenu();
			}
		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Confirm: Delete Lock")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.EMERALD)) {
				// delete lock
				LockUtils.deleteLock(lockMenuSystem.getLockID());
				lockMenuSystem.openLocksListGUI();
			} else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				lockMenuSystem.openLockManagerGUI();
			}
		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN + "Access Manager")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				lockMenuSystem.openLockManagerGUI();
			} else if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
				lockMenuSystem.openPlayerWithAccessMenu();
			} else if (e.getCurrentItem().getType().equals(Material.ENDER_EYE)) {
				// open player add menu
				lockMenuSystem.openAddPlayersToAddMenu();
			} else if (e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
				lockMenuSystem.openRemovePlayerAccessToLockMenu();
			}
		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Choose Player to add")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
				lockMenuSystem
						.setPlayerToGiveAccess(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()));
				lockMenuSystem.openAddPlayerConfirmMenu();
			} else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				lockMenuSystem.openAccessManagerMenu();
			}
		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.YELLOW + "Players With Access to Lock")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				lockMenuSystem.openAccessManagerMenu();
			}

		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Remove Player With Access to Lock")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				lockMenuSystem.openAccessManagerMenu();
			} else if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
				lockMenuSystem
						.setPlayerToRemoveAccess(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()));
				lockMenuSystem.openRemovePlayerConfirmMenu();
			}
		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Confirm: Add Player")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				lockMenuSystem.openAccessManagerMenu();
			} else if (e.getCurrentItem().getType().equals(Material.EMERALD)) {
				// add player
				LockUtils.addPlayerToLock(lockMenuSystem.getLockID(), lockMenuSystem.getPlayerToGiveAccess());
				p.sendMessage(ChatColor.YELLOW + lockMenuSystem.getPlayerToGiveAccess().getDisplayName() + ChatColor.GREEN + "has been granted access");
				lockMenuSystem.getPlayerToGiveAccess().sendMessage(ChatColor.YELLOW + p.getDisplayName()
						+ ChatColor.GREEN + "Has granted you to one of their locks");
				lockMenuSystem.openAccessManagerMenu();
			}
		} else if (e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Confirm: Remove Player")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
				lockMenuSystem.openAccessManagerMenu();
			} else if (e.getCurrentItem().getType().equals(Material.EMERALD)) {
				LockUtils.removePlayerFromLock(lockMenuSystem.getLockID(), lockMenuSystem.getPlayerToRemoveAccess());
				p.sendMessage(ChatColor.YELLOW + lockMenuSystem.getPlayerToRemoveAccess().getDisplayName() + ChatColor.RED+ "has been removed from this lock");
				lockMenuSystem.getPlayerToRemoveAccess().sendMessage(ChatColor.YELLOW + p.getDisplayName()
						+ ChatColor.RED + "Has removed your access to one of their locks");
				lockMenuSystem.openAccessManagerMenu();
			}
		}
	}
}
