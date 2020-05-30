package me.nurio.bungeekeeper.plugins.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.bungee.ConnectionResponsePacket;
import me.nurio.bungeekeeper.plugins.events.PacketAdapter;
import me.nurio.bungeekeeper.plugins.events.types.AllowedConnectionEvent;
import me.nurio.bungeekeeper.plugins.events.types.BlockedConnectionEvent;
import net.md_5.bungee.api.plugin.Event;

public class ConnectionResponseEventAdapter implements PacketAdapter {

    @Override
    public Event getEvent(Packet packet) {
        ConnectionResponsePacket responsePacket = (ConnectionResponsePacket) packet;

        if (!responsePacket.isAllowed()) {
            return new BlockedConnectionEvent(responsePacket.getEventId(), responsePacket.getReason());
        }
        return new AllowedConnectionEvent(responsePacket.getEventId());
    }

}