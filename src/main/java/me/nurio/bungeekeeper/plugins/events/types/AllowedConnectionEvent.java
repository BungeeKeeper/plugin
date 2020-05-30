package me.nurio.bungeekeeper.plugins.events.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.nurio.bungeekeeper.plugins.events.EventIdentityManager;
import net.md_5.bungee.api.plugin.Event;

@AllArgsConstructor
public class AllowedConnectionEvent extends Event {

    @Getter private long eventId;

    public Event getEvent() {
        return EventIdentityManager.getById(eventId);
    }

}