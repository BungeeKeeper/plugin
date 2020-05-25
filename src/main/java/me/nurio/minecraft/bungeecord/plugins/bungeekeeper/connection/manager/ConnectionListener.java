package me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.manager;

import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.packets.Packet;
import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.packets.system.HandshakeSystemPacket;
import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.sockets.ConnectionManager;
import lombok.SneakyThrows;

import java.io.DataInputStream;

public class ConnectionListener extends Thread {

    private PacketQueue inputQueue = ConnectionManager.getInputQueue();
    private DataInputStream inputStream = ConnectionManager.getSocket().getInputStream();

    @SneakyThrows
    public void run() {
        while (true) {
            byte packetId = inputStream.readByte();

            Packet packet = new HandshakeSystemPacket();
            packet.read(inputStream);

            inputQueue.registerPacket(packet);

            System.out.println("RECIBIDO: " + packet.toString());
        }
    }

}