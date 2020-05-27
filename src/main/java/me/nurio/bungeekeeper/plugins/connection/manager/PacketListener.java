package me.nurio.bungeekeeper.plugins.connection.manager;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.PacketFactory;
import me.nurio.bungeekeeper.plugins.connection.sockets.ConnectionManager;

import java.io.DataInputStream;

public class PacketListener extends Thread {

    private PacketQueue inputQueue = ConnectionManager.getInputQueue();
    private DataInputStream inputStream = ConnectionManager.getSocket().getInputStream();

    @SneakyThrows
    public void run() {
        while (true) {
            byte packetId = inputStream.readByte();

            Packet packet = PacketFactory.createPacketById(packetId);
            packet.read(inputStream);

            inputQueue.registerPacket(packet);

            System.out.println("RECIBIDO: " + packet.toString());
        }
    }

}