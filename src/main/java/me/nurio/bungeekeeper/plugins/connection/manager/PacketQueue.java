package me.nurio.bungeekeeper.plugins.connection.manager;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.plugins.utils.SchedulerUtil;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PacketQueue {

    private Queue<Packet> packetQueue = new ConcurrentLinkedQueue<>();

    public void registerPacket(Packet packet) {
        packetQueue.add(packet);
    }

    public Packet getNextPacket() {
        return packetQueue.poll();
    }

    public boolean hasPacket() {
        boolean hasNext = !packetQueue.isEmpty();
        if (!hasNext) SchedulerUtil.sleep(5);
        return hasNext;
    }

}