package me.ghaxz.bettersafety.util;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerManager {

    // Toggles safe-mode on or off and informs entire server
    public static void toggleSafemode() {
        BetterSafetyMC.getInstance().setSafemodeEnabled(!BetterSafetyMC.getInstance().isSafemodeEnabled());
        if(BetterSafetyMC.getInstance().isSafemodeEnabled()) {
            Bukkit.broadcastMessage(BetterSafetyMC.prefix + "The Server is now in Safe-mode. No more players can connect.");
        } else {
            Bukkit.broadcastMessage(BetterSafetyMC.prefix + "The Server is no longer in Safe-mode. Players can connect again.");

        }
    }

    // Kicks all players except the command sender
    public static void kickAllPlayers(CommandSender commandSender) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!player.equals(commandSender)) {
                player.kickPlayer("All players were kicked from the server.");
            }
        }

        Bukkit.broadcastMessage(BetterSafetyMC.prefix + "All players were kicked from the server.");
    }
}
