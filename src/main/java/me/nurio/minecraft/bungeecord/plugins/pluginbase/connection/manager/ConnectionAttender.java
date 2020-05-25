package me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.manager;

import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.Packet;
import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.sockets.ConnectionManager;

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