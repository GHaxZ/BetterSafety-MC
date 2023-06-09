package commands;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TogglechatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender.isOp()) {
            BetterSafetyMC.getInstance().setChatEnabled(!BetterSafetyMC.getInstance().isChatEnabled());

            if(BetterSafetyMC.getInstance().isChatEnabled()) {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(player.isOp()){
                        player.sendMessage(BetterSafetyMC.prefix + "The chat is enabled again.");
                    }
                }
            } else {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(player.isOp()) {
                        player.sendMessage(BetterSafetyMC.prefix + "The chat is now disabled.");

                    }
                }
            }
        }

        return true;
    }
}
