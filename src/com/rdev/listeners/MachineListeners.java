package com.rdev.listeners;

import com.rdev.MiniatureMobs;
import com.rdev.mob.MobMachine;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MachineListeners implements Listener {

    @EventHandler
    public void MachineDeath(EntityDeathEvent e) {
        if(!(e.getEntity() instanceof LivingEntity)) return;
        MobMachine mobMachine = MiniatureMobs.getInstance().getMobsManager().getMachineByEntity(e.getEntity());
        MiniatureMobs.getInstance().getMobsManager().remove(mobMachine);
    }
}
