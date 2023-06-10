package me.ghaxz.bettersafety.util;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Manages the verifiedPlayers.yml file
public class VerifiedPlayersSave {
    List<String> verifiedUUIDs = new ArrayList<>(); // Stores all verified UUIDs
    private final BetterSafetyMC plugin = BetterSafetyMC.getInstance();
    private final String filepath = "verifiedPlayers.yml"; // specifies the file name for the verifiedPlayers.yml file

    private final File verifiedPlayersFile = new File(plugin.getDataFolder(), filepath); // Creates File object with previously mentioned file name in the plugins data folder
    private final FileConfiguration verifiedPlayersConfig = YamlConfiguration.loadConfiguration(verifiedPlayersFile); // Turns File object into FileConfiguration Object

    // Creates the file if not already created
    public VerifiedPlayersSave() {
        if(!verifiedPlayersFile.exists()) {
            plugin.saveResource(filepath, false);
        }

        readConfigToList();
    }

    // Reads all verified UUIDs from file into the previously mentioned "verifiedUUIDs" List
    private void readConfigToList() {
        List<String> players = verifiedPlayersConfig.getStringList("verifiedPlayers");

        verifiedUUIDs.addAll(players);
    }

    // Adds a new verified player as soon they verify
    public void addVerifiedPlayer(String uuid) {
        verifiedUUIDs.add(uuid); // Adds UUID to "verifiedUUIDs" List
        verifiedPlayersConfig.set("verifiedPlayers", verifiedUUIDs); // Stores the content of the "verifiedUUIDs" List in the "verifiedPlayers" path in the verifiedPlayers.yml file
        saveConfig();
    }

    // Saves verifiedPlayers.yml file
    private void saveConfig() {
        try {
            verifiedPlayersConfig.save(verifiedPlayersFile);
        } catch (IOException e) {
            Bukkit.getLogger().severe("[BetterSafety] Failed saving the verifiedPlayers.yml file: " + e.getMessage());
        }
    }

    public List<String> getVerifiedUUIDs() {
        return verifiedUUIDs;
    }
}
