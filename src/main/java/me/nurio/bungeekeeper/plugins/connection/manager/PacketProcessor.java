package me.nurio.bungeekeeper.plugins.connection.manager;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.plugins.MClass;
import me.nurio.bungeekeeper.plugins.connection.sockets.ConnectionManager;
import me.nurio.bungeekeeper.plugins.events.EventAdapterManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Event;

public class PacketProcessor extends Thread {

    private PacketQueue inputQueue = ConnectionManager.getInputQueue();

    public void run() {
        while (!isInterrupted()) {
            if (!inputQueue.hasPacket()) continue;
            Packet packet = inputQueue.getNextPacket();

            ProxyServer.getInstance().getScheduler().runAsync(MClass.getInstance(), () -> {
                Event event = EventAdapterManager.getEvent(packet);
                ProxyServer.getInstance().getPluginManager().callEvent(event);
            });
        }
    }

}