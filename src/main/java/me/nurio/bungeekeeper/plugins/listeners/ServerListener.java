package me.nurio.bungeekeeper.plugins.listeners;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.nurio.bungeekeeper.packets.bungee.DisconnectPacket;
import me.nurio.bungeekeeper.packets.bungee.ServerChangePacket;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketQueue;
import me.nurio.bungeekeeper.plugins.connection.sockets.ConnectionManager;
import me.nurio.bungeekeeper.plugins.events.EventIdentityManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.net.InetSocketAddress;

@RequiredArgsConstructor
public class ServerListener implements Listener {

    @NonNull private Plugin plugin;
    private PacketQueue outputQueue = ConnectionManager.getOutputQueue();

    @EventHandler
    public void onPlayerChangeServer(ServerConnectedEvent event) {
        ServerChangePacket packet = new ServerChangePacket(
            event.getPlayer().getName(),
            event.getPlayer().getUniqueId(),
            (InetSocketAddress) event.getPlayer().getSocketAddress(),
            event.getServer().getInfo().getName()
        );
        outputQueue.registerPacket(packet);
        EventIdentityManager.register(packet.getEventId(), event);
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        DisconnectPacket packet = new DisconnectPacket(
            player.getName(),
            player.getUniqueId(),
            (InetSocketAddress) event.getPlayer().getSocketAddress()
        );
        outputQueue.registerPacket(packet);
        EventIdentityManager.register(packet.getEventId(), event);
    }

}