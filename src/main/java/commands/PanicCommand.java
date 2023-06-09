package commands;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import util.PlayerManager;

public class PanicCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender.isOp()) {
            PlayerManager.kickAllPlayers(commandSender);

            BetterSafetyMC.getInstance().setSafemodeEnabled(true);
            Bukkit.broadcastMessage(BetterSafetyMC.prefix + "The Server is now in Safe-mode. No more players can connect.");
        }

        return true;
    }
}
