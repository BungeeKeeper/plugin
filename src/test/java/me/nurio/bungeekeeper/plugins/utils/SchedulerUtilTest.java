package me.nurio.bungeekeeper.plugins.utils;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SchedulerUtilTest {

    @Test
    public void getMillsFromTimeAgo() {
        long result = SchedulerUtil.getMillsFromTimeAgo(TimeUnit.MINUTES, 10);
        assertEquals(System.currentTimeMillis() - (10 * 60 * 1000), result);
    }

}