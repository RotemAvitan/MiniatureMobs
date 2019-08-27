package com.rdev.entityai;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class MobsBase {

    @Getter public Entity entity;

    public abstract EntityType getEntityType();

    public Entity spawnEntity(Location location) {
        Entity mob = location.getWorld().spawnEntity(location, getEntityType());

        if (mob instanceof LivingEntity) ((LivingEntity)mob).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));

        mob.setSilent(true);
        if (mob instanceof Ageable){
            ((Ageable)mob).setAdult();
        }
        this.entity = mob;
        return mob;
    }


}
