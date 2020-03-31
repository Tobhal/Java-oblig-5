package com.company.repository;

import com.company.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class UniverseJSONRepository implements IUniverseRepository {
    public ArrayList<PlanetSystem> planetSystems = new ArrayList<>();

    public UniverseJSONRepository(String fileName) throws IOException {
        if (!fileName.matches(".+\\.csv"))  //Checks if there is .csv in the file name. if not then add.
            fileName += ".json";
        fileName = "json-files/" + fileName;

        File file = new File(fileName);

        ObjectMapper mapper = new ObjectMapper();

        PlanetSystem[] planetSystemArray = new PlanetSystem[0];

        planetSystemArray = mapper.readValue(file, PlanetSystem[].class);

        planetSystems.addAll(Arrays.asList(planetSystemArray));
    }

    public void inportJSON(String fileName) {

    }

    public ArrayList getAllPlanetsInSystem (String systemName) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanetArray();

        return new ArrayList<>();
    }

    public PlanetSystem getPlanetSystem(String planetSystemName) {
        for (PlanetSystem system : planetSystems) {
            if (system.getName().equals(planetSystemName)) {
                return system;
            }
        }

        return null;
    }
    public ArrayList<PlanetSystem> getAllPlanetSystem() {
        return planetSystems;
    }

    public CenterStar getCenterStar(String systemName) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);
        return aktuellPlanetSystem.getCenterStar();
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

    @Override
    public void createPlanet(String systemName, Planet newPlanet) {
        for (PlanetSystem planetSystem : planetSystems)
            if (planetSystem.getName().equalsIgnoreCase(systemName))
                planetSystem.addPlanetToSystem(newPlanet);
        save();
    }

    @Override
    public void updatePlanet(String systemName, String planetName, Planet newPlanet) {
        for (PlanetSystem planetSystem : planetSystems)
            if (planetSystem.getName().equalsIgnoreCase(systemName))
                planetSystem.getPlanet(planetName).setPropFromOtherPlanet(newPlanet);
        save();
    }

    @Override
    public void deletePlanet(String systemName, String planetName) {
        for (PlanetSystem planetSystem : planetSystems)
            if (planetSystem.getName().equalsIgnoreCase(systemName))
                planetSystem.removePlanetFromSystem(planetName);
        save();
    }

    @Override
    public void save() {
        Thread thread = new Thread( () -> {
            try {
                SaveFile.saveFileJason(getAllPlanetSystem());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void sort(PlanetSystem.Sort sort) {
        for (PlanetSystem planetSystem : planetSystems) {
            planetSystem.sortPlanet(sort);
        }
    }
}
