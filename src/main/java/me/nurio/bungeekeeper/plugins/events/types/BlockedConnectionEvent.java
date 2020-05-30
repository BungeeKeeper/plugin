package me.nurio.bungeekeeper.plugins.events.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.nurio.bungeekeeper.plugins.events.EventIdentityManager;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Event;

@AllArgsConstructor
public class BlockedConnectionEvent extends Event {

    @Getter private long eventId;
    @Getter private String message;

    public PreLoginEvent getEvent() {
        return (PreLoginEvent) EventIdentityManager.getById(eventId);
    }

}