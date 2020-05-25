package me.nurio.minecraft.bungeecord.plugins.bungeekeeper.listeners;

import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.packets.bungee.ConnectionPacket;
import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.packets.bungee.PostConnectionPacket;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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

    @EventHandler
    public void onPlayerConnect(PreLoginEvent event) {
        event.registerIntent(plugin);

        PendingConnection connection = event.getConnection();

        ConnectionPacket packet = new ConnectionPacket(
            connection.getName(),
            (InetSocketAddress) connection.getSocketAddress(),
            connection.getVersion()
        );

        System.out.println(connection.getVirtualHost());
        System.out.println(packet.toString());

        event.completeIntent(plugin);
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

        System.out.println(connection.getVirtualHost());
        System.out.println(packet.toString());
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

        System.out.println(connection.getVirtualHost());
        System.out.println(packet.toString());
    }

}