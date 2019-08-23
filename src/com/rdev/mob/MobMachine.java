package com.rdev.mob;

import com.rdev.MiniatureMobs;
import com.rdev.entityai.MobsBase;
import com.rdev.utils.EntityUtil;
import com.rdev.utils.MathUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class MobMachine {

    @Getter private String name;
    @Getter private MobsBase baseMob;
    @Getter private ArmorStand nametag;
    @Getter @Setter private Map<Part, Vector> parts = new HashMap<>();

    public MobMachine(String name, MobsBase baseMob) {
        this.name = name;
        this.baseMob = baseMob;
    }

    public void addPart(Part part, Vector offset) {
        this.parts.put(part, offset.add(new Vector(0,0,0)));
    }

    public LivingEntity spawn(Location spawnLocation) {
        LivingEntity mob = (LivingEntity) getBaseMob().spawnEntity(spawnLocation);

        this.nametag = EntityUtil.spawnCustomArmorStand(mob.getEyeLocation().add(0,-1.75,0), false, this.name);

        parts.keySet().forEach(p -> p.spawnPart(mob.getLocation().clone().add(parts.get(p))));

        new BukkitRunnable() {
            @Override
            public void run() {
                if( (!mob.isValid()) || mob.isDead() || mob == null) {
                    nametag.remove();
                    parts.keySet().forEach(
                            p -> {
                                p.getArmorstand().getPassengers().forEach((passenger) -> passenger.remove());
                                p.getArmorstand().remove();
                            });
                    cancel();
                }
                parts.keySet().forEach((p)-> {
                    p.getArmorstand().teleport(mob.getLocation().clone().add(parts.get(p)));
                });
                nametag.teleport(mob.getEyeLocation().add(0,-1.75,0));
            }
        }.runTaskTimer(MiniatureMobs.getInstance(), 1, 1);

        return mob;
    }
}