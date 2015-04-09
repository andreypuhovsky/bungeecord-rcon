package net.borlcand.rcon.config;

import net.cubespace.Yamler.Config.InvalidConfigurationException;

public class ConfigManager {

    public static MainConfig main = new MainConfig();

    static {
        try {
            main.init();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
