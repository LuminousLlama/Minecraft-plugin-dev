package me.luminousllama.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import me.luminousllama.EnderBowPlugin;
import me.luminousllama.utils.EnderBowUtils;

public class EnderBowShootEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.isCancelled())
            return;

        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player) event.getEntity();

        if (!(EnderBowUtils.isEnderBow(event.getBow()))) {
            return;
        }

        // is ender bow without perm so cancel arrow shot
        if (!(p.hasPermission(EnderBowUtils.ENDERBOW_USE_PERM))) {
            p.sendMessage(EnderBowPlugin.getInternalConfig().getNoPermMessage());
            event.setCancelled(true);
            return;
        }

        if (EnderBowPlugin.getInternalConfig().isUsePearls()) {

            if (p.getGameMode() != GameMode.CREATIVE) {
                if (!(p.getInventory().contains(Material.ENDER_PEARL))) {
                    event.setCancelled(true);
                    return;
                }

                // if in survival remove ender pearl
                for (ItemStack item : p.getInventory().getContents()) {
                    if (item != null && item.getType().equals(Material.ENDER_PEARL)) {
                        item.setAmount(item.getAmount() - 1);
                        break;
                    }
                }
            }
        }

       
        Entity arrow = event.getProjectile();

        EnderPearl pearl = p.launchProjectile(EnderPearl.class, arrow.getVelocity());

        pearl.setShooter(p);

        arrow.remove();
    }
}
