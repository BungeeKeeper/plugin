package me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.manager;

import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.Packet;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class PacketQueue {

    private List<Packet> packetQueue = new ArrayList<>();

    public synchronized void registerPacket(Packet packet) {
        System.out.println("Registered packet with id: " + packet.getId());
        packetQueue.add(packet);
    }

    public synchronized Packet getNextPacket() {
        return packetQueue.remove(0);
    }

    @SneakyThrows
    public synchronized boolean hasPacket() {
        boolean hasNext = packetQueue.size() > 0;
        if (!hasNext) Thread.sleep(5);
        return hasNext;
    }

}