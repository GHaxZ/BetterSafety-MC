package commands;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import util.PlayerManager;

public class SafemodeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender.isOp()) {
            PlayerManager.toggleSafemode();
        }

        return true;
    }
}
