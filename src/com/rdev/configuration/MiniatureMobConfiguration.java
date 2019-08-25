package com.rdev.configuration;

import com.rdev.mob.Part;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.EntityType;

import java.util.List;

public class MiniatureMobConfiguration {

    @Getter private String nameID;
    @Getter private List<Part> parts;
    @Getter @Setter boolean small;
    @Getter @Setter private int health;
    @Getter @Setter private String displayName;
    @Getter @Setter private int damage;

    public MiniatureMobConfiguration(String nameID) {
        this.nameID = nameID;
    }

    public void addPart(Part part) {
        parts.add(part);
    }

}

