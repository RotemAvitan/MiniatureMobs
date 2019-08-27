package com.rdev.mob;

import com.rdev.utils.EntityUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

/**
 * Part. Holding information about the mob's parts such as {@link PartType}, size, armor stand's parts poses, armor stand and etc.
 * You can add parts to the mob machine here as well.
 */
public class Part {

    @Getter private ArmorStand armorstand;
    @Getter @Setter private PartType partType;
    @Getter @Setter private Material material;
    @Getter @Setter private boolean small;
    @Getter @Setter private boolean spawned;
    @Getter @Setter private EulerAngle headPose;
    @Getter @Setter private EulerAngle rightHandPose;
    @Getter @Setter private EulerAngle leftHandPose;

    /**
     * Creating a part without any information on it.
     */
    public Part() { }

    /**
     * Creating part with default pose.
     *
     * @param partType The part type, contains the item position.
     * @param material The material of the part.
     * @param small The size of the part.
     */
    public Part(PartType partType, Material material, boolean small) {
        this(partType, material, small, null, null, null);
    }

    /**
     * Creating part with custom head pose.
     *
     * @param partType The part type, contains the item position.
     * @param material The material of the part.
     * @param small The size of the part.
     * @param headPose The position of the head.
     */
    public Part(PartType partType, Material material, boolean small, EulerAngle headPose) {
        this(partType, material, small, headPose, null, null);
    }

    /**
     * Creating part with custom head and right hand pose.
     *
     * @param partType The part type, contains the item position.
     * @param material The material of the part.
     * @param small The size of the part.
     * @param headPose The position of the head.
     * @param rightHandPose The position of the right hand.
     */
    public Part(PartType partType, Material material, boolean small, EulerAngle headPose, EulerAngle rightHandPose) {
        this(partType, material, small, headPose, rightHandPose, null);
    }

    /**
     * Creating part with custom head, right hand and left hand pose.
     *
     * @param partType The part type, contains the item position.
     * @param material The material of the part.
     * @param small The size of the part.
     * @param headPose The position of the head.
     * @param rightHandPose The position of the right hand.
     * @param leftHandPose The position of the left hand.
     */
    public Part(PartType partType, Material material, boolean small, EulerAngle headPose, EulerAngle rightHandPose, EulerAngle leftHandPose) {
        this.material = material;
        this.small = small;
        this.partType = partType;
        this.headPose = headPose;
        this.rightHandPose = rightHandPose;
        this.leftHandPose = leftHandPose;
    }

    /**
     * Spawning the part in the specified location
     *
     * @param spawnLocation The spawn location of the part.
     * @return If the part has spawned.
     */
    public boolean spawnPart(Location spawnLocation) {
        if (armorstand != null) return false;
        this.armorstand = EntityUtil.spawnCustomArmorStand(spawnLocation.clone(), small);

        switch(partType) {
            case HEAD:
                armorstand.setHelmet(new ItemStack(material));
                if (headPose != null) armorstand.setHeadPose(headPose);
                break;
            case LEFT_HAND:
                armorstand.getEquipment().setItemInOffHand(new ItemStack(material));
                if (leftHandPose != null) armorstand.setLeftArmPose(leftHandPose);
                break;
            case RIGHT_HAND:
                armorstand.getEquipment().setItemInMainHand(new ItemStack(material));
                if (rightHandPose != null) armorstand.setRightArmPose(rightHandPose);
                break;
        }

        return true;
    }
}
