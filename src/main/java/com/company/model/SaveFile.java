package com.company.model;

import java.util.List;
import java.io.File;
import java.io.FileWriter;

public class SaveFile {
    static String planetSavePath = "CSV-files/solar_system_planets";
    static String planetSaveExtension = ".csv";
    static String planetCSVDevider = ",";

    public static void saveFile_V1(List<PlanetSystem> planetSystems) {
        planetSaveExtension = ".csv";
        try (FileWriter fileWriter = new FileWriter(new File(planetSavePath + "_UserSaved" + planetSaveExtension))){
            fileWriter.write("name,mass,radius,semiMajorAxis,eccentricity,orbitalPeriod,centralCelestialBody,type,effectiveTemp");  // Adds the headers

            for (PlanetSystem planetSystem : planetSystems) {
                fileWriter.write("\n");
                fileWriter.write(planetSystem.getCenterStar().getName() + planetCSVDevider +
                    planetSystem.getCenterStar().getMass() + planetCSVDevider +
                    planetSystem.getCenterStar().getRadius() + planetCSVDevider +
                    planetCSVDevider + planetCSVDevider + planetCSVDevider + planetCSVDevider +
                    "sun" + planetCSVDevider +
                    planetSystem.getCenterStar().getEffectiveTemp());

                for (Planet planet : planetSystem.getPlanetList()) {
                    fileWriter.write("\n");
                    fileWriter.write(planet.getName() + planetCSVDevider +
                        planet.getMass() + planetCSVDevider +
                        planet.getRadius() + planetCSVDevider +
                        planet.getSemiMajorAxis() + planetCSVDevider +
                        planet.getEccentricity() + planetCSVDevider +
                        planet.getOrbitalPeriod() + planetCSVDevider +
                        planet.getCentralCelestialBody().getName() + planetCSVDevider +
                        "planet");

                    for (Moon moon : planet.moonList()) {
                        fileWriter.write("\n");
                        fileWriter.write(moon.getName() + planetCSVDevider +
                            moon.getMass() + planetCSVDevider +
                            moon.getRadius() + planetCSVDevider +
                            moon.getSemiMajorAxis() + planetCSVDevider +
                            moon.getEccentricity() + planetCSVDevider +
                            moon.getOrbitalPeriod() + planetCSVDevider +
                            moon.getCentralCelestialBody().getName() + planetCSVDevider +
                            "moon");
                    }
                }
            }
            fileWriter.flush();   // Remoes the buffer of characters
        } catch (Exception ignored) {
        }
    }
}