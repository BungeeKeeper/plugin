package me.nurio.minecraft.bungeecord.plugins.bungeekeeper;

import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.sockets.ConnectionManager;
import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.listeners.PlayerConnectionListener;
import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.listeners.PingListener;
import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.listeners.ServerListener;
import net.md_5.bungee.api.plugin.Plugin;

public class MClass extends Plugin {

    @Override
    public void onEnable() {
        getLogger().info("Anguilas Activadas");
        getProxy().getPluginManager().registerListener(this, new PlayerConnectionListener(this));
        getProxy().getPluginManager().registerListener(this, new PingListener(this));
        getProxy().getPluginManager().registerListener(this, new ServerListener(this));

        ConnectionManager.enable();
    }

    @Override
    public void onDisable() {
        ConnectionManager.disable();
    }

}