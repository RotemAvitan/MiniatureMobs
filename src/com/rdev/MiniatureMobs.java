package com.rdev;

import com.rdev.commands.MainCommand;
import com.rdev.mob.MobsManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class MiniatureMobs extends JavaPlugin {

    @Getter private static MiniatureMobs instance;
    @Getter public MobsManager mobsManager;

    @Override
    public void onEnable() {
        instance = this;

        this.loadMobsManager();

        getCommand("miniaturemobs").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {

    }

    private void loadMobsManager() {
        this.mobsManager = new MobsManager();
    }

}
