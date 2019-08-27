package com.rdev.configuration;

import com.rdev.mob.Part;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class MiniatureMobConfiguration {

    @Getter private String nameID;
    @Getter private List<Part> parts;
    @Getter @Setter boolean small;
    @Getter @Setter private int health;
    @Getter @Setter private String displayName;
    @Getter @Setter private int damage;

    MiniatureMobConfiguration(String nameID) {
        this.nameID = nameID;
        this.parts = new ArrayList<>();
    }

    void addPart(Part part) {
        this.parts.add(part);
    }

}

