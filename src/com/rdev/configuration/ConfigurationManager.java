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

public class ConfigurationManager {

    @Getter private final List<MiniatureMobConfiguration> configurationMobs = new ArrayList<>();

    private FileConfiguration getConfigFile() {
        File file = new File(MiniatureMobs.getInstance().getDataFolder(), "mobs.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public MiniatureMobConfiguration getMobConfigurationByID(String nameID) {
        return configurationMobs.stream().filter(mb -> mb.getNameID().equals(nameID)).findAny().orElse(null);
    }

    public void registerConfigMobs() {
        ConfigurationSection cs = getConfigFile().getConfigurationSection("");
        if (cs == null) {
            return;
        }

        cs.getKeys(false).forEach(key -> configurationMobs.add(createMobConfiguration(key)));
    }

    public Vector getOffsetFromConfiguration(String nameID) {
        String dirOffset = nameID + ".BodyParts.Offset";
        ConfigurationSection cs = getConfigFile().getConfigurationSection(dirOffset);

        return cs == null ? new Vector(0,0,0) : new Vector(cs.getDouble("x"), cs.getDouble("y"), cs.getDouble("z"));
}

    private MiniatureMobConfiguration createMobConfiguration(String configurationDir) {
        FileConfiguration file = getConfigFile();

        if (!file.contains(configurationDir)) {
            return null;
        }

        MiniatureMobConfiguration mobConfiguration = new MiniatureMobConfiguration(configurationDir);

        String dir = configurationDir +  ".";

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
