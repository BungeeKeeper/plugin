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

@RequiredArgsConstructor
public class ServerListener implements Listener {

    @NonNull private Plugin plugin;
    private PacketQueue outputQueue = ConnectionManager.getOutputQueue();

    @EventHandler
    public void onPlayerChangeServer(ServerConnectedEvent event) {
        ProxiedPlayer player = event.getPlayer();

        ServerChangePacket packet = new ServerChangePacket(
            player.getName(),
            player.getUniqueId(),
            player.getSocketAddress().toString(),
            event.getServer().getInfo().getName()
        );

        EventIdentityManager.register(packet.getEventId(), event);
        outputQueue.registerPacket(packet);
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        DisconnectPacket packet = new DisconnectPacket(
            player.getName(),
            player.getUniqueId(),
            player.getSocketAddress().toString()
        );

        EventIdentityManager.register(packet.getEventId(), event);
        outputQueue.registerPacket(packet);
    }

}