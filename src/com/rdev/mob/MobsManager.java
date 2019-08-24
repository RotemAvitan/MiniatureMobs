package com.rdev.mob;

import com.rdev.entityai.MobsBase;
import com.rdev.entityai.ZombieMobBaseEntity;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class MobsManager {

    @Getter private final List<MobMachine> mobs = new ArrayList<>();

    public MobMachine buildMiniatureMobs(String name, MobsBase baseMob) {
        MobMachine mobMachine = new MobMachine(name, baseMob);
        mobs.add(mobMachine);
        return mobMachine;
    }

    public void removeAll() {
        mobs.forEach(mobMachine -> {
            if(mobMachine.isSpawned())
                mobMachine.getBaseMob().getEntity().remove();
            mobs.remove(mobMachine);
        });
    }

    public void remove(MobMachine mobMachine) {
        if(!(mobMachine.getBaseMob().getEntity() == null || mobMachine.getBaseMob().getEntity().isDead()))
            mobMachine.getBaseMob().getEntity().remove();

        mobs.remove(mobMachine);
    }

    public MobMachine getMachineByEntity(LivingEntity matchMob) {
        for(MobMachine mb : mobs)
            if(mb.getBaseMob().getEntity().equals(matchMob)) return mb;
        return null;
    }
}
