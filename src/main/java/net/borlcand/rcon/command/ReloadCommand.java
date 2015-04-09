package net.borlcand.rcon.command;

import net.borlcand.rcon.Rcon;
import net.borlcand.rcon.config.ConfigManager;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super("rconreload", "rcon.admin", "rrcon");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender.hasPermission("rcon.admin") || sender.hasPermission("rcon.reload") || sender.hasPermission("bungeecord.command.reload"))) {
            sender.sendMessage("You have no permission to do this");

            return;
        }

        try {
            ConfigManager.main.reload();
            
            Rcon.instance.stopListener();
            Rcon.instance.startListener();
            
            sender.sendMessage("All Configs reloaded");
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
            sender.sendMessage("Could not reload. Check the logs");
        }
    }
}
