package com.company.repository;

import com.company.model.Moon;
import com.company.model.Planet;
import com.company.model.PlanetSystem;

import java.util.ArrayList;
import java.util.HashMap;

public interface IUniverseCSVRepository {
    ArrayList<Planet> getAllPlanetsInSystem(String systemName);

    HashMap<Integer, PlanetSystem> getAllPlanetSystem();
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
