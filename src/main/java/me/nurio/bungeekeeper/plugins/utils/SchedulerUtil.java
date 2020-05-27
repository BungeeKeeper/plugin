package me.nurio.bungeekeeper.plugins.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SchedulerUtil {

    public static long getMillsFromTimeAgo(TimeUnit timeUnit, long units) {
        long mills = TimeUnit.MILLISECONDS.convert(units, timeUnit);
        return System.currentTimeMillis() - mills;
    }

}