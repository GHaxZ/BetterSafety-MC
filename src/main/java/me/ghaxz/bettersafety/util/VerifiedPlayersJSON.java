package me.ghaxz.bettersafety.util;

import me.ghaxz.bettersafety.BetterSafetyMC;
import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class VerifiedPlayersJSON {
    private String filepath = "verifiedPlayers.json";
    private ArrayList<String> verifiedUUIDs = new ArrayList<>();
    private PrintWriter writer;
    private JSONObject verifiedUUIDsJSON;
    private JSONArray verifiedUUIDsJSONArray;

    public VerifiedPlayersJSON() {
        Bukkit.broadcastMessage("Debug 1");

        verifiedUUIDsJSON = openJSON();

        if(verifiedUUIDsJSON == null) {
            Bukkit.getLogger().severe("[BetterSafety] Couldn't load verifiedPlayers.json file. Verification process has been disabled.");
            BetterSafetyMC.getInstance().setVerificationEnabled(false);
        } else {
            appendVerifiedUUIDs((JSONArray) verifiedUUIDsJSON.get("verifiedPlayers"));
            Bukkit.getLogger().info("[BetterSafety] Successfully loaded verifiedPlayers.json");
        }
    }

    private JSONObject openJSON() {
        JSONParser jsonParser = new JSONParser();

        InputStreamReader reader;

        if(getClass().getResourceAsStream(filepath) == null) {
            Bukkit.getLogger().warning("[BetterSafety] verifiedPlayers.json file not found, creating new file.");
            return createJSON();
        } else {
            reader = new InputStreamReader(this.getClass().getResourceAsStream(filepath));
        }

        try {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObj = (JSONObject) obj;
            if(jsonObj.containsKey("verifiedPlayers")) {
                verifiedUUIDsJSONArray = (JSONArray) jsonObj.get("verifiedPlayers");
            } else {
                Bukkit.getLogger().warning("[BetterSafety] The verifiedPlayers array could not be found in the verifiedPlayers.json file, creating new file.");
                return createJSON();
            }

            return (JSONObject) obj;
        } catch (IOException | ParseException e) {
            Bukkit.getLogger().severe("[BetterSafety] An error occurred when trying to parse the verifiedPlayers.json file: \n");
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject createJSON() {
        try {
            writer = new PrintWriter(new File(filepath));

            JSONObject jsonObj = new JSONObject();
            verifiedUUIDsJSONArray = new JSONArray();
            jsonObj.put("verifiedPlayers", verifiedUUIDsJSONArray);

            writer.println(jsonObj.toJSONString());

            return jsonObj;
        } catch (IOException e) {
            Bukkit.getLogger().severe("[BetterSafety] An error occurred when trying to create new verifiedPlayers.json file: \n" + e.getMessage());
            return null;
        }
    }

    public void addVerifiedPlayer(String uuid) {
        verifiedUUIDsJSONArray.add(uuid);
        writer.println(verifiedUUIDsJSON.toJSONString());
        verifiedUUIDs.add(uuid);
    }

    private void appendVerifiedUUIDs(JSONArray verifiedUUIDsJSON) {
        for(int x = 0; x < verifiedUUIDsJSON.size(); x++) {
            verifiedUUIDs.add((String) verifiedUUIDsJSON.get(x));
        }
    }

    public ArrayList<String> getVerifiedUUIDs() {
        return verifiedUUIDs;
    }
}
