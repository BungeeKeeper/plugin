package me.nurio.minecraft.bungeecord.plugins.bungeekeeper.listeners;

import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.packets.bungee.HandshakePacket;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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

        System.out.println(packet.toString());
    }

}