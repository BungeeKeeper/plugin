package me.nurio.bungeekeeper.plugins.connection.sockets;

import lombok.Getter;
import me.nurio.bungeekeeper.plugins.connection.manager.ConnectionAttender;
import me.nurio.bungeekeeper.plugins.connection.manager.ConnectionListener;
import me.nurio.bungeekeeper.plugins.connection.manager.PacketQueue;

public class ConnectionManager {

    @Getter private static MasterSocket socket = new MasterSocket();

    @Getter private static ConnectionListener listener;
    @Getter private static ConnectionAttender attender;

    @Getter private static PacketQueue inputQueue = new PacketQueue();
    @Getter private static PacketQueue outputQueue = new PacketQueue();

    public static void enable() {
        socket.connect();

        listener = new ConnectionListener();
        listener.start();

        attender = new ConnectionAttender();
        attender.start();
    }

    public static void disable() {
        listener.interrupt();
        attender.interrupt();

        socket.disconnect();
    }

}