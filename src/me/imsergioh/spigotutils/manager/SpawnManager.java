package me.imsergioh.spigotutils.manager;

import me.imsergioh.spigotutils.instance.PluginConfig;
import me.imsergioh.spigotutils.util.LocationsUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnManager {

    private final PluginConfig pluginConfig;

    private Location spawn;

    public SpawnManager(String configPath, String configName){
        pluginConfig = new PluginConfig(configPath, configName);
    }

    public void loadSpawn(){
        if(pluginConfig.getConfig().contains("spawn")){
            try {
                spawn = LocationsUtil.stringToLocation(pluginConfig.getConfig().getString("spawn"));
            } catch (Exception e){}
        }
    }

    public void setSpawn(Location location){
        this.spawn = location;
        pluginConfig.getConfig().set("spawn", LocationsUtil.locationToString(location));
    }

    public void teleportSpawn(Player player){
        if(spawn != null){
            player.teleport(spawn);
        }
    }

    public Location getSpawn() {
        return spawn;
    }
}
