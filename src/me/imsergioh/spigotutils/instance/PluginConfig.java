package me.imsergioh.spigotutils.instance;

import me.imsergioha.spigotutils.util.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class PluginConfig {

    private File file;
    private FileConfiguration config;

    public PluginConfig(String configPath, String configName) {
        file = new File(configPath, configName);
        FileUtils.setupFileAndParents(file);
    }

}
