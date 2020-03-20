package com.company;

import com.company.controller.PlanetController;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;

import com.company.repository.UniverseRepository;

public class Application {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

        app.config.enableWebjars();

        app.get("/planet-systems", new VueComponent("planet-system-overview"));
        app.get("/planet-systems/:planet-system-id", new VueComponent("planet-system-detail"));
        app.get("/planet-systems/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));
        app.get("/planet-systems/:planet-system.id/planets/:planet-id/moons", new VueComponent("moons-overview"));
        app.get("/planet-systems/:planet-system-id/planets/:planet-id/moons/:moon-id", new VueComponent("moon-detail"));

        PlanetController planetController = new PlanetController(new UniverseRepository());

        app.get("/api/planet-systems", planetController::getPlanetSystems);
        app.get("/api/planet-systems/:planet-system-id", planetController::getPlanetSystem);
        app.get("/api/planet-systems/:planet-system-id/planets", planetController::getPlanets);
        app.get("/api/planet-systems/:planet-system-id/planets/:planetName", planetController::getPlanet);
        app.get("/api/planet-systems/:planet-system-id/planets/:planetName/moons", planetController::getMoons);
        app.get("/api/planet-systems/:planet-system-id/planets/:planetName/moons/:moonName", planetController::getMoon);

    }
}
