package com.company.model;

import org.jetbrains.annotations.NotNull;

import static com.company.Main.planetSystemList;

public class CenterStar extends CelestialBody{
    private double effectiveTemp;

    public CenterStar() {

    }
    CenterStar(String name) {
        super(name);
    }
    CenterStar(String name, double mass) {
        super(name, mass);
    }
    CenterStar(String name, double mass, double radius) {
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
        switch (prop) {
            case "name":
            case "Name":
                this.name = new UserInput(value).getString();
                break;
            case "mass":
            case "Mass":
                this.mass = new UserInput(value).getDouble();
                break;
            case "radius":
            case "Radius":
                this.radius = new UserInput(value).getDouble();
                break;
            case "EffectiveTemp":
            case "effectiveTemp":
            case "Effective_Temp":
            case "effective_Temp":
                this.effectiveTemp = new UserInput(value).getDouble();
                break;
            case "Sun":
            case "sun":
                planetSystem.setCenterStar(this);
                break;
            case "pictureUrl":
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
