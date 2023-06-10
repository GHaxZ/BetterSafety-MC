package me.ghaxz.bettersafety.util;

import me.ghaxz.bettersafety.BetterSafetyMC;

public class ConfigFile {
    private BetterSafetyMC plugin = BetterSafetyMC.getInstance();
    public ConfigFile() {
        createConfig();
    }


    private void createConfig() {
        plugin.getConfig().options().copyDefaults(true);
        plugin.getConfig().addDefault("plugin-settings.verification-enabled", true);

        if(!(plugin.getConfig().get("plugin-settings.verification-enabled") instanceof Boolean)) {
            plugin.getConfig().set("plugin-settings.verification-enabled", true);
        }

        plugin.saveConfig();
    }

    public boolean getVerificationEnabled() {
        return plugin.getConfig().getBoolean("plugin-settings.verification-enabled");
    }
}
