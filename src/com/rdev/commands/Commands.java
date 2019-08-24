package com.rdev.commands;

import com.rdev.MiniatureMobs;
import com.rdev.entityai.ZombieMobBaseEntity;
import com.rdev.mob.MobMachine;
import com.rdev.mob.MobsManager;
import com.rdev.mob.Part;
import com.rdev.mob.PartType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

final class Commands {

    static void testCommand(Location location) {

        MobMachine mobMachine = MiniatureMobs.getInstance().getMobsManager().buildMiniatureMobs("Test", new ZombieMobBaseEntity());

        mobMachine.addPart(new Part(PartType.HEAD, Material.PUMPKIN, false), new Vector(0.0, -0.10883, 0.0));
        mobMachine.addPart(new Part(PartType.HEAD, Material.SNOW_BLOCK, false), new Vector(0.0, -1.35883, 0.0));
        mobMachine.addPart(new Part(PartType.HEAD, Material.SNOW_BLOCK, false), new Vector(0.0, -0.73383, 0.0));

        mobMachine.addPart(new Part(PartType.HEAD, Material.STICK, false, new EulerAngle(0f,0,0f)), new Vector(-0.5, -1.20258, 0.273438));

        mobMachine.addPart(new Part(PartType.HEAD, Material.STICK, false, new EulerAngle(180f,0f,0)), new Vector(0.5, 0.23492, -0.273438));

        mobMachine.spawn(location);
    }

    static void checkList() {
        Bukkit.broadcastMessage(MiniatureMobs.getInstance().getMobsManager().getMobs().toString());
    }

}
