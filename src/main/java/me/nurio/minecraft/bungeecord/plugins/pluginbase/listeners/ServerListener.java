package me.nurio.minecraft.bungeecord.plugins.pluginbase.listeners;

import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.bungee.DisconnectPacket;
import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.bungee.ServerChangePacket;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

@RequiredArgsConstructor
public class ServerListener implements Listener {

    @NonNull private Plugin plugin;

    @EventHandler
    public void onPlayerChangeServer(ServerConnectedEvent event) {
        ServerChangePacket packet = new ServerChangePacket(
            0,
            event.getServer().getInfo().getName()
        );
        System.out.println("PIP");
        System.out.println(event.getPlayer().getPendingConnection().getVirtualHost().toString());
        System.out.println(packet.toString());
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        DisconnectPacket packet = new DisconnectPacket(
            0,
            player.getServer().getInfo().getName()
        );
        System.out.println(packet.toString());
    }

}