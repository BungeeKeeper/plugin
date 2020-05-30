package me.nurio.bungeekeeper.plugins.listeners;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.nurio.bungeekeeper.plugins.events.types.AllowedConnectionEvent;
import me.nurio.bungeekeeper.plugins.events.types.BlockedConnectionEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

@RequiredArgsConstructor
public class ResponseListener implements Listener {

    @NonNull private Plugin plugin;

    @EventHandler
    public void onAllow(AllowedConnectionEvent event) {
        System.out.println("Event allow");
        PreLoginEvent event1 = (PreLoginEvent) event.getEvent(); // TODO Generify this event
        event1.completeIntent(plugin);
    }

    @EventHandler
    public void onBlock(BlockedConnectionEvent event) {
        System.out.println("Event blocked");
        event.getEvent().completeIntent(plugin);
    }

}