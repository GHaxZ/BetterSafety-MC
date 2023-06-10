package me.ghaxz.bettersafety.commands;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// Toggle commands on or off
public class TogglecommandsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender.isOp()) {
            BetterSafetyMC.getInstance().setCommandsEnabled(!BetterSafetyMC.getInstance().isCommandsEnabled());

            // Informs all operators that commands are disabled/enabled
            if(BetterSafetyMC.getInstance().isCommandsEnabled()) {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(player.isOp()){
                        player.sendMessage(BetterSafetyMC.prefix + "Command are enabled again.");
                    }
                }
            } else {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(player.isOp()) {
                        player.sendMessage(BetterSafetyMC.prefix + "Commands are now disabled.");
                    }
                }
            }
        }

        return true;
    }
}
