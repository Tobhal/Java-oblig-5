package com.company.model;

import org.jetbrains.annotations.NotNull;

public class Moon extends NaturalSatellite {
    public Moon() {
    }
    Moon(String name) {
        super(name);
    }
    Moon(String name, double mass) {
        super(name, mass);
    }
    Moon(String name, double mass, double radius) {
        super(name, mass, radius);
    }
    Moon(String name, double mass, double radius, double semiMajorAxis) {
        super(name, mass, radius, semiMajorAxis);
    }
    Moon(String name, double mass, double radius, double semiMajorAxis, double eccentricity) {
        super(name, mass, radius, semiMajorAxis, eccentricity);
    }
    Moon(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod);
    }
    Moon(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String centralCelestialBody) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody);
    }

    public void printAllInfo() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(name + " has theas properties:");
        System.out.println("Mass: " + mass +
                "\nRadius: " + radius +
                "\nSemi Maijor Axis: " + semiMajorAxis +
                "\nEccentricity: " + eccentricity +
                "\nOrbital period: " + orbitalPeriod +
                "\nIs Orbeting: " + centralCelestialBody.getName());
        System.out.println("--------------------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Mass: " + mass + " | Radius: " + radius  +
                "Semi Maijor Axis: " + semiMajorAxis + " | Eccentricity: " + eccentricity + " | Orbital Period: " + orbitalPeriod  +
                "Orbering: " + centralCelestialBody;
    }

    @Override
    public int compareTo(@NotNull CelestialBody otherCelestialBody) {
        return (int)(this.mass - otherCelestialBody.mass);
    }
}
