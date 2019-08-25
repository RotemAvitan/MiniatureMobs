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

       /* mobMachine.addPart(new Part(PartType.HEAD, Material.PUMPKIN, false), new Vector(0.0, -0.10883, 0.0));
        mobMachine.addPart(new Part(PartType.HEAD, Material.SNOW_BLOCK, false), new Vector(0.0, -1.35883, 0.0));
        mobMachine.addPart(new Part(PartType.HEAD, Material.SNOW_BLOCK, false), new Vector(0.0, -0.73383, 0.0));

        mobMachine.addPart(new Part(PartType.HEAD, Material.STICK, false, new EulerAngle(0f,0,0f)), new Vector(-0.5, -1.20258, 0.273438));

        mobMachine.addPart(new Part(PartType.HEAD, Material.STICK, false, new EulerAngle(180f,0f,0)), new Vector(0.5, 0.23492, -0.273438));*/

        mobMachine.addPart(new Part(PartType.HEAD, Material.WHITE_TERRACOTTA, false), new Vector(0.34375, 1.01617, 0.0));

        mobMachine.addPart(new Part(PartType.HEAD, Material.WHITE_TERRACOTTA, true, new EulerAngle(0f,0f,5f)), new Vector(0.328498, 1.587736, 0.0));
        mobMachine.addPart(new Part(PartType.HEAD, Material.WHITE_TERRACOTTA, true, new EulerAngle(0f,0f,25f)), new Vector(0.551042, 0.897216, 0.9));
        mobMachine.addPart(new Part(PartType.HEAD, Material.WHITE_TERRACOTTA, true, new EulerAngle(0f,0f,35f)), new Vector(0.524624, 0.897216, -0.93125));

        mobMachine.addPart(new Part(PartType.HEAD, Material.IRON_BLOCK, true), new Vector(0.34375, 1.09957, 0.31875));
        mobMachine.addPart(new Part(PartType.HEAD, Material.IRON_BLOCK, true), new Vector(0.34375, 1.09957, -0.31875));
        mobMachine.addPart(new Part(PartType.HEAD, Material.IRON_BLOCK, true), new Vector(0.34375, 0.66207, 0.0));

        mobMachine.addPart(new Part(PartType.HEAD, Material.BLUE_WOOL, true, new EulerAngle(-10f,0f,-25f)), new Vector(0.510335, 0.543376, 0.280388));
        mobMachine.addPart(new Part(PartType.HEAD, Material.BLUE_WOOL, true, new EulerAngle(15f,0f,-25f)), new Vector(0.477688, 0.54637, -0.726543));

        mobMachine.addPart(new Part(PartType.HEAD, Material.COAL_BLOCK, true), new Vector(0.4375, -0.18168, 0.4));
        mobMachine.addPart(new Part(PartType.HEAD, Material.COAL_BLOCK, true), new Vector(0.4375, -0.21293, -0.48125));


        mobMachine.spawn(location);
    }

    static void checkList() {
        Bukkit.broadcastMessage(MiniatureMobs.getInstance().getMobsManager().getMobs().toString());
    }

}
