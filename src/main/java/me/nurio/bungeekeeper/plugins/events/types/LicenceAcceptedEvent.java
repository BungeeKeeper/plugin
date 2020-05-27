package me.nurio.bungeekeeper.plugins.events.types;

import lombok.AllArgsConstructor;
import net.md_5.bungee.api.plugin.Event;

@AllArgsConstructor
public class LicenceAcceptedEvent extends Event {

    private boolean accepted;
    private String message;
    private String licence;
    private long expiration;

}