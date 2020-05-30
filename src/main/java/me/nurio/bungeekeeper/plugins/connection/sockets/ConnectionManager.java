package me.nurio.bungeekeeper.plugins.connection.sockets;

import lombok.Getter;
import lombok.SneakyThrows;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketDispenser;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketListener;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketProcessor;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketQueue;
import me.nurio.bungeekeeper.plugins.utils.SchedulerUtil;

public class ConnectionManager {

    @Getter private static MasterSocket socket = new MasterSocket();

    @Getter private static PacketListener listener;
    @Getter private static PacketDispenser attender;
    @Getter private static PacketProcessor processor;

    @Getter private static PacketQueue inputQueue = new PacketQueue();
    @Getter private static PacketQueue outputQueue = new PacketQueue();

    private static boolean autoReconnect = true;

    public static void enable() {
        autoReconnect = true;
        socket.connect();

        listener = new PacketListener();
        listener.start();

        attender = new PacketDispenser();
        attender.start();

        processor = new PacketProcessor();
        processor.start();
    }

    @SneakyThrows
    public static void disable() {
        if (listener.isAlive()) listener.interrupt();
        if (attender.isAlive()) attender.interrupt();
        if (processor.isAlive()) processor.interrupt();

        autoReconnect = false;
        if (!socket.getSslSocket().isClosed()) socket.disconnect();
    }

    public static void reConnect() {
        listener.interrupt();
        attender.interrupt();
        processor.interrupt();

        try {
            socket.disconnect();
        } catch (Exception er) {

        }
        //SchedulerUtil.sleep(20000);
        if (autoReconnect) enable();
    }

}