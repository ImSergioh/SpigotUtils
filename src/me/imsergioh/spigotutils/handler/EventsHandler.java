package me.imsergioh.spigotutils.handler;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EventsHandler {

    private final JavaPlugin plugin;

    public EventsHandler(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public EventsHandler register(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener, plugin);
        return this;
    }

}
