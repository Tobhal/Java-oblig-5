package com.company.model;

public class SItoAU {       // A class to convert "Normal" units in the SI format, to the astronomical units
    // All of the constants that vil be used for the convertions
    public static final double ASTRONOMICAL_UNIT = 149597871; // km
    public static final double JUPITER_MASS = 1.898E27;
    public static final double JUPITER_RADIUS = 71492;
    public static final double SUN_MASS = 1.98892E30;
    public static final double SUN_RADIUS = 695700;
    public static final double EARTH_MASS = 5.972E24;
    public static final double EARTH_RADIUS = 6371;

    public static double toMjup(double kg) {
        return kg / JUPITER_MASS;
    }
    public static double toRjup(double km) {
        return km / JUPITER_RADIUS;
    }
    public static double toMsun(double kg) {
        return kg / SUN_MASS;
    }
    public static double toRsun(double km) {
        return km / SUN_RADIUS;
    }
    public static double toMearth(double kg) {
        return kg / EARTH_MASS;
    }
    public static double toRearth(double km) {
        return km / EARTH_RADIUS;
    }
    public static double toAU(double km) {
        return km / ASTRONOMICAL_UNIT;
    }
    public static double aUtoKM(double au) {
        return au * ASTRONOMICAL_UNIT;
    }
}
