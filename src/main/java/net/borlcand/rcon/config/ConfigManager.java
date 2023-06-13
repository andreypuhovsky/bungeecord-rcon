package net.borlcand.rcon.config;

import de.exlll.configlib.YamlConfigurations;

import java.nio.file.Paths;

public class ConfigManager {

    public static String CONFIG_PATH = "plugins/BungeeRcon/config.yml";

    public static MainConfig config = YamlConfigurations.update(Paths.get(CONFIG_PATH), MainConfig.class);



    public static void reload(){
        config = YamlConfigurations.update(Paths.get(CONFIG_PATH), MainConfig.class);
    }


}
