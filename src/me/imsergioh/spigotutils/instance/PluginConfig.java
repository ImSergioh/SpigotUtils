package me.imsergioh.spigotutils.instance;

import me.imsergioh.spigotutils.manager.ConfigManager;
import me.imsergioh.spigotutils.util.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class PluginConfig {

    private JavaPlugin plugin;

    private final File file;
    private FileConfiguration config;

    public PluginConfig(JavaPlugin plugin, String configPath, String configName) {
        this.plugin = plugin;
        file = new File(configPath, configName);
        FileUtils.setupFileAndParents(file);
        ConfigManager.register(this);
    }

    public PluginConfig registerDefault(String path, Object value){
        if(config.contains(path)){
            return this;
        }
        config.set(path, value);
        return this;
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

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
