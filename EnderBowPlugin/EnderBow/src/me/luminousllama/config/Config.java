package me.luminousllama.config;

import org.bukkit.configuration.file.FileConfiguration;

import me.luminousllama.EnderBowPlugin;

public class Config {
    public static final String PEARL_KEY = "enderbow.usepearl";
    public static final String NO_PERM_KEY = "messages.noperm";
    public static final String INVALID_PLAYER_KEY = "messages.invalidplayer";
	public static final String ONLY_PLAYERS_KEY = "messages.onlyplayers";
	public static final String GIVE_KEY = "messages.giveenderbow";
    public static final String RECEIVED_KEY = "messages.receivedenderbow";
	public static final String CONFIG_RELOAD_KEY = "messages.reload";

   
    public static final String DEFAULT_NO_PERM_VALUE = "&4You do not have permission for that!";
    public static final String DEFAULT_INVALID_PLAYER_VALUE = "&4That is not a valid player!";
    public static final String DEFAULT_ONLY_PLAYERS_VALUE = "&4YOnly players can enter that command!";
    public static final String DEFAULT_GIVE_VALUE = "&2Gave enderbow to %PLAYER%!";
    public static final String DEFAULT_RECEIVED_VALUE = "&2 Received enderbow from %PLAYER%";
    public static final String DEFAULT_CONFIG_RELOAD_VALUE = "&2[Enderbow Config Reloaded]"; 

    public static final boolean DEFAULT_PEARL_VALUE = true;


    private String noPermMessage, invalidPlayerMessage, onlyPlayersMessage, giveEnderbowMessage, receivedEnderBowMessage, configReloadedMessage;

   
    

    public void setReceivedEnderBowMessage(String receivedEnderBowMessage) {
        this.receivedEnderBowMessage = receivedEnderBowMessage;
    }

    private boolean usePearls;

   

    public Config(){
        saveDefaultValues();
        loadConfig();
    }

    public void saveDefaultValues(){
        FileConfiguration config = EnderBowPlugin.getInstance().getConfig();

        config.addDefault(PEARL_KEY, true);
		config.addDefault(NO_PERM_KEY, DEFAULT_NO_PERM_VALUE);
        config.addDefault(INVALID_PLAYER_KEY, DEFAULT_INVALID_PLAYER_VALUE);
        config.addDefault(ONLY_PLAYERS_KEY, DEFAULT_ONLY_PLAYERS_VALUE);
        config.addDefault(GIVE_KEY, DEFAULT_GIVE_VALUE);
        config.addDefault(CONFIG_RELOAD_KEY, DEFAULT_CONFIG_RELOAD_VALUE);
        config.addDefault(RECEIVED_KEY, DEFAULT_RECEIVED_VALUE);

        config.options().copyDefaults(true);

        EnderBowPlugin.getInstance().saveConfig();
    }

    public void loadConfig(){
        FileConfiguration config = EnderBowPlugin.getInstance().getConfig();

        usePearls = config.getBoolean(PEARL_KEY, DEFAULT_PEARL_VALUE);

        noPermMessage = config.getString(NO_PERM_KEY, DEFAULT_NO_PERM_VALUE);
        invalidPlayerMessage = config.getString(INVALID_PLAYER_KEY, DEFAULT_INVALID_PLAYER_VALUE);
        onlyPlayersMessage = config.getString(ONLY_PLAYERS_KEY, DEFAULT_ONLY_PLAYERS_VALUE);
        giveEnderbowMessage = config.getString(GIVE_KEY, DEFAULT_GIVE_VALUE);
        receivedEnderBowMessage = config.getString(RECEIVED_KEY, DEFAULT_RECEIVED_VALUE);
        configReloadedMessage = config.getString(CONFIG_RELOAD_KEY, DEFAULT_CONFIG_RELOAD_VALUE);
    }

    public String getConfigReloadedMessage() {
        return configReloadedMessage;
    }

    public String getGiveEnderbowMessage() {
        return giveEnderbowMessage;
    }

    public String getOnlyPlayersMessage() {
        return onlyPlayersMessage;
    }

    public String getInvalidPlayerMessage() {
        return invalidPlayerMessage;
    }
    public String getReceivedEnderBowMessage() {
        return receivedEnderBowMessage;
    }
    public String getNoPermMessage() {
        return noPermMessage;
    }

    public boolean isUsePearls() {
        return usePearls;
    }

}
