package me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.packets.bungee;

import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.packets.Packet;
import lombok.*;
import me.nurio.minecraft.bungeecord.plugins.bungeekeeper.utils.IdentityUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DisconnectPacket implements Packet {

    public static final byte PACKET_ID = 30;

    private long eventId = IdentityUtil.timeBasedId();

    // Disconnected server's name
    @NonNull private String serverName;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        serverName = inputStream.readUTF();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeUTF(serverName);
    }

}