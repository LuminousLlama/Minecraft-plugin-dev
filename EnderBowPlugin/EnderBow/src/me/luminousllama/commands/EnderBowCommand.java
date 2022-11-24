package me.luminousllama.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.luminousllama.EnderBowPlugin;
import me.luminousllama.config.Config;
import me.luminousllama.utils.EnderBowUtils;

public class EnderBowCommand implements CommandExecutor {
   

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Config internalConfig = EnderBowPlugin.getInternalConfig();

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.translateAlternateColorCodes( '&', internalConfig.getOnlyPlayersMessage()));
            return true;
        }
        if(args.length > 1){
            sender.sendMessage(ChatColor.AQUA + "/enderbow <player>");
            return true;
        }

        //already checked to make sure this is a player in the first if 
        Player p = (Player) sender;
        

        if(args.length == 0){
            if( !(sender.hasPermission(EnderBowUtils.ENDERBOW_GIVE_SELF)) ){
                sender.sendMessage(ChatColor.RED + "YOU DO NOT HAVE PERMISSION TO RUN THIS COMMAND");
                return true;
            }
            ItemStack bow = EnderBowUtils.createEnderBow();

            p.getInventory().addItem(bow);

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', internalConfig.getGiveEnderbowMessage().replaceFirst( "%PLAYER%", p.getDisplayName())));

            return true;
        }
        if(args.length == 1){
            //handles reload 
            if(args[0] == "reload"){
                if( !(sender.hasPermission(EnderBowUtils.ENDERBOW_RELOAD)) ){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', internalConfig.getNoPermMessage()));
                    return true;
                }

                EnderBowPlugin.getInstance().reloadConfig();

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', internalConfig.getConfigReloadedMessage()));
                return true;
            }

            //handles if sender added player to give bow to 
            if( !(sender.hasPermission(EnderBowUtils.ENDERBOW_GIVE_OTHERS)) ) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', internalConfig.getNoPermMessage()));
                return true;
            }
            if( Bukkit.getPlayer(args[0]) == null ){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', internalConfig.getInvalidPlayerMessage()));
                return true;
            }
            
            Player receivingPlayer = Bukkit.getPlayer(args[0]);

            ItemStack bow = EnderBowUtils.createEnderBow();

            receivingPlayer.getInventory().addItem(bow);

            receivingPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', internalConfig.getReceivedEnderBowMessage().replaceFirst("%PLAYER%", p.getDisplayName())));

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', internalConfig.getGiveEnderbowMessage().replaceFirst("%PLAYER%", receivingPlayer.getDisplayName())));

            return true;
        }

        return false;
    }
    
}
