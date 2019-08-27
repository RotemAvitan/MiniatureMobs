package com.rdev.configuration;

import com.rdev.mob.Part;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A miniature mob configuration. Holding information about mob configuration.
 * Used to load mobs from the configuration files.
 */
public class MiniatureMobConfiguration {

    @Getter private String nameID;
    @Getter private List<Part> parts;
    @Getter @Setter boolean small;
    @Getter @Setter private int health;
    @Getter @Setter private String displayName;
    @Getter @Setter private int damage;

    /**
     * Create miniature mob configuration.
     *
     * @param nameID The string ID of the mob, the section name of each miniature mob.
     */
    MiniatureMobConfiguration(String nameID) {
        this.nameID = nameID;
        this.parts = new ArrayList<>();
    }

    /**
     * Adding part to the parts List.
     *
     * @param part The part (armor stand)
     */
    void addPart(Part part) {
        this.parts.add(part);
    }

}

