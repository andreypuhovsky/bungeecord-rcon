package net.borlcand.rcon;

import de.exlll.configlib.YamlConfigurations;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.borlcand.rcon.command.ReloadCommand;
import net.borlcand.rcon.config.ConfigManager;
import net.borlcand.rcon.config.MainConfig;
import net.borlcand.rcon.server.RconServer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class Rcon extends Plugin {

    public static Rcon instance;
    public static ProxyServer proxy = ProxyServer.getInstance();
    public MainConfig config = YamlConfigurations.update(Paths.get("plugins/Warden/config.yml"), MainConfig.class);

    private RconServer rconServer;
    private static final Logger logger = ProxyServer.getInstance().getLogger();

    @Override
    public void onEnable() {
        instance = this;
        proxy = ProxyServer.getInstance();

        registerCommands();

        startListener();
    }

    public void startListener() {
        if (!ConfigManager.config.Rcon_Enabled){
            logger.warning("RCON disabled");
            return;
        }
        
        SocketAddress address = new InetSocketAddress(ConfigManager.config.Rcon_Port);
        rconServer = new RconServer(this.getProxy(), ConfigManager.config.Rcon_Password);

        logger.log(Level.INFO, "Binding rcon to address: {0}...", address);

        ChannelFuture future = rconServer.bind(address);
        Channel channel = future.awaitUninterruptibly().channel();

        if (!channel.isActive()) {
            logger.warning("Failed to bind rcon port. Address already in use?");
        }
    }

    public void stopListener() {
        if (rconServer != null) {
            logger.log(Level.INFO, "Trying to stop RCON listener");
            
            rconServer.shutdown();
        }
    }

    private void registerCommands() {
        proxy.getPluginManager().registerCommand(this, new ReloadCommand());
    }

    @Override
    public void onDisable() {
        stopListener();
    }

}
