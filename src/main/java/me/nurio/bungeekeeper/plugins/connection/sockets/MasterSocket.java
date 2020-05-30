package me.nurio.bungeekeeper.plugins.connection.sockets;

import lombok.Getter;
import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.system.GoodbyeSystemPacket;
import me.nurio.bungeekeeper.packets.system.HandshakeSystemPacket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class MasterSocket {

    @Getter private SSLSocket sslSocket;

    @Getter private DataOutputStream outputStream;
    @Getter private DataInputStream inputStream;

    @SneakyThrows
    public void connect() {
        // Connect to server
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        sslSocket = (SSLSocket) factory.createSocket("local.nurio.me", 6060);

        System.out.println("Connected to BungeeKeeper!");

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

        // Close connection
        outputStream.close();
        inputStream.close();
        sslSocket.close();
    }

    private void handshake() {
        HandshakeSystemPacket packet = new HandshakeSystemPacket(
            1,
            "224nHfwa3quJ9mWj0a70RCHOMPtEJfPa",
            "Nugget",
            "0.0.0.0",
            25565
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