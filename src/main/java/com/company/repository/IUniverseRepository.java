package com.company.repository;

import com.company.model.PlanetSystem;
import com.company.model.Planet;
import com.company.model.Moon;

import java.util.ArrayList;

public interface IUniverseRepository {
    ArrayList<Planet> getAllPlanetsInSystem(String systemName);

    ArrayList<PlanetSystem> getAllPlanetSystem();
    PlanetSystem getPlanetSystem(String planetSystemName);

    Planet getPlanet(String systemName, String planetName);
    Planet getPlanet(String systemName, int planetId);

    ArrayList<Moon> getMoons(String systemName, String planetName);
    ArrayList<Moon> getMoons(String systemName, int planetId);

    Moon getMoon(String systemName, String planetName, String moonName);
    Moon getMoon(String systemName, String planetName, int moonId);
    Moon getMoon(String systemName, int planetId, String moonName);
    Moon getMoon(String systemName, int planetId, int moonId);

    void sort(PlanetSystem.Sort sort);
}
/*
TODO: Get moons
 */