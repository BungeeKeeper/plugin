package me.nurio.bungeekeeper.plugins.connection.manager;

import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.plugins.utils.SchedulerUtil;

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
        if (!hasNext) SchedulerUtil.sleep(5);
        return hasNext;
    }

}