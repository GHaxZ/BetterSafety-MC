package me.ghaxz.bettersafety.util;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerifiedPlayersSave {
    List<String> verifiedUUIDs = new ArrayList<>();
    private BetterSafetyMC plugin = BetterSafetyMC.getInstance();
    private final String filepath = "verifiedPlayers.yml";

    private File verifiedPlayersFile = new File(plugin.getDataFolder(), filepath);
    private FileConfiguration verifiedPlayersConfig = YamlConfiguration.loadConfiguration(verifiedPlayersFile);

    public VerifiedPlayersSave() {
        if(!verifiedPlayersFile.exists()) {
            plugin.saveResource(filepath, false);
        }

        readConfigToList();
    }

    private void readConfigToList() {
        List<String> players = verifiedPlayersConfig.getStringList("verifiedPlayers");

        verifiedUUIDs.addAll(players);
    }

    public void addVerifiedPlayer(String uuid) {
        verifiedUUIDs.add(uuid);
        verifiedPlayersConfig.set("verifiedPlayers", verifiedUUIDs);
        saveConfig();
    }

    private void saveConfig() {
        try {
            verifiedPlayersConfig.save(verifiedPlayersFile);
        } catch (IOException e) {
            Bukkit.getLogger().warning("[BetterSafety] Failed saving the verifiedPlayers.yml file.");
        }
    }

    public List<String> getVerifiedUUIDs() {
        return verifiedUUIDs;
    }
}
