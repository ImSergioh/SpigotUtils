package me.imsergioh.spigotutils.instance;

import me.imsergioh.spigotutils.instance.player.PlayerScoreboard;
import me.imsergioh.spigotutils.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PluginScoreboard {

    private final JavaPlugin plugin;

    private final String displayName;
    private final int updateTicks;

    private final List<String> lines;

    private FileConfiguration config;

    private HashMap<UUID, PlayerScoreboard> scoreboards = new HashMap<>();

    private int task;

    public PluginScoreboard(JavaPlugin plugin, PluginConfig pluginConfig){
        this.plugin = plugin;
        config = pluginConfig.getConfig();
        displayName = ChatUtil.chatColor(config.getString("title"));
        updateTicks = config.getInt("updateTicks");
        lines = config.getStringList("lines");

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    if (scoreboards.isEmpty()) {
                        return;
                    }
                    scoreboards.values().forEach(PlayerScoreboard::updateScoreboard);
                } catch (Exception e) {}
            }
        },20, updateTicks);
    }

    public void register(Player player){
        if(scoreboards.containsKey(player.getUniqueId())){
            return;
        }
        scoreboards.put(player.getUniqueId(), new PlayerScoreboard(this, player));
    }

    public void unregister(Player player){
        scoreboards.remove(player.getUniqueId());
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getLines() {
        return lines;
    }
}
