package me.nurio.bungeekeeper.plugins;

import me.nurio.bungeekeeper.plugins.connection.sockets.ConnectionManager;
import me.nurio.bungeekeeper.plugins.events.EventIdentityManager;
import me.nurio.bungeekeeper.plugins.listeners.PingListener;
import me.nurio.bungeekeeper.plugins.listeners.PlayerConnectionListener;
import me.nurio.bungeekeeper.plugins.listeners.ResponseListener;
import me.nurio.bungeekeeper.plugins.listeners.ServerListener;
import net.md_5.bungee.api.plugin.Plugin;

public class MClass extends Plugin {

    @Override
    public void onEnable() {
        getLogger().info("Anguilas Activadas");
        getProxy().getPluginManager().registerListener(this, new PlayerConnectionListener(this));
        getProxy().getPluginManager().registerListener(this, new PingListener(this));
        getProxy().getPluginManager().registerListener(this, new ServerListener(this));
        getProxy().getPluginManager().registerListener(this, new ResponseListener(this));

        EventIdentityManager.enable(this);
        ConnectionManager.enable();
    }

    @Override
    public void onDisable() {
        ConnectionManager.disable();
    }

}
