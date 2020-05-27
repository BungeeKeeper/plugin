package me.nurio.bungeekeeper.plugins.connection.sockets;

import lombok.Getter;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketDispenser;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketListener;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketQueue;

public class ConnectionManager {

    @Getter private static MasterSocket socket = new MasterSocket();

    @Getter private static PacketListener listener;
    @Getter private static PacketDispenser attender;

    @Getter private static PacketQueue inputQueue = new PacketQueue();
    @Getter private static PacketQueue outputQueue = new PacketQueue();

    public static void enable() {
        socket.connect();

        listener = new PacketListener();
        listener.start();

        attender = new PacketDispenser();
        attender.start();
    }

    public static void disable() {
        listener.interrupt();
        attender.interrupt();

        socket.disconnect();
    }

}