package com.company.model;

public abstract class NaturalSatellite extends CelestialBody {
    protected double semiMajorAxis;
    protected double eccentricity;
    protected double orbitalPeriod;
    protected CelestialBody centralCelestialBody;   // The body that this body goes around

    public NaturalSatellite() {
    }
    NaturalSatellite(String name) {
        super(name);
    }
    NaturalSatellite(String name, double mass) {
        super(name, mass);
    }
    NaturalSatellite(String name, double mass, double radius) {
        super(name, mass, radius);
    }
    NaturalSatellite(String name, double mass, double radius, double semiMajorAxis) {
        super(name, mass, radius);
        this.semiMajorAxis = semiMajorAxis;
    }
    NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity) {
        super(name, mass, radius, semiMajorAxis, eccentricity);
    }
    NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod);
    }
    NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, CelestialBody centralCelestialBody) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod);
        this.centralCelestialBody = centralCelestialBody;
    }
    NaturalSatellite(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String centralCelestialBody) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod);
        /*
        if (planetSystemList.get(whatSystem).bodyExists(centralCelestialBody, PlanetSystem.Body.STAR)) {
            this.centralCelestialBody = planetSystemList.get(whatSystem).getCenterStar();
        } else if (planetSystemList.get(whatSystem).bodyExists(centralCelestialBody, PlanetSystem.Body.PLANET)) {
            this.centralCelestialBody = planetSystemList.get(whatSystem).getPlanet(centralCelestialBody);
        }
         */
    }

    public enum UnitType {
        METERS,
        KILOMETER
    }
    public enum Degree {
        RADIANS,
        DEGREES
    }

    public void setSemiMajorAxis(double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }
    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }
    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }
    public void setCentralCelestialBody(CelestialBody centralCelestialBody) {
        this.centralCelestialBody = centralCelestialBody;
    }

    public void setBodyPropByName(String prop, String value, PlanetSystem planetSystem) {
        switch (prop.toLowerCase()) {
            case "name":
            case "pl_name":
                this.name = new UserInput(value).getString();
                break;
            case "mass":
            case "pl_mass":
                this.mass = new UserInput(value).getDouble();
                break;
            case "radius":
            case "pl_radius":
                this.radius = new UserInput(value).getDouble();
                break;
            case "semiMajorAxis":
            case "semi major axis":
            case "pl_semimajor":
                this.semiMajorAxis = new UserInput(value).getDouble();
                break;
            case "eccentricity":
            case "pl_ecc":
                this.eccentricity = new UserInput(value).getDouble();
                break;
            case "orbitalPeriod":
            case "pl_orbper":
                this.orbitalPeriod = new UserInput(value).getDouble();
                break;
            case "centralCelestialBody":
            case "st_name":
                if (planetSystem.getCenterStar().getName().equals(value)) {
                    this.centralCelestialBody = planetSystem.getCenterStar();
                    break;
                } else if (planetSystem.bodyExists(value, PlanetSystem.Body.PLANET)) {
                    this.centralCelestialBody = planetSystem.getPlanet(value);
                    break;
                }

                System.out.println("Did not find " + value + " as a central body.");
                break;
            case "pictureurl":
            case "pl_pictureurl":
                this.pictureUrl = new UserInput(value).getString();
                break;
            default:
                break;
        }
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }
    public double getEccentricity() {
        return eccentricity;
    }
    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public CelestialBody getCentralCelestialBody() {
        return centralCelestialBody;
    }

    public double distanceToCentralBody(double degrees, Degree degreeType) {

        if (degreeType == Degree.DEGREES)
            degrees = degrees % 360;        // This makes shure that there is only 360 degrees
        else if (degreeType == Degree.RADIANS)
            degrees = degrees % 2*Math.PI;  // This makes shure that there is only 2 Pi radians

        double a = semiMajorAxis;
        double e = this.eccentricity;
        double O = degreeType == Degree.RADIANS ? degrees : Math.toRadians(degrees);    // sets degrees to radians or converts degrees to radians

        double r = (a*(1-Math.pow(e, 2)))/(1+e*Math.cos(O));

        return SItoAU.aUtoKM(r);
    }

    public double orbitingVelocity(double distance, UnitType unitType) {
        double r = distance * 1000;
        double g = 0.00000000006674;
        double m = centralCelestialBody.mass;

        double unit = unitType == UnitType.METERS ? 1 : 1000 ;

        double v = Math.sqrt((g*m)/r)/unit;

        return v;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Mass: " + mass + " | Radius: " + radius  +
                " | Semi Maijor Axis: " + semiMajorAxis + " | Eccentricity: " + eccentricity + " | Orbital Period: " + orbitalPeriod +
                " | Orbering: " + centralCelestialBody.getName();
    }
}

/*
TODO: implement Comparable
 */