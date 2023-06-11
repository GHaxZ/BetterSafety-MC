package me.ghaxz.bettersafety;

import me.ghaxz.bettersafety.commands.*;
import me.ghaxz.bettersafety.util.ConfigFile;
import me.ghaxz.bettersafety.util.VerificationProcess;
import me.ghaxz.bettersafety.util.VerifiedPlayersSave;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterSafetyMC extends JavaPlugin implements Listener {
    private static BetterSafetyMC instance; // Instance of the Main Plugin used by the other classes
    public static final String prefix = ChatColor.YELLOW + "[BetterSafety]" + ChatColor.WHITE + " "; // Chat prefix for the plugin
    private VerifiedPlayersSave verifiedPlayersSave; // verifiedPlayers.yml file manager
    private ConfigFile configFile; // config.yml file manager
    private boolean commandsEnabled = true; // Commands are enabled by default
    private boolean chatEnabled = true; // The chat is enabled by default
    private boolean safemodeEnabled = false; // The safe-mode is disabled by default
    private boolean verificationEnabled; // Enables or disables verification based on config.yml settings

    // Runs on Plugin load
    @Override
    public void onEnable() {

        instance = this;
        verifiedPlayersSave = new VerifiedPlayersSave();
        configFile = new ConfigFile();
        verificationEnabled = configFile.getVerificationEnabled();

        // Adds all the commands and event listeners
        this.getCommand("safemode").setExecutor(new SafemodeCommand());
        this.getCommand("togglecommands").setExecutor(new TogglecommandsCommand());
        this.getCommand("togglechat").setExecutor(new TogglechatCommand());
        this.getCommand("cc").setExecutor(new ClearChatCommand());
        this.getCommand("kickall").setExecutor(new KickAllCommand());
        this.getCommand("panic").setExecutor(new PanicCommand());
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new VerificationProcess(), this);
    }

    // Runs on plugin unload
    @Override
    public void onDisable() {

    }

    // Runs when a player tries to log into server
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        // If safe-mode is enabled all new logins get disallowed
        if(safemodeEnabled) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "You can't join this server, it's currently in Safe-mode.");
        }
    }

    // Runs whenever a command is executed by a player
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        // If commands are toggled off all new commands get cancelled
        if(!commandsEnabled) {
            if(!event.getMessage().equals("/togglecommands")) { // Allows the "/togglecommands" command to still be executed to toggle commands on again
                event.getPlayer().sendMessage(prefix + "Commands are currently disabled (/togglecommands).");
                event.setCancelled(true);
            }
        }
    }

    // Runs whenever a player chat message is sent
    @EventHandler
    public void onChat(PlayerChatEvent event) {
        // If the chat is toggled off all new chat messages get cancelled
        if(!chatEnabled) {
            event.getPlayer().sendMessage(prefix + "The chat is currently disabled (/togglechat).");
            event.setCancelled(true);
        }
    }

    // Kicks all unverified players on plugin reload, because a plugin reload can cause issues in the verification process


    public String getPrefix() {
        return prefix;
    }

    // Returns the current plugin instance so other classes can access it
    public static BetterSafetyMC getInstance() {
        return instance;
    }

    public void setCommandsEnabled(boolean commandsEnabled) {
        this.commandsEnabled = commandsEnabled;
    }

    public void setChatEnabled(boolean chatEnabled) {
        this.chatEnabled = chatEnabled;
    }

    public boolean isCommandsEnabled() {
        return commandsEnabled;
    }

    public boolean isChatEnabled() {
        return chatEnabled;
    }

    public boolean isSafemodeEnabled() {
        return safemodeEnabled;
    }

    public void setSafemodeEnabled(boolean safemodeEnabled) {
        this.safemodeEnabled = safemodeEnabled;
    }

    public boolean isVerificationEnabled() {
        return verificationEnabled;
    }

    public void setVerificationEnabled(boolean verificationEnabled) {
        this.verificationEnabled = verificationEnabled;
    }
}
