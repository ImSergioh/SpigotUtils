package me.imsergioh.spigotutils.manager;

import me.imsergioh.spigotutils.instance.PluginConfig;
import me.imsergioh.spigotutils.util.ChatUtil;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TabManager {

    private final JavaPlugin plugin;

    private int task;
    private static FileConfiguration config;

    public TabManager(JavaPlugin plugin, PluginConfig pluginConfig){
        this.plugin = plugin;
        config = pluginConfig.getConfig();

        List<String> list = new ArrayList<>();
        list.add("Line1");
        list.add("Line2");
        pluginConfig.registerDefault("tablistHeader", list);
        List<String> list1 = list;
        list1.add("Footer");
        pluginConfig.registerDefault("tablistFooter", list1);
        pluginConfig.save();

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    sendTablist(player);
                });
            }
        },0, 20*3);
    }

    public void sendTablist(Player p){
        CraftPlayer craftplayer = (CraftPlayer) p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        String headerStr = "";
        String footerStr = "";
        for(String all : config.getStringList("tablistHeader")) {
            headerStr += all+"\n";
        } for(String all : config.getStringList("tablistFooter")) {
            footerStr += all+"\n";
        }
        headerStr = replaceLast(headerStr, "\n", "");
        footerStr = replaceLast(footerStr, "\n", "");
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\": \""+ ChatUtil.chatColorWithVariables(p, headerStr)+"\"}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\": \""+ChatUtil.chatColorWithVariables(p, footerStr)+"\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        connection.sendPacket(packet);
    }

    private String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }

}
