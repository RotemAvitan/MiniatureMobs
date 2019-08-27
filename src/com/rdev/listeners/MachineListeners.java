package com.rdev.listeners;

import com.rdev.MiniatureMobs;
import com.rdev.mob.MobMachine;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/**
 * A listener responsible for the mob machine.
 */
public class MachineListeners implements Listener {

    /**
     * Removing mob machine from Lists.
     */
    @EventHandler
    public void MachineDeath(EntityDeathEvent e) {
        MobMachine mobMachine = MiniatureMobs.getInstance().getMobsManager().getMachineByEntity(e.getEntity());
        if (mobMachine == null) return;
        MiniatureMobs.getInstance().getMobsManager().remove(mobMachine);
    }
}
