package me.luminous_llama.discordplugintest.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.luminous_llama.discordplugintest.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

public class HelloCommand implements CommandExecutor{

	Main plugin;
	public HelloCommand(Main plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		TextChannel textchannel = plugin.getJDA().getTextChannelById("819626890004267009");
		textchannel.sendMessage("hi").queue();
		
		Guild guild = plugin.getJDA().getGuildsByName("Llama's Diary", true).get(0);
		
		guild.getMembersByEffectiveName(sender.getName(), true);
		
		//Role role = guild.getRolesByName("DMparticipant", true);
		return false;
		
	}
}
