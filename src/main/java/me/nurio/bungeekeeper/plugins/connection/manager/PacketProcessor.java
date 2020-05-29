package me.nurio.bungeekeeper.plugins.connection.manager;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.plugins.connection.sockets.ConnectionManager;

public class PacketProcessor extends Thread {

    private PacketQueue inputQueue = ConnectionManager.getInputQueue();

    public void run() {
        while (true) {
            if (!inputQueue.hasPacket()) continue;
            Packet packet = inputQueue.getNextPacket();
        }
    }

}