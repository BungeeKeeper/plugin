package me.nurio.bungeekeeper.plugins.events;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.*;
import me.nurio.bungeekeeper.packets.system.GoodbyeSystemPacket;
import me.nurio.bungeekeeper.packets.system.HandshakeSystemPacket;
import me.nurio.bungeekeeper.packets.system.LicenceSystemPacket;
import me.nurio.bungeekeeper.plugins.events.adapters.LicencePacketAdapter;
import me.nurio.bungeekeeper.plugins.events.types.LicenceAcceptedEvent;
import net.md_5.bungee.api.plugin.Event;

public class PacketAdapterManager {

    public static Event getEvent(Packet packet) {
        return getAdapterById(packet.getId()).getEvent(packet);
    }

    public static PacketAdapter getAdapterById(byte packetId) {


//        if (packetId == 1) return new HandshakeSystemPacket();
//        if (packetId == 2) return new GoodbyeSystemPacket();
        if (packetId == 3) return new LicencePacketAdapter();
//
//        if (packetId == 20) return new HandshakePacket();
//        if (packetId == 21) return new ConnectionPacket();
//        if (packetId == 22) return new PostConnectionPacket();
//        if (packetId == 23) return new ServerChangePacket();
//        if (packetId == 24) return new DisconnectPacket();

        return null;
    }

}