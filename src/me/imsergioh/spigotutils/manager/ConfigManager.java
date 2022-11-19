package me.imsergioh.spigotutils.manager;

import me.imsergioh.spigotutils.instance.PluginConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class ConfigManager {

    private static Set<PluginConfig> configs = new HashSet<>();

    public static void register(PluginConfig pluginConfig){
        if(configs.contains(pluginConfig)){
            return;
        }
        configs.add(pluginConfig);
    }

    public Collection<PluginConfig> getFromPlugin(JavaPlugin plugin){
        Collection<PluginConfig> list = new ArrayList<>();
        for(PluginConfig pluginConfig : configs){
            if(pluginConfig.getPlugin().equals(plugin)){
                list.add(pluginConfig);
            }
        }
        return list;
    }

}
