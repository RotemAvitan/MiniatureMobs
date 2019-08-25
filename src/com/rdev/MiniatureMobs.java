package com.rdev;

import com.rdev.commands.MainCommand;
import com.rdev.configuration.ConfigurationManager;
import com.rdev.listeners.MachineListeners;
import com.rdev.mob.MobsManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MiniatureMobs extends JavaPlugin {

    @Getter private static MiniatureMobs instance;
    @Getter public MobsManager mobsManager;
    @Getter public ConfigurationManager configurationManager;

    @Override
    public void onEnable() {
        instance = this;

        registerEvents();
        this.loadMobsManager();
        this.loadConfigurationManager();

        configurationManager.registerConfigMobs();

        getCommand("miniaturemobs").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        mobsManager.removeAll();
    }

    private void loadMobsManager() {
        this.mobsManager = new MobsManager();
    }

    private void loadConfigurationManager() {
        this.configurationManager = new ConfigurationManager();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new MachineListeners(), this);
    }

}
