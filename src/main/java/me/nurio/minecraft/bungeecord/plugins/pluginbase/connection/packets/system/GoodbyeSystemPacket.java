package me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.system;

import me.nurio.minecraft.bungeecord.plugins.pluginbase.connection.packets.Packet;
import lombok.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoodbyeSystemPacket implements Packet {

    public static final byte PACKET_ID = 2;

    @Getter private byte reason;

    public byte getId() {
        return PACKET_ID;
    }

    @SneakyThrows
    public void read(DataInputStream inputStream) {
        reason = inputStream.readByte();
    }

    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeByte(reason);
    }

}