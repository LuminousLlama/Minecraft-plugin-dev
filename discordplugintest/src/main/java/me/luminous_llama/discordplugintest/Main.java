package me.luminous_llama.discordplugintest;

import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;

import me.luminous_llama.discordplugintest.commands.HelloCommand;
import me.luminous_llama.discordplugintest.commands.VerifyCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Main extends JavaPlugin {

	JDA jda;

	@Override
	public void onEnable() {


		try {
			jda = JDABuilder.createDefault("ODgyNTI1NDc0OTg1NzQyMzY5.YS8p5Q.y82uiGb74qLaRYXFy7e3uNfVlTs")
					.setChunkingFilter(ChunkingFilter.ALL).
					setMemberCachePolicy(MemberCachePolicy.ALL)
					.enableIntents(GatewayIntent.GUILD_MEMBERS).
					build();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		getCommand("hello").setExecutor(new HelloCommand(this));
		getCommand("verify").setExecutor(new VerifyCommand(this));
	}
	
	@Override
	public void onDisable() {
		jda.shutdownNow();
	}
	
	public JDA getJDA() {
		return jda;
	}
	
	
}
