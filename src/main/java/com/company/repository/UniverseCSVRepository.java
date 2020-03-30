package com.company.repository;

import com.company.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UniverseCSVRepository {
    public HashMap<Integer, PlanetSystem> planetSystems = new HashMap<>();

    public UniverseCSVRepository(String fileName) {
        // Read CSV file here

        if (!fileName.matches(".+\\.csv"))  //Checks if there is .csv in the file name. if not then add.
            fileName += ".csv";
        fileName = "CSV-files/" + fileName;

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            String[] prop = fileScanner.next().split(",");
            String[] splitString;
            PlanetSystem workingSystem;

            while (fileScanner.hasNextLine()) {
                splitString = fileScanner.next().split(",");
                if ( !planetSystemExist(splitString[0])) {   //Creates a new planet system if there is no already
                    PlanetSystem planetSystem = new PlanetSystem(splitString[0]);
                    planetSystems.put(planetSystems.size() + 1, planetSystem);
                    workingSystem = planetSystem;
                } else {
                    workingSystem = getPlanetSystem(splitString[0]);
                }

                if (!workingSystem.bodyExists(splitString[2], PlanetSystem.Body.STAR)) {    //Creates a new star in the system if there is no already
                    CenterStar centerStar = new CenterStar();
                    for (int i = 0; i < prop.length; i++) {
                        centerStar.setSunPropByName(prop[i], splitString[i], workingSystem);
                    }
                    workingSystem.setCenterStar(centerStar);
                } else {
                    for (int i = 0; i < prop.length; i++) {
                        workingSystem.getCenterStar().setSunPropByName(prop[i], splitString[i], workingSystem);
                    }
                }

                if (!workingSystem.bodyExists(splitString[7], PlanetSystem.Body.PLANET)) {  //Creates a new planet if the planets does not exist
                    Planet planet = new Planet();
                    for (int i = 0; i < prop.length; i++) {
                        planet.setBodyPropByName(prop[i], splitString[i], workingSystem);
                    }
                    workingSystem.addPlanetToSystem(planet);
                } else {
                    for (int i = 0; i < prop.length; i++) {
                        workingSystem.getPlanet(splitString[7]).setBodyPropByName(prop[i], splitString[i], workingSystem);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(planetSystems);
    }

    public void inportCSVAgain(String fileName) {

    }

    public ArrayList getAllPlanetsInSystem (String systemName) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanetArray();

        return new ArrayList<>();
    }

    public boolean planetSystemExist(String planetSystemName) {
        for (int i: planetSystems.keySet()) {
            if (planetSystems.get(i).equals(planetSystemName))
                return true;
        }
        return false;
    }

    public PlanetSystem getPlanetSystem(String planetSystemName) {
        for (int i : planetSystems.keySet()) {
            if (planetSystems.get(i).equals(planetSystemName)) {
                return planetSystems.get(i);
            }
        }

        return null;
    }
    public HashMap<Integer, PlanetSystem> getAllPlanetSystem() {
        return planetSystems;
    }

    public Planet getPlanet(String systemName, String planetName) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanet(planetName);

        return null;
    }
    public Planet getPlanet(String systemName, int planetId) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanet(planetId);

        return null;
    }

    public ArrayList getMoons(String systemName, String planetName) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanet(planetName).moonArray();

        return null;
    }
    public ArrayList getMoons(String systemName, int planetId) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanet(planetId).moonArray();

        return null;
    }

    public Moon getMoon(String systemName, String planetName, String moonName) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanet(planetName).getMoon(moonName);

        return null;
    }
    public Moon getMoon(String systemName, String planetName, int moonId) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanet(planetName).getMoon(moonId);

        return null;
    }
    public Moon getMoon(String systemName, int planetId, String moonName) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanet(planetId).getMoon(moonName);

        return null;
    }
    public Moon getMoon(String systemName, int planetId, int moonId) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanet(planetId).getMoon(moonId);

        return null;
    }
}

/*
TODO: Add moons to the systems
 */