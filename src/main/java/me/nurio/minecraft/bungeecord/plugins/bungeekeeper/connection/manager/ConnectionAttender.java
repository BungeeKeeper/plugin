package me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.manager;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.sockets.ConnectionManager;

import java.io.DataOutputStream;

public class ConnectionAttender extends Thread {

    private PacketQueue outputQueue = ConnectionManager.getOutputQueue();
    private DataOutputStream outputStream = ConnectionManager.getSocket().getOutputStream();

    public void run() {
        while (true) {
            if (!outputQueue.hasPacket()) continue;

            Packet packet = outputQueue.getNextPacket();
            packet.write(outputStream);

            System.out.println(packet.toString());
        }
    }

}