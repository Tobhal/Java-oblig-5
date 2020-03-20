package com.company.controller;

import com.company.model.PlanetSystem;
import io.javalin.http.Context;
import com.company.model.Planet;
import com.company.repository.IUniverseRepository;

public class PlanetController {
    private IUniverseRepository universeRepository;

    public PlanetController(IUniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }

    public void getPlanetSystems(Context context) {
        context.json(universeRepository.getAllPlanetSystem());
    }
    public void getPlanetSystem(Context context) {
        String systemName = context.pathParam("planet-system-id");

        sortPlanetSystem(context);

        context.json(universeRepository.getPlanetSystem(systemName));
    }

    public void getPlanets(Context context) {
        String systemName = context.pathParam("planet-system-id");

        sortPlanetSystem(context);

        context.json(universeRepository.getAllPlanetsInSystem(systemName));
    }
    public void getPlanet(Context context) {
        String systemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planetName");

        context.json(universeRepository.getPlanet(systemName, planetName));
    }

    public void getMoons(Context context) {
        String systemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planetName");

        context.json(universeRepository.getMoons(systemName, planetName));
    }
    public void getMoon(Context context) {
        String systemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planetName");
        String moonName = context.pathParam("moonName");

        context.json(universeRepository.getMoon(systemName, planetName, moonName));
    }

    private void sortPlanetSystem(Context context) {
        String sort = context.queryParam("sort_by");

        if (sort == null)
            return;

        switch (sort.toLowerCase()) {
            case "name":
                universeRepository.sort(PlanetSystem.Sort.ALFABETICAL);
                break;
            case "mass":
                universeRepository.sort(PlanetSystem.Sort.MASS);
                break;
            case "radius":
                universeRepository.sort(PlanetSystem.Sort.RADIUS);
                break;
            case "num":
                universeRepository.sort(PlanetSystem.Sort.NUMBER);
                break;
        }
    }

}