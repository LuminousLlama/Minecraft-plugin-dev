package me.luminousllama.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.checkerframework.checker.signature.qual.BinaryNameWithoutPackage;

import me.luminousllama.EnderBowPlugin;
import net.md_5.bungee.api.ChatColor;

public final class EnderBowUtils {
    public static NamespacedKey ENDERBOW_KEY = new NamespacedKey(EnderBowPlugin.getInstance(), "enderbow");

    public static final String ENDERBOW_USE_PERM = "enderbow.use";
    public static final String ENDERBOW_GIVE_SELF = "enderbow.give.self";
    public static final String ENDERBOW_GIVE_OTHERS = "enderbow.give.others";
    public static final String ENDERBOW_RELOAD = "enderbow.reload";

    private static final ArrayList<Permission> perms = new ArrayList<>();

    private EnderBowUtils() {
    }

    public static ItemStack createEnderBow() {
        ItemStack bow = new ItemStack(Material.BOW);

        ItemMeta meta = bow.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_PURPLE + "ENDER BOW");
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        bow.setItemMeta(meta);

        return bow;
    }

    public static boolean isEnderBow(ItemStack itemStack) {
        //is a valid itemstack 
        if (itemStack == null ||
                itemStack.getType() != Material.BOW ||
                !itemStack.hasItemMeta() ||
                !itemStack.getItemMeta().hasDisplayName())
            return false;
        //has name of "ENDER BOW"
        else if (itemStack.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "ENDER BOW"))
            return true;

        //does not match ender bow display name
        else return false;
    }

    public static boolean registerEnderBowRecipe(){
        ItemStack bow = createEnderBow();

        ShapedRecipe recipe = new ShapedRecipe(ENDERBOW_KEY, bow);

        recipe.shape("EEE",
                     "EBE",
                     "EEE");

        recipe.setIngredient('E', Material.ENDER_PEARL);
        recipe.setIngredient('B', Material.BOW);

        boolean success = Bukkit.addRecipe(recipe);

        if(success) EnderBowPlugin.getInstance().getLogger().fine("Registered recipe: " + ENDERBOW_KEY.getNamespace() + ":" + ENDERBOW_KEY.getKey());

        else EnderBowPlugin.getInstance().getLogger().fine("Failed to register recipe: " + ENDERBOW_KEY.getNamespace() + ":" + ENDERBOW_KEY.getKey());

        return success;
    }

    public static boolean unregisterEnderBowRecipe(){
		//Remove the recipe from the server and store whether it was successful
		boolean success = Bukkit.removeRecipe(ENDERBOW_KEY);
		
		//If success, log a message saying so
		if(success) EnderBowPlugin.getInstance().getLogger().fine("Unregistered recipe: " + ENDERBOW_KEY.getNamespace()+":"+ENDERBOW_KEY.getKey());
		
		//If failure, log a message saying so
		else EnderBowPlugin.getInstance().getLogger().fine("Failed to unregister recipe: " + ENDERBOW_KEY.getNamespace()+":"+ENDERBOW_KEY.getKey());
		
		//Return the result
		return success;
	}

    public static void registerPermissions(){
		//Create the permissions and store them in a list
		//The list is mainly used internally but a getter could be used to grant other developers access to the list
		//For a permission we only need to have a string representing the key, however it's best to include the description and who has the permission by default
		perms.add(new Permission(ENDERBOW_USE_PERM, "Allows player to use enderbow", PermissionDefault.TRUE));
		perms.add(new Permission(ENDERBOW_GIVE_SELF, "Allows player give themselves an enderbow", PermissionDefault.OP));
		perms.add(new Permission(ENDERBOW_GIVE_OTHERS, "Allows player give others an enderbow", PermissionDefault.OP));
		perms.add(new Permission(ENDERBOW_RELOAD, "Allows players to reload the config", PermissionDefault.OP));
		
		//Loop through the list and add all the permissions we created
		for(Permission perm : perms){
			Bukkit.getPluginManager().addPermission(perm);
			
			//Log a message that we added the permission
			EnderBowPlugin.getInstance().getLogger().fine("Registered Permission: " + perm.getName());
		}
	}

	/**
	 * Unregister all Enderbow permissions
	 */
	public static void unregisterPermissions(){
		//Remove all permissions that we created
		//Mainly used when disabling the plugin to prevent issues if the permissions are changed and the plugin is enabled again (possibly an update?)
		//While using the /reload command is bad practice, many server owners will do so anyway and that can cause issues if we don't clean up properly
		for(Permission perm : perms){
			Bukkit.getPluginManager().removePermission(perm);
			
			//Log a message that we removed the permission
			EnderBowPlugin.getInstance().getLogger().fine("Unregistered Permission: " + perm.getName());
		}
		
		//Clear the list of permissions incase this method was called but the plugin wasn't disabled
		//If we don't do this then calling registerPermissions() would result in trying to register each permission twice
		perms.clear();
	}
}
