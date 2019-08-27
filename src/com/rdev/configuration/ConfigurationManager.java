package com.rdev.configuration;

import com.google.common.base.Enums;
import com.rdev.MiniatureMobs;
import com.rdev.mob.Part;
import com.rdev.mob.PartType;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The configuration manager, manage all the loading system of the mobs from the config.
 * Contains methods of getConfig, registerConfigMobs and createMobConfiguration.
 */
public class ConfigurationManager {

    @Getter private final List<MiniatureMobConfiguration> configurationMobs = new ArrayList<>();

    /**
     * Get the configuration file of the miniature mobs.
     *
     * @return The configuration file.
     */
    private FileConfiguration getConfigFile() {
        File file = new File(MiniatureMobs.getInstance().getDataFolder(), "mobs.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Get the mob configuration by the name ID.
     *
     * @param nameID The name ID of the mob, from the configuration section.
     * @return The miniature mob configuration object.
     */
    public MiniatureMobConfiguration getMobConfigurationByID(String nameID) {
        return configurationMobs.stream().filter(mb -> mb.getNameID().equals(nameID)).findAny().orElse(null);
    }

    /**
     * Register all the mobs from the configuration file.
     */
    public void registerConfigMobs() {
        ConfigurationSection cs = getConfigFile().getConfigurationSection("");
        if (cs == null) {
            return;
        }

        cs.getKeys(false).forEach(key -> configurationMobs.add(createMobConfiguration(key)));
    }

    /**
     * Get the offset data from the configuration file.
     *
     * @param nameID The name ID of the mob, from the configuration section.
     * @return Vector that presents x, y and z of the {@link Part} offset.
     */
    public Vector getOffsetFromConfiguration(String nameID) {
        String dirOffset = nameID + ".BodyParts.Offset";
        ConfigurationSection cs = getConfigFile().getConfigurationSection(dirOffset);

        return cs == null ? new Vector(0, 0, 0) : new Vector(cs.getDouble("x"), cs.getDouble("y"), cs.getDouble("z"));
    }

    /**
     * Creating mob configuration object that used to summon a custom miniature mob.
     *
     * @param nameID The string ID of the miniature mob from the configuration file.
     * @return The configuration object.
     */
    private MiniatureMobConfiguration createMobConfiguration(String nameID) {
        FileConfiguration file = getConfigFile();

        if (!file.contains(nameID)) {
            return null;
        }

        MiniatureMobConfiguration mobConfiguration = new MiniatureMobConfiguration(nameID);

        String dir = nameID +  ".";

        mobConfiguration.setHealth(file.getInt(dir + "Health"));
        mobConfiguration.setDisplayName(file.getString(dir + "Display"));
        mobConfiguration.setDamage(file.getInt(dir + "Damage"));

        ConfigurationSection bodyPartsSection = file.getConfigurationSection(dir + "BodyParts");
        if (bodyPartsSection != null) {
            String bodyParts = "BodyParts.";

            Set<String> partsSections = bodyPartsSection.getKeys(false);

            partsSections.forEach(partsSection -> {
                Part part = new Part();
                //PartType partType, Material material, boolean small

                PartType partType;
                if (file.contains(dir + bodyParts + partsSection + "." + "PartType"))
                partType = Enums.getIfPresent(PartType.class, file.getString(dir + bodyParts + partsSection + "." + "PartType")).orNull();
                else partType = PartType.HEAD;

                part.setPartType(partType == null ? partType : PartType.HEAD);

                Material material;

                material = Enums.getIfPresent(Material.class, file.getString(dir + bodyParts + partsSection + "." + "Material")).orNull();
                if (material == null) {
                    return;
                }
                part.setMaterial(material);

                part.setSmall(file.getBoolean(dir + bodyParts + partsSection + "." + "Small"));

                String partPose = dir + bodyParts + partsSection + "." + "PartPose";
                if (file.contains(partPose)) {

                    EulerAngle poseAngle = new EulerAngle(file.getDouble(partPose + ".x"), file.getDouble(partPose + ".y"), file.getDouble(partPose + ".z"));

                    switch (partType) {
                        case HEAD:
                            part.setHeadPose(poseAngle);
                            break;
                        case RIGHT_HAND:
                            part.setRightHandPose(poseAngle);
                            break;
                        case LEFT_HAND:
                            part.setLeftHandPose(poseAngle);
                            break;
                    }
                }
                mobConfiguration.addPart(part);
            });
        }
        return mobConfiguration;
    }
}
