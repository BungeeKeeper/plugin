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
        try {
            while (!isInterrupted()) {
                byte packetId = inputStream.readByte();
                processPacket(packetId);
            }
        } catch (Exception er) {
            System.err.println("Connection loss.");
            ConnectionManager.reConnect();
        }
    }

    private void processPacket(byte packetId) {
        try {
            Packet packet = PacketFactory.createPacketById(packetId);
            packet.read(inputStream);

            inputQueue.registerPacket(packet);
        } catch (Exception er) {
            System.err.println("Something went wrong reading a packet. (ID: " + packetId + ")");
        }
    }

}