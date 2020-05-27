package me.nurio.bungeekeeper.plugins.listeners;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.nurio.bungeekeeper.packets.bungee.HandshakePacket;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketQueue;
import me.nurio.bungeekeeper.plugins.connection.sockets.ConnectionManager;
import me.nurio.bungeekeeper.plugins.manager.EventIdentityManager;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.protocol.packet.Handshake;

import java.net.InetSocketAddress;

@RequiredArgsConstructor
public class PingListener implements Listener {

    @NonNull private Plugin plugin;
    private PacketQueue outputQueue = ConnectionManager.getOutputQueue();

    @EventHandler
    public void onPing(PlayerHandshakeEvent event) {
        PendingConnection connection = event.getConnection();
        Handshake handshake = event.getHandshake();


        HandshakePacket packet = new HandshakePacket(
            (InetSocketAddress) connection.getSocketAddress(),
            handshake.getHost(),
            (short) handshake.getPort(),
            connection.getVersion()
        );
        outputQueue.registerPacket(packet);
        EventIdentityManager.register(packet.getEventId(), event);

        System.out.println(packet.toString());
    }

}