package net.borlcand.rcon.config;

import java.io.File;
import net.borlcand.rcon.Rcon;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;

public class MainConfig extends Config {

    public MainConfig() {
        CONFIG_FILE = new File(Rcon.instance.getDataFolder(), "config.yml");
    }

    @Comment("If you want to disable RCON listener")
    public Boolean Rcon_Enabled = true;
    @Comment("Port you want RCON to listen")
    public Integer Rcon_Port = 39999;
    @Comment("Password you want to authenticate connection with")
    public String Rcon_Password = "12fsdaf5s4f5asdf3456";
    @Comment("This can be used if you want RCON response to be (not) colored")
    public Boolean Rcon_Colored = true;
}
