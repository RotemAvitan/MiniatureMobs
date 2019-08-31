package com.rdev.mob;

import com.rdev.MiniatureMobs;
import com.rdev.configuration.MiniatureMobConfiguration;
import com.rdev.entityai.MobsBase;
import com.rdev.entityai.ZombieMobBaseEntity;
import lombok.Getter;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * The mobs manager, manage all the miniature mobs, create and remove {@link MiniatureMobs}.
 */
public class MobsManager {

    @Getter private final List<MobMachine> mobs = new ArrayList<>();

    /**
     * Creating a miniature mob.
     *
     * @param name the name of the mob that will be displayed on the name tag.
     * @param baseMob the base of the mob, entity.
     * @return The Mob Machine.
     */
    public MobMachine buildMiniatureMob(String name, MobsBase baseMob) {
        MobMachine mobMachine = new MobMachine(name, baseMob);
        mobs.add(mobMachine);
        return mobMachine;
    }

    /**
     * Creating a miniature mob.
     *
     * @param mobConfiguration The mob configuration from the file.
     * @return The Mob Machine.
     */
    public MobMachine buildMiniatureMob(MiniatureMobConfiguration mobConfiguration) {
        MobsBase mobsBase = new ZombieMobBaseEntity();
        MobMachine mobMachine = new MobMachine(mobConfiguration.getNameID() + "", mobsBase);
        mobConfiguration.getParts().forEach(part ->
                mobMachine.addPart(new Part(part), MiniatureMobs.getInstance().getConfigurationManager().getOffsetFromConfiguration(mobConfiguration.getNameID())));

        mobMachine.setDamage(mobConfiguration.getDamage());
        mobMachine.setHealth(mobConfiguration.getHealth());

        mobs.add(mobMachine);
        return mobMachine;
    }

    /**
     * Removing all the miniature mobs from the world.
     */
    public void removeAll() {
        //Created in order to avoid ConcurrentModificationException.
        List<MobMachine> cloneList = new ArrayList<>();
        cloneList.addAll(mobs);
        cloneList.forEach(this::remove);
    }

    /**
     * Removing a specific miniature mob.
     * @param mobMachine The miniature mob.
     */
    public void remove(MobMachine mobMachine) {
        if (!(mobMachine.getBaseMob().getEntity() == null || mobMachine.getBaseMob().getEntity().isDead()))
            mobMachine.getBaseMob().getEntity().remove();

        mobMachine.getParts().keySet().forEach(part -> part.getArmorstand().remove());
        if (mobMachine.getNameTag() != null) mobMachine.getNameTag().remove();
        mobs.remove(mobMachine);
    }

    /**
     * Get {@link MobMachine} by {@link LivingEntity}.
     *
     * @param matchMob
     * @return
     */
    public MobMachine getMachineByEntity(LivingEntity matchMob) {
        return mobs.stream().filter(mb -> mb.getBaseMob().getEntity().equals(matchMob)).findFirst().orElse(null);
    }

}
