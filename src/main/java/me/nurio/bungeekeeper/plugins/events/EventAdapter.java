package me.nurio.bungeekeeper.plugins.events;

import me.nurio.bungeekeeper.packets.Packet;
import net.md_5.bungee.api.plugin.Event;

public interface EventAdapter {

    Event getEvent(Packet packet);

}