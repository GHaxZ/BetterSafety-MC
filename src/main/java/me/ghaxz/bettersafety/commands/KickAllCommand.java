package me.ghaxz.bettersafety.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import me.ghaxz.bettersafety.util.PlayerManager;

// Runs the kickAllPlayers() method in the PlayerManager class
public class KickAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender.isOp()) {
            PlayerManager.kickAllPlayers(commandSender);
        }

        return true;
    }
}
