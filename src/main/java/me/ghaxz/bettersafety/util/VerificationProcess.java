package me.ghaxz.bettersafety.util;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.util.ChatPaginator;

import java.util.HashMap;
import java.util.List;

public class VerificationProcess implements Listener {
    private HashMap<String, String> verificationCodes = new HashMap<>(); // Stores player UUIDs and their respective verification codes
    private VerifiedPlayersSave verifiedPlayersSave = new VerifiedPlayersSave(); // Contains the verifiedPlayers.yml file where verified players get stored

    // If the player is not verified yet, stop them from moving
    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(BetterSafetyMC.getInstance().isVerificationEnabled()) {
            if(!verifiedPlayersSave.getVerifiedUUIDs().contains(player.getUniqueId().toString())) {
                event.setCancelled(true);
            }
        }
    }

    // If player quits and isn't verified yet, remove their verification code from the HashMap
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        verificationCodes.remove(player.getUniqueId().toString());
    }

    // Checks if the newly joined player is verified, if not set them into spectator mode and start the verification process
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(BetterSafetyMC.getInstance().isVerificationEnabled()) {
            Player player = event.getPlayer();

            if(!verifiedPlayersSave.getVerifiedUUIDs().contains(player.getUniqueId().toString())) {
                verificationCodes.put(player.getUniqueId().toString(), VerificationCodeGenerator.generateCode(10));

                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(BetterSafetyMC.prefix + "Welcome to the server, please verify yourself, by entering the verification code.");
                player.sendMessage(ChatColor.AQUA + verificationCodes.get(player.getUniqueId().toString()));
            }
        }
    }

    // Checks if the player who sent the chat message is verified yet, if not, checks if the code is correct
    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();

        if(BetterSafetyMC.getInstance().isVerificationEnabled()){
            if(!verifiedPlayersSave.getVerifiedUUIDs().contains(player.getUniqueId().toString())) {
                if(event.getMessage().trim().equals(verificationCodes.get(player.getUniqueId().toString()))) {
                    verificationCodes.remove(player.getUniqueId().toString());
                    verifiedPlayersSave.addVerifiedPlayer(player.getUniqueId().toString());

                    player.setGameMode(Bukkit.getDefaultGameMode());

                    player.sendMessage(BetterSafetyMC.prefix + "You are now verified.");
                } else {
                    player.sendMessage(BetterSafetyMC.prefix + "This is not the correct verification code.");
                    player.sendMessage(BetterSafetyMC.prefix + "Welcome to the server, please verify yourself, by entering the verification code.");

                    /*Checks for null, because if a plugin reload occurs during the verification process the verification process
                    breaks and gives the verification code "null". If this happens, the player is asked to reconnect*/
                    player.sendMessage(((verificationCodes.get(player.getUniqueId().toString()) == null) ? ChatColor.RED + "An error occurred, please reconnect." :
                            ChatColor.AQUA + verificationCodes.get(player.getUniqueId().toString())));
                }

                event.setCancelled(true); // Cancel the chat event so other players don't see the verification code sent by other player
            }
        }
    }

    // Stops unverified players from running commands
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if(!verifiedPlayersSave.getVerifiedUUIDs().contains(player.getUniqueId().toString())) {
            event.setCancelled(true);
        }
    }
}
