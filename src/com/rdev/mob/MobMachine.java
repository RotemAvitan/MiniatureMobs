package com.rdev.mob;

import com.rdev.MiniatureMobs;
import com.rdev.entityai.MobsBase;
import com.rdev.utils.EntityUtil;
import com.rdev.utils.MathUtils;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

/**
 * A mob machine. Holding information about the mob machine such as name, parts, hasSpawned and etc.
 * You can add parts to the mob machine here as well.
 * @see Part
 */
public class MobMachine {

    @Getter private String name;
    @Getter private MobsBase baseMob;
    @Getter private ArmorStand nametag;
    @Getter private boolean spawned;
    @Getter @Setter private Map<Part, Vector> parts = new HashMap<>();
    private final double mobHeight = -1.5;

    public MobMachine(String name, MobsBase baseMob) {
        this.name = name;
        this.baseMob = baseMob;
    }

    /**
     * Adding part to the mob machine using offset.
     *
     * @param part The part that will be added.
     * @param offset The location of the part relevant to the Zombie base.
     */
    public void addPart(Part part, Vector offset) {
        this.parts.put(part, offset.add(new Vector(0, mobHeight,0)));
    }

    /**
     * Spawning the mob machine with all the parts.
     * Calculate parts spinning via entity's yaw.
     *
     * @param spawnLocation The spawn location of the mob machine.
     * @return The entity of the mob machine.
     */
    public LivingEntity spawn(Location spawnLocation) {
        LivingEntity mob = (LivingEntity) getBaseMob().spawnEntity(spawnLocation);

        this.nametag = EntityUtil.spawnCustomArmorStand(mob.getEyeLocation().clone().add(0,-1.75,0), false, this.name);

        parts.keySet().forEach(p -> p.spawnPart(mob.getLocation().clone().add(parts.get(p))));

        new BukkitRunnable() {
            @Override
            public void run() {
                if ((!mob.isValid()) || mob.isDead() || mob == null) {
                    MiniatureMobs.getInstance().getMobsManager().remove(MobMachine.this);
                    cancel();
                }

                Location loc = mob.getEyeLocation().subtract(0.0D, 0.3D, 0.0D);
                loc.setPitch(0.0F);
                loc.setYaw(mob.getEyeLocation().getYaw());
                Vector v1 = loc.getDirection().normalize().multiply(-0.09D);
                v1.setY(0);
                loc.add(v1);

                parts.keySet().forEach((mp)-> {
                    Vector offset = parts.get(mp);

                    //rotate parts around the mob while mob is rotating.
                    Vector v = new Vector(offset.getX(), 0, -offset.getZ());
                    v = MathUtils.rotateAroundAxisX(v, 2.0071287155151367D);
                    v = MathUtils.rotateAroundAxisY(v, -loc.getYaw() * (Math.PI/180));
                    v.setY(offset.getY());
                    loc.add(v);

                    mp.getArmorstand().teleport(loc);

                    loc.subtract(v);
            });
                nametag.teleport(mob.getEyeLocation().add(0,-1.75,0));
            }
        }.runTaskTimer(MiniatureMobs.getInstance(), 2, 2);

        this.spawned = true;

        return mob;
    }
}