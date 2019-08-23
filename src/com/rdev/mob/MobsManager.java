package com.rdev.mob;

import com.rdev.entityai.ZombieMobBaseEntity;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MobsManager {

    @Getter private final List<MobMachine> mobs = new ArrayList<>();

    public void buildMiniatureMobs(Location location, MobMachine mobMachine) {
    }
}
