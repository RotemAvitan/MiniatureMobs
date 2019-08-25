package com.rdev.configuration;

import com.rdev.MiniatureMobs;
import com.rdev.mob.Part;
import com.rdev.mob.PartType;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.util.EulerAngle;

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

    public void loadMobsFromConfigurationFile() {

        getConfigFile().getConfigurationSection("").getKeys(false).forEach( key -> {
            MiniatureMobConfiguration mobConfiguration = createMobConfiguration(key);
            configurationMobs.add(mobConfiguration);
        });

    }

    public MiniatureMobConfiguration createMobConfiguration(String configurationDir) {
        FileConfiguration file = getConfigFile();

        if(!file.contains(configurationDir)) {
            return null;
        }

        MiniatureMobConfiguration mobConfiguration = new MiniatureMobConfiguration(configurationDir);

        final String DIR = configurationDir +  ".";

        EntityType entityType = EntityType.valueOf(file.getString(DIR + "MobType"));
        mobConfiguration.setMobType(entityType == null ? entityType : EntityType.ZOMBIE);

        mobConfiguration.setHealth(file.getInt(DIR + "Health"));
        mobConfiguration.setDisplayName(file.getString(DIR + "Display"));
        mobConfiguration.setDamage(file.getInt(DIR + "Damage"));

        if(file.contains(DIR + "BodyParts")) {
            final String BODY_PARTS = "BodyParts.";
            Set<String> partsSections = file.getConfigurationSection(DIR + "BodyParts").getKeys(false);

            partsSections.forEach(partsSection -> {
                Part part = new Part();
                //PartType partType, Material material, boolean small
                PartType partType = PartType.valueOf(file.getString(DIR + BODY_PARTS + partsSection + "." + "PartType"));
                part.setPartType(partType == null ? partType : PartType.HEAD);

                Material material = Material.valueOf(file.getString(DIR + BODY_PARTS + partsSection + "." + "Material"));
                if(material == null) return;
                part.setMaterial(material);

                part.setSmall(file.getBoolean(DIR + BODY_PARTS + partsSection + "." + "Small"));

                final String PART_POSE = DIR + BODY_PARTS + partsSection + "." + "PartPose";
                if(file.contains(PART_POSE)) {

                    EulerAngle poseAngle = new EulerAngle(file.getDouble(PART_POSE + ".x"), file.getDouble(PART_POSE + ".y"), file.getDouble(PART_POSE + ".z"));

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
