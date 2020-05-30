package me.nurio.bungeekeeper.plugins.events.adapters;

import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.system.LicenceSystemPacket;
import me.nurio.bungeekeeper.plugins.events.EventAdapter;
import me.nurio.bungeekeeper.plugins.events.types.LicenceAcceptedEvent;
import me.nurio.bungeekeeper.plugins.events.types.LicenceDeniedEvent;
import net.md_5.bungee.api.plugin.Event;

public class LicenceEventAdapter implements EventAdapter {

    @Override
    public Event getEvent(Packet packet) {
        LicenceSystemPacket licencePacket = (LicenceSystemPacket) packet;

        boolean accepted = licencePacket.isAccepted();
        String message = licencePacket.getMessage();
        String licence = licencePacket.getLicence();
        long expiration = licencePacket.getExpiration();

        if (!accepted) {
            return new LicenceDeniedEvent(accepted, message, licence, expiration);
        }
        return new LicenceAcceptedEvent(accepted, message, licence, expiration);
    }

}