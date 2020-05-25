package me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.sockets;

import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.system.GoodbyeSystemPacket;
import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.system.HandshakeSystemPacket;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class MasterSocket {

    private SSLSocket sslSocket;

    @Getter private DataOutputStream outputStream;
    @Getter private DataInputStream inputStream;

    @SneakyThrows
    public void connect() {
        // Connect to server
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        sslSocket = (SSLSocket) factory.createSocket("local.nurio.me", 6060);

        // Register input and output channels
        outputStream = new DataOutputStream(sslSocket.getOutputStream());
        inputStream = new DataInputStream(sslSocket.getInputStream());

        // Send handshake packet
        handshake();
    }

    @SneakyThrows
    public void disconnect() {
        // Send goodbye packet
        goodbye();

        // Close me.nurio.minecraft.bungeecord.plugins.pluginbase.connection
        outputStream.close();
        inputStream.close();
        sslSocket.close();
    }

    private void handshake() {
        HandshakeSystemPacket packet = new HandshakeSystemPacket(
            "Hola",
            true
        );
        packet.write(outputStream);
    }

    private void goodbye() {
        GoodbyeSystemPacket packet = new GoodbyeSystemPacket(
            (byte) 1
        );
        packet.write(outputStream);
    }

}