package me.ghaxz.bettersafety.commands;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Clears the chat (wouldn't have guess that, I know right)
public class ClearChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        // If the command sender has op permissions
        if(commandSender.isOp()) {
            if(args.length > 0) { // If an argument was given
                if(args[0].equalsIgnoreCase("global")) { // check if the "global" argument was given
                    for (int x = 0; x < 100; x++) { // Sends 100 empty messages to every player (clears chat globally)
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage("");
                        }
                    }
                    Bukkit.broadcastMessage(BetterSafetyMC.prefix + "The chat was cleared by an administrator.");
                    return true;
                }
            }
        }

        // If any of the conditions on top fail the chat gets cleared for the command sender only
        if(commandSender instanceof Player) { // Checks if the command was sent by an actual player
            for (int x = 0; x < 100; x++) {
                commandSender.sendMessage("");
            }

            commandSender.sendMessage(BetterSafetyMC.prefix + "Your chat was cleared.");
        }

        return true;
    }
}
