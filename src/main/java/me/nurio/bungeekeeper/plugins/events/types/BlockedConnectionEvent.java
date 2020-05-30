package me.nurio.bungeekeeper.plugins.events.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.nurio.bungeekeeper.plugins.events.EventIdentityManager;
import net.md_5.bungee.api.event.AsyncEvent;
import net.md_5.bungee.api.plugin.Event;

@AllArgsConstructor
public class BlockedConnectionEvent extends Event {

    @Getter private long eventId;
    @Getter private String message;

    public AsyncEvent<Event> getEvent() {
        return (AsyncEvent<Event>) EventIdentityManager.getById(eventId);
    }

}