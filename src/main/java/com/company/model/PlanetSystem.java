package com.company.model;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Condition;

import static com.company.Main.*;

public class PlanetSystem{
    private String name;
    private CenterStar centerStar;
    private ArrayList<Planet> planetList = new ArrayList<>();      // Array list with all of the planets in
    private String pictureUrl;

    public PlanetSystem() {
    }
    public PlanetSystem(String name) {
        this.name = name;
    }
    PlanetSystem(String name, Planet planet) {
        this.name = name;
        planetList.add(planet);
    }
    public PlanetSystem(String name, String pictureUrl) {
        this.name = name;
        this.pictureUrl = pictureUrl;
    }
    PlanetSystem(String name, Planet planet, CenterStar senterStar) {
        this.name = name;
        planetList.add(planet);
        this.centerStar = senterStar;
    }
    PlanetSystem(String name, Planet planet, CenterStar senterStar, String pictureUrl) {
        this.name = name;
        planetList.add(planet);
        this.centerStar = senterStar;
        this.pictureUrl = pictureUrl;
    }

    public enum Body {
        STAR,
        PLANET,
        MOON
    }
    public enum Sort {
        ALFABETICAL,
        MASS,
        RADIUS,
        NUMBER
    }

    // Add
    public void addPlanetToSystem(Planet planet) {
        planetList.add(planet);
    }

    public void createBody(Body body) {
        switch (body) {
            case STAR:
                createCenterStar();
                break;
            case PLANET:
                createPlanet();
                break;
            case MOON:
                createSatellite();
                break;
        }
    }
    public void createCenterStar() {
        System.out.print("Write the name of the Center Star");
        UserInput input1 = new UserInput();

        System.out.print("Write the mass of the Center Star");
        UserInput input2 = new UserInput();

        System.out.print("Write the radius of the Center Star");
        UserInput input3 = new UserInput();

        System.out.print("Write the efective temprature of the Center Star");
        UserInput input4 = new UserInput();

        centerStar = new CenterStar(input1.getString(), input2.getDouble(), input3.getDouble(), input4.getDouble());
    }
    public void createPlanet() {        // Function to add planets.
        System.out.println("Type the name of the planet:");
        Planet planet = new Planet(new UserInput().getString());
        planetSystemList.get(whatSystem).addPlanetToSystem(planet);

        while (running) {
            System.out.println("What prop do you want to create?");
            System.out.println("1: Mass, 2: Radius, 3: Semi Major Axis, 4. Eccectricity, 5 Orbital Peorid, 6 Central Celestial Body");
            System.out.println("9 to exit");
            /*
            switch (new UserInput().getString()) {
                case "1":
                    planetSystemList.get(whatSystem).getPlanet(planet.name).setBodyPropByName("mass", sc.next());
                    break;
                case "2":
                    planetSystemList.get(whatSystem).getPlanet(planet.name).setBodyPropByName("radius", sc.next());
                    break;
                case "3":
                    planetSystemList.get(whatSystem).getPlanet(planet.name).setBodyPropByName("semiMajorAxis", sc.next());
                    break;
                case "4":
                    planetSystemList.get(whatSystem).getPlanet(planet.name).setBodyPropByName("eccentricity", sc.next());
                    break;
                case "5":
                    planetSystemList.get(whatSystem).getPlanet(planet.name).setBodyPropByName("orbitalPeriod", sc.next());
                    break;
                case "6":
                    planetSystemList.get(whatSystem).getPlanet(planet.name).setBodyPropByName("centralCelestialBody", sc.next());
                    break;
                case "9":
                    running = false;
                    break;
                default:
                    System.out.println("That is not a valdi command");
                    break;
            }
             */
        }
    }
    public void createSatellite() {

    }

    // Sett
    public void setName(String name) {
        this.name = name;
    }
    public void setCenterStar(CenterStar senterStar) {
        this.centerStar = senterStar;
    }

    // Get
    public String getName() {
        return name;
    }
    public CenterStar getCenterStar() {
        return centerStar;
    }
    public Planet getPlanet(int index) {
        if (planetList.get(index) != null) {
            return planetList.get(index);
        } else {
        return null;
        }
    }
    public Planet getPlanet(String name) {
        for (Planet planet : planetList) {
            if (planet.getName().equals(name)) {
                return planet;
            }
        }
        return null;
    }
    public int getNumberOfPlanets() {
        return planetList.size();
    }
    public List<Planet> getPlanetList() {
        return planetList;
    }   // This is a list so i can use it in a for each loop
    public ArrayList getPlanetArray() {
        return planetList;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }

    public Planet biggestPlanet() {
        Planet tempPlanet = planetList.get(0);
        for (Planet planet : planetList) {
            if (tempPlanet.getMassRadiusRatio() < planet.getMassRadiusRatio()) {
                tempPlanet = planet;
            }
        }
        return tempPlanet;
    }
    public Planet smallestPlanet() {
        Planet tempPlanet = planetList.get(0);
        for (Planet planet : planetList) {
            if (tempPlanet.getMassRadiusRatio() > planet.getMassRadiusRatio()) {
                tempPlanet = planet;
            }
        }
        return tempPlanet;
    }

    public boolean bodyExists(String name, Body body) {
        if (body == Body.PLANET) {
            for (Planet planet : planetList) {
                if (planet.getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    public boolean bodyExists(String name, Body body, String centralBody) {
        switch (body) {
            case PLANET:
                for (Planet planet : planetList) {
                    if (planet.getName().equals(name)) {
                        return true;
                    }
                }
                return false;
            case MOON:
                for (int i = 0; i < planetSystemList.get(whatSystem).getPlanet(centralBody).numberOfMoons(); i++) {
                    if (planetSystemList.get(whatSystem).getPlanet(centralBody).getMoon(i).getName().equals(name)) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }

    public void sortPlanet(Sort sort) {
        // planetList.sort((Comparator<? super Planet>) condition);
        switch (sort) {
            case ALFABETICAL:
                planetList.sort((planet1, planet2) -> planet1.getName().compareToIgnoreCase(planet2.getName()));
                break;
            case MASS:
                planetList.sort((planet1, planet2) -> (int)(planet1.getMass() - planet2.getMass()));
                break;
            case RADIUS:
                planetList.sort((planet1, planet2) -> (int)(planet1.getRadius() - planet2.getRadius()));
                break;
            case NUMBER:
                planetList.sort(Comparator.comparingInt(CelestialBody::getIndex));
                break;
        }

    }

    // Print
    public void printPlanetList() {
        for (Planet planet : planetList) {
            System.out.println(planet.getName());
        }
    }
    public void printAllInfo() {
        System.out.println(centerStar.toString());
        for (Planet planet : planetList) {
            planet.printAllInfo();
            System.out.println("\n");
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Center Star: " + centerStar.getName() + " | Number of planets: " + planetList.size();
    }
}
