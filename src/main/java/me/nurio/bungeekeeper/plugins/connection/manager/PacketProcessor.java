package me.nurio.bungeekeeper.plugins.connection.manager;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.plugins.connection.sockets.ConnectionManager;
import me.nurio.bungeekeeper.plugins.events.PacketAdapterManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Event;

public class PacketProcessor extends Thread {

    private PacketQueue inputQueue = ConnectionManager.getInputQueue();

    public void run() {
        while (true) {
            if (!inputQueue.hasPacket()) continue;
            Packet packet = inputQueue.getNextPacket();

            Event event = PacketAdapterManager.getEvent(packet);
            ProxyServer.getInstance().getPluginManager().callEvent(event);
        }
    }

}