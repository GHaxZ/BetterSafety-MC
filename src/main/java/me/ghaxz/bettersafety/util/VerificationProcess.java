package me.ghaxz.bettersafety.util;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class VerificationProcess implements Listener {
    private HashMap<String, String> verificationCodes = new HashMap<>();
    private VerifiedPlayersSave verifiedPlayersSave = new VerifiedPlayersSave();
    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(BetterSafetyMC.getInstance().isVerificationEnabled()) {
            if(!verifiedPlayersSave.getVerifiedUUIDs().contains(player.getUniqueId().toString())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(BetterSafetyMC.getInstance().isVerificationEnabled()) {
            Player player = event.getPlayer();

            if(!verifiedPlayersSave.getVerifiedUUIDs().contains(player.getUniqueId().toString())) {
                verificationCodes.put(player.getUniqueId().toString(), VerificationCodeGenerator.generateCode(10));

                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage("[BetterSafety] Welcome to the server, please verify yourself, by entering the verification code.");
                player.sendMessage(ChatColor.AQUA + verificationCodes.get(player.getUniqueId().toString()));
            }
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();

        if(!verifiedPlayersSave.getVerifiedUUIDs().contains(player.getUniqueId().toString())) {
            if(event.getMessage().trim().equals(verificationCodes.get(player.getUniqueId().toString()))) {
                verificationCodes.remove(player.getUniqueId().toString());
                verifiedPlayersSave.addVerifiedPlayer(player.getUniqueId().toString());

                player.setGameMode(Bukkit.getDefaultGameMode());

                player.sendMessage("[BetterSafety] You are now verified.");
            }
        } else {
            player.sendMessage("[BetterSafety] This is not the correct verification code.");
            player.sendMessage("[BetterSafety] Welcome to the server, please verify yourself, by entering the verification code.");
            player.sendMessage(ChatColor.AQUA + verificationCodes.get(player.getUniqueId().toString()));
        }
    }
}
