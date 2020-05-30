package me.nurio.bungeekeeper.plugins.listeners;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.nurio.bungeekeeper.packets.bungee.ConnectionPacket;
import me.nurio.bungeekeeper.packets.bungee.PostConnectionPacket;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketQueue;
import me.nurio.bungeekeeper.plugins.connection.sockets.ConnectionManager;
import me.nurio.bungeekeeper.plugins.events.EventIdentityManager;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.net.InetSocketAddress;

@RequiredArgsConstructor
public class PlayerConnectionListener implements Listener {

    @NonNull private Plugin plugin;
    private PacketQueue outputQueue = ConnectionManager.getOutputQueue();

    @EventHandler
    public void onPlayerConnect(PreLoginEvent event) {
        event.registerIntent(plugin);

        PendingConnection connection = event.getConnection();

        ConnectionPacket packet = new ConnectionPacket(
            connection.getName(),
            (InetSocketAddress) connection.getSocketAddress(),
            connection.getVersion()
        );

        EventIdentityManager.register(packet.getEventId(), event);
        outputQueue.registerPacket(packet);
    }

    @EventHandler
    public void onPlayerLogin(LoginEvent event) {
        event.registerIntent(plugin);
        PendingConnection connection = event.getConnection();

        PostConnectionPacket packet = new PostConnectionPacket(
            connection.getName(),
            connection.getUniqueId(),
            (InetSocketAddress) connection.getSocketAddress(),
            connection.getVersion()
        );

        EventIdentityManager.register(packet.getEventId(), event);
        outputQueue.registerPacket(packet);

        event.completeIntent(plugin);
    }

    @EventHandler
    public void onPlayerPostLogin(PostLoginEvent event) {
        PendingConnection connection = event.getPlayer().getPendingConnection();

        PostConnectionPacket packet = new PostConnectionPacket(
            connection.getName(),
            connection.getUniqueId(),
            (InetSocketAddress) connection.getSocketAddress(),
            connection.getVersion()
        );

        EventIdentityManager.register(packet.getEventId(), event);
        outputQueue.registerPacket(packet);
    }

}