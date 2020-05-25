package me.nurio.minecraft.bungeecord.plugins.bungeekeeper.connection.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface Packet {

    byte getId();
    void read(DataInputStream inputStream);
    void write(DataOutputStream outputStream);

}