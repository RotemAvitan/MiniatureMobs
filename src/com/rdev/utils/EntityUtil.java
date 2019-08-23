package com.rdev.utils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public final class EntityUtil {

    public static ArmorStand spawnCustomArmorStand(Location location, boolean small, String customname) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(small);
        armorStand.setInvulnerable(true);
        armorStand.setCustomNameVisible(false);

        if(customname != null) {
            armorStand.setCustomName(customname);
            armorStand.setCustomNameVisible(true);
        }

        return armorStand;
    }

    public static ArmorStand spawnCustomArmorStand(Location location, boolean small) {
        return spawnCustomArmorStand(location, small, null);
    }

    public static int getHeadDistanceFromGround(Entity e){
        Location loc = e.getLocation().clone();
        int distance = 0;
        while (!loc.getBlock().getType().isSolid()) {
            loc.subtract(0, 1, 0);
            distance++;
        }
        return distance;
    }

}
