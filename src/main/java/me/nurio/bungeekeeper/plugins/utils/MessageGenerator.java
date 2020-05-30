package me.nurio.bungeekeeper.plugins.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageGenerator {

    public static TextComponent getKickMessage(String reason) {
        TextComponent message = new TextComponent("BungeeKeeper");
        message.setColor(ChatColor.WHITE);
        message.setBold(true);
        message.addExtra("\n");
        message.addExtra("\n");

        TextComponent cause = new TextComponent(reason);
        cause.setColor(ChatColor.RED);
        cause.setBold(false);
        message.addExtra(cause);

        return message;
    }

}