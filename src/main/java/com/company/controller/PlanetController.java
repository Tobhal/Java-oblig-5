package com.company.controller;

import com.company.model.CelestialBody;
import com.company.model.CenterStar;
import com.company.model.PlanetSystem;
import io.javalin.http.Context;
import com.company.model.Planet;
import com.company.repository.IUniverseRepository;
import org.jetbrains.annotations.NotNull;

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

    public void deletePlanet(Context context) {
        System.out.println("Delete planet");

        String systemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planetName");

        universeRepository.deletePlanet(systemName, planetName);

        context.redirect("/planet-systems/" + systemName);
    }
    public void createPlanet(Context context) {
        String systemName = context.pathParam("planet-system-id");

        String name = context.formParam("name");
        double mass = Double.parseDouble(context.formParam("mass"));
        double radius = Double.parseDouble(context.formParam("radius"));
        double semiMajorAxis = Double.parseDouble(context.formParam("semiMajorAxis"));
        double eccentricity = Double.parseDouble(context.formParam("eccentricity"));
        double orbitalPeroid = Double.parseDouble(context.formParam("orbitalPeriod"));
        String pictureUrl = context.formParam("pictureUrl");

        Planet planet = new Planet(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeroid);
        planet.setPictureUrl(pictureUrl);
        planet.setCentralCelestialBody(universeRepository.getCenterStar(systemName));

        System.out.println(planet);

        universeRepository.createPlanet(systemName, planet);

        context.redirect("/planet-systems/" + systemName + "/planets/" + name);
    }
    public void updatePlanet(Context context) {
        String systemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planetName");

        String name = context.formParam("name");
        double mass = Double.parseDouble(context.formParam("mass"));
        double radius = Double.parseDouble(context.formParam("radius"));
        double semiMajorAxis = Double.parseDouble(context.formParam("semiMajorAxis"));
        double eccentricity = Double.parseDouble(context.formParam("eccentricity"));
        double orbitalPeroid = Double.parseDouble(context.formParam("orbitalPeriod"));
        String pictureUrl = context.formParam("pichureUrl");

        Planet planet = new Planet(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeroid);
        planet.setPictureUrl(pictureUrl);
        planet.setCentralCelestialBody(universeRepository.getPlanet(systemName, name).getCentralCelestialBody());

        universeRepository.updatePlanet(systemName, planetName, planet);

        context.redirect("/planet-systems/" + systemName + "/planets/" + name);
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