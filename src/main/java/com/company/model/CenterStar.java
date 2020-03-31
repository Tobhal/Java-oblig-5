package com.company.model;

import org.jetbrains.annotations.NotNull;

public class CenterStar extends CelestialBody{
    private double effectiveTemp;

    public CenterStar() {
    }
    public CenterStar(String name) {
        super(name);
    }
    public CenterStar(String name, double mass) {
        super(name, mass);
    }
    public CenterStar(String name, double mass, double radius) {
        super(name, mass, radius);
    }
    public CenterStar(String name, double mass, double radius, double effectiveTemp) {
        super(name, mass, radius);
        this.effectiveTemp = effectiveTemp;
    }

    public void setEffetiveTemp(double effectiveTemp) {
        this.effectiveTemp = effectiveTemp;
    }

    public double getEffectiveTemp() {
        return effectiveTemp;
    }
    public double getMsun() {
        return mass/1.98892E30;
    }
    public double getRsun() {
        return radius/695700;
    }

    public void setSunPropByName(String prop, String value, PlanetSystem planetSystem) {
        switch (prop.toLowerCase()) {
            case "name":
            case "st_name":
                this.name = new UserInput(value).getString();
                break;
            case "mass":
            case "st_mass":
                this.mass = new UserInput(value).getDouble();
                break;
            case "radius":
            case "st_radius":
                this.radius = new UserInput(value).getDouble();
                break;
            case "effectivetemp":
            case "effective_temp":
            case "st_efftemp":
                this.effectiveTemp = new UserInput(value).getDouble();
                break;
            case "sun":
            //case "#_psys_name":
                planetSystem.setCenterStar(this);
                break;
            case "pictureurl":
            case "st_pictureurl":
                this.pictureUrl = new UserInput(value).getString();
                break;
            default:
                break;
        }
    }

    // Print
    public void printAllInfo() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(name + " has the properties:");
        System.out.println("Radius " + radius + " and Rsun:" + getRsun() + "\nMass: " + mass + " and " + getMsun() + "\nAnd the effective tempratur is: " + getEffectiveTemp());
        System.out.println("--------------------------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Mass: " + mass + " | Radius: " + radius + " | Effective Tempratur: " + effectiveTemp;
    }

    @Override
    public int compareTo(@NotNull CelestialBody otherCelestialBody) {
        return (int)(this.mass - otherCelestialBody.mass);
    }
}
