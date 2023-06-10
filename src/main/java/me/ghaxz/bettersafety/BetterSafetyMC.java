package me.ghaxz.bettersafety;

import me.ghaxz.bettersafety.commands.*;
import me.ghaxz.bettersafety.util.VerificationProcess;
import me.ghaxz.bettersafety.util.VerifiedPlayersSave;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterSafetyMC extends JavaPlugin implements Listener {
    private static BetterSafetyMC instance;
    public static final String prefix = ChatColor.YELLOW + "[BetterSafety]" + ChatColor.WHITE + " ";
    private VerifiedPlayersSave verifiedPlayersSave;
    private boolean commandsEnabled = true; // Standardmäßig sind Command an
    private boolean chatEnabled = true; // Standardmäßig ist der Chat an
    private boolean safemodeEnabled = false; // Standardmäßig ist der Safemode aus
    private boolean verificationEnabled = true; // Verification is enabled by default

    @Override
    public void onEnable() {
        instance = this;
        verifiedPlayersSave = new VerifiedPlayersSave();

        this.getCommand("safemode").setExecutor(new SafemodeCommand());
        this.getCommand("togglecommands").setExecutor(new TogglecommandsCommand());
        this.getCommand("togglechat").setExecutor(new TogglechatCommand());
        this.getCommand("cc").setExecutor(new ClearChatCommand());
        this.getCommand("kickall").setExecutor(new KickAllCommand());
        this.getCommand("panic").setExecutor(new PanicCommand());
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new VerificationProcess(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if(safemodeEnabled) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "You can't join this server, it's currently in Safe-mode.");
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(!commandsEnabled) {
            if(!event.getMessage().equals("/togglecommands")) {
                event.getPlayer().sendMessage(prefix + "Commands are currently disabled (/togglecommands).");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        if(!chatEnabled) {
            event.getPlayer().sendMessage(prefix + "The chat is currently disabled (/togglechat).");
            event.setCancelled(true);
        }
    }

    public String getPrefix() {
        return prefix;
    }

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
