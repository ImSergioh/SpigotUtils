package me.imsergioh.spigotutils.instance;

import me.imsergioh.spigotutils.util.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PluginConfig {

    private File file;
    private FileConfiguration config;

    public PluginConfig(String configPath, String configName) {
        file = new File(configPath, configName);
        FileUtils.setupFileAndParents(file);
    }

    public void set(String path, Object value){
        config.set(path, value);
    }

    public void save(){
        try {
            config.save(file);
        } catch (IOException e){e.printStackTrace();}
    }

    public void load(){
        config = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
