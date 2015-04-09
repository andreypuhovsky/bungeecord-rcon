package net.borlcand.rcon.commandsender;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Collection;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;

public class RconCommandSender implements CommandSender {

    private final StringBuffer buffer = new StringBuffer();

    private final ProxyServer server;

    public RconCommandSender(ProxyServer server) {
        this.server = server;
    }

    public ProxyServer getServer() {
        return server;
    }

    public String flush() {
        String result = buffer.toString();
        buffer.setLength(0);
        return result;
    }

    @Override
    public String getName() {
        return "Rcon";
    }

    @Override
    public void sendMessage(String message) {
        buffer.append(message).append("\n");
    }

    @Override
    public void sendMessages(String... messages) {
        for (String line : messages) {
            sendMessage(line);
        }
    }

    @Override
    public void sendMessage(BaseComponent... message) {
        for (BaseComponent line : message) {
            sendMessage(line);
        }
    }

    @Override
    public void sendMessage(BaseComponent message) {
        sendMessage(message.toLegacyText());
    }

    @Override
    public Collection<String> getGroups() {
        return new ArrayList<>();
    }

    @Override
    public void addGroups(String... groups) {
    }

    @Override
    public void removeGroups(String... groups) {
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }

    @Override
    public void setPermission(String permission, boolean value) {
    }

    @Override
    public Collection<String> getPermissions() {
        return new ArrayList<>();
    }
}
