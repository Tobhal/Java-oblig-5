package com.company.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Planet extends NaturalSatellite {
    public Planet() {
    }
    Planet(String name) {
        super(name);
    }
    Planet(String name, double mass) {
        super(name, mass);
    }
    Planet(String name, double mass, double radius) {
        super(name, mass, radius);
    }
    Planet(String name, double mass, double radius, double semiMajorAxis) {
        super(name, mass, radius, semiMajorAxis);
    }
    Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity) {
        super(name, mass, radius, semiMajorAxis, eccentricity);
    }
    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod);
    }
    Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody centralCelestialBody) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody);
    }

    private ArrayList<Moon> moons = new ArrayList<>();

    public double surfaceGravity() {
        return (0.00000000006674 * mass)/(Math.pow((radius * 1000), 2));
    }

    public String getMoons() {
        StringBuilder bString = new StringBuilder();

        for (Moon moon : this.moons) {
            bString.append(moon.name).append(", ");
        }

        return bString.toString();
    }
    public Moon getMoon(int index) {
        return moons.get(index);
    }
    public Moon getMoon(String name) {
        for (Moon moon : moons) {
            if (moon.name.equals(name))
                return moon;
        }
        return null;
    }
    public List<Moon> moonList() {
        return moons;
    }   // This is a list so i can use it in a for each loop
    public ArrayList moonArray() {
        return moons;
    }

    public Boolean hasMoon(String name) {
        for (Moon moon : moons) {
            if (moon.name.equals(name))
                return true;
        }
        return false;
    }

    public int numberOfMoons() {
        return moons.size();
    }

    public void addMoon(Moon moon) {
        moons.add(moon);
    }

    public void setPropFromOtherPlanet(Planet otherPlanet) {
        setName(otherPlanet.getName());
        setMass(otherPlanet.getMass());
        setRadius(otherPlanet.getRadius());
        setSemiMajorAxis(otherPlanet.getSemiMajorAxis());
        setEccentricity(otherPlanet.getEccentricity());
        setOrbitalPeriod(otherPlanet.getOrbitalPeriod());
        setCentralCelestialBody(otherPlanet.getCentralCelestialBody());
    }

    public void printPlanet() {   // Prints all of the plannets that is stored
        System.out.println("The planet " + name + " has a radus of: " + radius + " km and the gravity is: " + mass + "");
    }
    public void printAllInfo() {
        System.out.println(toString());
        if (!moons.isEmpty()) {
            System.out.println("Moons (" + moons.size() + "):");
            for (Moon moon : moons) {
                System.out.println(moon.getName());
            }
        }
    }

    @Override
    public int compareTo(@NotNull CelestialBody otherCelestialBody) {
        return (int)(this.mass - otherCelestialBody.mass);
    }
}

/*
 */