package me.nurio.bungeekeeper.plugins.manager;

import me.nurio.bungeekeeper.plugins.utils.SchedulerUtil;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class EventIdentityManager {

    private static Map<Long, Event> eventMap = new ConcurrentHashMap<>();

    public static void enable(Plugin plugin) {
        plugin.getProxy().getScheduler().schedule(plugin, EventIdentityManager::garbageCollector, 10, 10, TimeUnit.MINUTES);
    }

    public static Event getById(long eventId) {
        if (!eventMap.containsKey(eventId)) {
            System.err.println("Requested event was not found (id: " + eventId + ").");
            return null;
        }

        return eventMap.get(eventId);
    }

    public static void register(long eventId, Event event) {
        eventMap.put(eventId, event);
    }

    public static void remove(long eventId) {
        eventMap.remove(eventId);
    }

    private static void garbageCollector() {
        long timeCap = SchedulerUtil.getMillsFromTimeAgo(TimeUnit.MINUTES, 10);

        eventMap.keySet().stream()
            .map(id -> id / 10000)
            .filter(time -> time < timeCap)
            .forEach(eventMap::remove);
    }

}