package commands;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(commandSender.isOp()) {
            if(args.length > 0) {
                if(args[0].equalsIgnoreCase("global")) {
                    for (int x = 0; x < 100; x++) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage("");
                        }
                    }
                    Bukkit.broadcastMessage(BetterSafetyMC.prefix + "The chat was cleared by an administrator.");
                    return true;
                }
            }
        }

        for (int x = 0; x < 100; x++) {
            commandSender.sendMessage("");
        }

        commandSender.sendMessage(BetterSafetyMC.prefix + "Your chat was cleared.");

        return true;
    }
}
