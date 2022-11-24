package me.luminous_llama.quartermaster.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.luminous_llama.quartermaster.utils.LockUtils;
import net.md_5.bungee.api.ChatColor;

public class ChestListeners implements Listener {

	@EventHandler
	public void openChestListener(PlayerInteractEvent e) {
		e.getPlayer().sendMessage("In event");
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			e.getPlayer().sendMessage("checked if right click");
			Block b = e.getClickedBlock();
			if (b.getType().equals(Material.CHEST)) {
				e.getPlayer().sendMessage("checked if chest");
				if (LockUtils.isCurrentlyLocked(b)) {
					e.getPlayer().sendMessage("checked if locked");
					if (LockUtils.getWhoLocked(b).getUniqueId().equals(e.getPlayer().getUniqueId())) {
						e.getPlayer().sendMessage("U own this chest");
					} else {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.DARK_RED + "This chest is locked by " + ChatColor.YELLOW
								+ LockUtils.getWhoLocked(b).getName());
					}
				}
			}
		}
	}

	@EventHandler
	public void breakChestListener(BlockBreakEvent e) {
		if (e.getBlock().getType().equals(Material.CHEST)) {
			if (LockUtils.isCurrentlyLocked(e.getBlock())) {
				if (e.getPlayer().getUniqueId().equals(LockUtils.getWhoLocked(e.getBlock()).getUniqueId())) {
					LockUtils.deleteLock(e.getBlock());
				} else {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Cannot break chests locked by other players");
				}
			}
		}
	}
}
