package me.ghaxz.bettersafety.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import me.ghaxz.bettersafety.util.PlayerManager;

// Runs the toggleSafemode() method in the PlayerManager class
public class SafemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender.isOp()) {
            PlayerManager.toggleSafemode();
        }

        return true;
    }
}
