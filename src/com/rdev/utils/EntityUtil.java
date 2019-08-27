package com.rdev.utils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

/**
 * Class holding utilities methods about entities.
 */
public final class EntityUtil {

    public static ArmorStand spawnCustomArmorStand(Location location, boolean small, String customName) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false);
        armorStand.setCollidable(false);
        armorStand.setBasePlate(false);
        armorStand.setGravity(false);
        armorStand.setSmall(small);
        armorStand.setInvulnerable(true);
        armorStand.setCustomNameVisible(false);

        if (customName != null) {
            armorStand.setCustomName(customName);
            armorStand.setCustomNameVisible(true);
        }

        return armorStand;
    }

    public static ArmorStand spawnCustomArmorStand(Location location, boolean small) {
        return spawnCustomArmorStand(location, small, null);
    }

}
