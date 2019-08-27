package com.rdev;

import com.rdev.commands.MainCommand;
import com.rdev.configuration.ConfigurationManager;
import com.rdev.listeners.MachineListeners;
import com.rdev.mob.MobsManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of the MiniatureMobs project.
 */
public class MiniatureMobs extends JavaPlugin {

    @Getter private static MiniatureMobs instance;
    @Getter public MobsManager mobsManager;
    @Getter public ConfigurationManager configurationManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {
        instance = this;

        registerEvents();
        this.loadMobsManager();
        this.loadConfigurationManager();

        configurationManager.registerConfigMobs();

        getCommand("miniaturemobs").setExecutor(new MainCommand());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDisable() {
        mobsManager.removeAll();
    }

    /**
     *  Initializes the {@link MobsManager}.
     */
    private void loadMobsManager() {
        this.mobsManager = new MobsManager();
    }

    /**
     *  Initializes the {@link ConfigurationManager}.
     */
    private void loadConfigurationManager() {
        this.configurationManager = new ConfigurationManager();
    }

    /**
     * Register all the listener in the project.
     */
    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new MachineListeners(), this);
    }

}
