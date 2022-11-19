package me.imsergioh.spigotutils.util;

import java.util.List;

public class ConfigsUtil {

    public static String getLinesFromStringList(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        for(String all : list){
            stringBuilder.append(ChatUtil.chatColor(all)).append("\n");
        }
        return stringBuilder.toString();
    }

}
