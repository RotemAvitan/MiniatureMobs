package com.rdev.entityai;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class ZombieMobBaseEntity extends MobsBase {

    @Override
    public EntityType getEntityType() {
        return EntityType.ZOMBIE;
    }

}
