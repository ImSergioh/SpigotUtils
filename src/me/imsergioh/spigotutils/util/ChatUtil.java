package me.imsergioh.spigotutils.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static String chatColor(String message){
        return message.replace("&", "§");
    }

    public static String chatColorWithVariables(Player player, String message){
        try {
            message = PlaceholderAPI.setPlaceholders(player, message);
        } catch (Exception e){}
        return chatColor(message);
    }

}
