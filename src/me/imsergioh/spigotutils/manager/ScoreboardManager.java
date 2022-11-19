package me.imsergioh.spigotutils.manager;

import me.imsergioh.spigotutils.instance.PluginConfig;
import me.imsergioh.spigotutils.instance.PluginScoreboard;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScoreboardManager {

    private JavaPlugin plugin;

    private static Map<String, PluginScoreboard> scoreboards = new HashMap<>();
    private static File dir;

    public ScoreboardManager(JavaPlugin plugin){
        this.plugin = plugin;
        dir = new File(plugin.getDataFolder()+"/scoreboards");
        dir.mkdirs();
        Arrays.stream(Objects.requireNonNull(dir.listFiles())).forEach(file -> {
            PluginConfig config = new PluginConfig(plugin, file.getParentFile().toString(), file.getName());
            System.out.println("Parsing scoreboard '"+config.fileName()+"'..");
            PluginScoreboard pluginScoreboard = new PluginScoreboard(plugin, config);
            scoreboards.put(config.fileName(), pluginScoreboard);
            System.out.println("Scoreboard config registered: "+config.fileName()+"!");
        });
    }

    public PluginScoreboard getFromName(String name){
        return scoreboards.get(name);
    }

}
