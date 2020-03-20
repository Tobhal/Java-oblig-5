package com.company.repository;

import com.company.model.Moon;
import com.company.model.Planet;
import com.company.model.PlanetSystem;
import com.company.model.ReadFile;

import java.util.ArrayList;

public class UniverseRepository implements IUniverseRepository {
    public ArrayList<PlanetSystem> planetSystems = new ArrayList<>();

    public UniverseRepository() {
        PlanetSystem solarSystem = new PlanetSystem("Solar System", "https://upload.wikimedia.org/wikipedia/commons/c/c3/Solar_sys8.jpg");
        ReadFile.readFile_V1_5(solarSystem, "solarSystem");
        planetSystems.add(solarSystem);

        PlanetSystem kepler = new PlanetSystem("Kepler-11", "https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png");
        ReadFile.readFile_V1_5(kepler, "keplerSystem");
        planetSystems.add(kepler);
    }

    public ArrayList getAllPlanetsInSystem (String systemName) {
        PlanetSystem aktuellPlanetSystem = getPlanetSystem(systemName);

        if (aktuellPlanetSystem != null)
            return aktuellPlanetSystem.getPlanetArray();

        return new ArrayList<>();
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
    public void sort(PlanetSystem.Sort sort) {
        for (PlanetSystem planetSystem : planetSystems) {
            planetSystem.sortPlanet(sort);
        }
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
}