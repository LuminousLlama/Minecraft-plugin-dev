package me.luminous_llama.discordplugintest.commands;

import java.util.EnumSet;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.luminous_llama.discordplugintest.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;


public class VerifyCommand implements CommandExecutor{
	Main plugin;
	public VerifyCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		
		User user = plugin.getJDA().getUserByTag(args[0]);
		Guild guild = plugin.getJDA().getGuildById("817572492276662282");
		Member member = guild.getMember(user);
		
		System.out.println(member.getEffectiveName());
		member.modifyNickname(sender.getName()).queue();
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println(member.getEffectiveName());
				
			}
		};
		
		//timer.schedule(task, 1);
		
		guild.createVoiceChannel("TestVC")
			.addPermissionOverride(guild.getPublicRole(), EnumSet.of(Permission.VIEW_CHANNEL), EnumSet.of(Permission.VOICE_CONNECT)).queue();
		System.out.println("Created channel");
		
		return false;
	
	}
}
