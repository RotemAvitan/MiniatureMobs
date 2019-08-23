package com.rdev.mob;

import com.rdev.utils.EntityUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class Part {

    @Getter private ArmorStand armorstand;
    @Getter private PartType partType;
    @Getter @Setter private Material material;
    @Getter @Setter private boolean small;
    @Getter @Setter private boolean spawned;
    @Getter @Setter private EulerAngle headPose;
    @Getter @Setter private EulerAngle rightHandPose;
    @Getter @Setter private EulerAngle leftHandPose;

    public Part(PartType partType, Material material, boolean small) {
        this(partType, material, small, null, null, null);
    }

    public Part(PartType partType, Material material, boolean small, EulerAngle headPose) {
        this(partType, material, small, headPose, null, null);
    }
    public Part(PartType partType, Material material, boolean small, EulerAngle headPose, EulerAngle rightHandPose) {
        this(partType, material, small, headPose, rightHandPose, null);
    }

    public Part(PartType partType, Material material, boolean small, EulerAngle headPose, EulerAngle rightHandPose, EulerAngle leftHandPose) {
        this.material = material;
        this.small = small;
        this.partType = partType;
        this.headPose = headPose;
        this.rightHandPose = rightHandPose;
        this.leftHandPose = leftHandPose;
    }

    public boolean spawnPart(Location spawnLocation) {
        if(armorstand != null) return true;
        this.armorstand = EntityUtil.spawnCustomArmorStand(spawnLocation, small);

        switch(partType) {
            case HEAD:
                armorstand.setHelmet(new ItemStack(material));
                if(headPose != null) armorstand.setHeadPose(headPose);
                break;
            case LEFT_HAND:
                armorstand.getEquipment().setItemInOffHand(new ItemStack(material));
                if(leftHandPose != null) armorstand.setLeftArmPose(leftHandPose);
                break;
            case RIGHT_HAND:
                armorstand.getEquipment().setItemInMainHand(new ItemStack(material));
                if(rightHandPose != null) armorstand.setRightArmPose(rightHandPose);
                break;
        }

        return false;
    }
}
