package me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.system;

import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.Packet;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LicenceSystemPacket implements Packet {

    public static final byte PACKET_ID = 4;

    @Getter private boolean accepted;
    @Getter private String message;
    @Getter private String licence;
    @Getter private long expiration;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        accepted = inputStream.readBoolean();
        message = inputStream.readUTF();
        licence = inputStream.readUTF();
        expiration = inputStream.readLong();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeBoolean(accepted);
        outputStream.writeUTF(message);
        outputStream.writeUTF(licence);
        outputStream.writeLong(expiration);
    }

}