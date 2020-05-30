package me.nurio.bungeekeeper.plugins.events;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.plugins.events.adapters.ConnectionResponseEventAdapter;
import me.nurio.bungeekeeper.plugins.events.adapters.LicencePacketAdapter;
import net.md_5.bungee.api.plugin.Event;

public class PacketAdapterManager {

    public static Event getEvent(Packet packet) {
        return getAdapterById(packet.getId()).getEvent(packet);
    }

    public static PacketAdapter getAdapterById(byte packetId) {
        if (packetId == 3) return new LicencePacketAdapter();
        if (packetId == 30) return new ConnectionResponseEventAdapter();
        return null;
    }

}