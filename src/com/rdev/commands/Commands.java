package com.rdev.commands;

import com.rdev.MiniatureMobs;
import com.rdev.configuration.MiniatureMobConfiguration;
import com.rdev.consts.Constants;
import com.rdev.entityai.ZombieMobBaseEntity;
import com.rdev.mob.MobMachine;
import com.rdev.mob.Part;
import com.rdev.mob.PartType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

/**
 * A class holding static functions in order to implement the commands from {@link MainCommand}.
 */
final class Commands {

    /**
     * Command to spawn a mob from the configuration file settings.
     * This command using the nameID of the mob.
     *  @param nameID The name ID of the miniature mob.
     * @param spawnLocation The spawn location of the mob.
     * @param commandSender The command sender of this command.
     * @return Boolean value if the mob has spawned
     */
    static boolean spawnMob(String nameID, int amount, Location spawnLocation, CommandSender commandSender) {
        MiniatureMobConfiguration mobConfiguration = MiniatureMobs.getInstance().getConfigurationManager().getMobConfigurationByID(nameID);

        if (mobConfiguration == null) {
            commandSender.sendMessage(Constants.Command.MOB_CONFIGUCATION_NOT_FOUND.replace("%mob%", nameID));
            return false;
        }

        Player p = (Player) commandSender;
        MobMachine mobMachine = MiniatureMobs.getInstance().getMobsManager().buildMiniatureMob(mobConfiguration);
        mobMachine.spawn(p.getLocation());
        commandSender.sendMessage(Constants.Command.SPAWN_MOB_SUCCESS.replace("%mob%", nameID));

        return true;
    }

    //just a dev test command
    static void testCommand(Location location) {

        MobMachine mobMachine = MiniatureMobs.getInstance().getMobsManager().buildMiniatureMob("Test", new ZombieMobBaseEntity());

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

    //memory leak testing
    static void checkList() {
        Bukkit.broadcastMessage(MiniatureMobs.getInstance().getMobsManager().getMobs().toString());
    }

}
