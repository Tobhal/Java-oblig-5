package com.company.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    static String CSVFilePath = "CSV-files";
    static String CSVFileExtetnion = ".csv";
    static String planetCSVDevider = ",";
    static boolean emtySystem = false;

    public static void readFile_V1_5(PlanetSystem planetSystem, String systemCSV) {
        CSVFilePath = "CSV-files/" + systemCSV;
        CSVFileExtetnion = ".csv";
        int typePlace = 0;
        int namePlace = 0;
        int centralCelestialBodyPlace = 0;

        try (Scanner fileScanner = new Scanner(new File(CSVFilePath + CSVFileExtetnion))) {   // Putting the scanner in the try so that is closes when the try catch ends
            String[] splitFileString = fileScanner.next().split(planetCSVDevider);

            for (int i = 0; i < splitFileString.length; i++) {  // Here to save where information i am going to use later
                // TODO: Make not dependent on the things below... if I feel like it
                switch (splitFileString[i]) {
                    case "type":
                        typePlace = i;
                        break;
                    case "name":
                        namePlace = i;
                        break;
                    case "centralCelestialBody":
                        centralCelestialBodyPlace = i;
                        break;

                }
            }
            String[] stringProp = splitFileString;

            emtySystem = planetSystem.getNumberOfPlanets() == 0;

            while (fileScanner.hasNextLine()) {
                splitFileString = fileScanner.next().split(planetCSVDevider);

                if (emtySystem) {   // If the system was emty before the import started it will not do a check if there is some of the same planets
                    switch (splitFileString[typePlace]) {
                        case "sun":
                            CenterStar centerStar = new CenterStar();
                            for (int i = 0; i < splitFileString.length; i++) {
                                if (!splitFileString[i].equals("")) {
                                    centerStar.setSunPropByName(stringProp[i], splitFileString[i], planetSystem);
                                }
                            }
                            planetSystem.setCenterStar(centerStar);
                            break;
                        case "planet":
                            Planet planet = new Planet();
                            for (int i = 0; i < splitFileString.length; i++) {
                                if (!splitFileString[i].equals("")) {
                                    planet.setBodyPropByName(stringProp[i], splitFileString[i], planetSystem);
                                }
                            }
                            planetSystem.addPlanetToSystem(planet);
                            break;
                        case "moon":
                            Moon moon = new Moon();
                            for (int i = 0; i < splitFileString.length; i++) {
                                if (!splitFileString[i].equals("")) {
                                    moon.setBodyPropByName(stringProp[i], splitFileString[i], planetSystem);
                                }
                            }
                            planetSystem.getPlanet(splitFileString[centralCelestialBodyPlace]).addMoon(moon);
                            break;
                    }
                } else {    // If the system has planets check if there is a planet with the same props
                    switch (splitFileString[typePlace]) {
                        case "sun":
                            if (planetSystem.getCenterStar() == null) {
                                CenterStar centerStar = new CenterStar();
                                for (int i = 0; i < splitFileString.length; i++) {
                                    if (!splitFileString[i].equals("")) {
                                        centerStar.setSunPropByName(stringProp[i], splitFileString[i], planetSystem);
                                    }
                                }
                                planetSystem.setCenterStar(centerStar);
                            } else {
                                for (int i = 0; i < splitFileString.length; i++) {
                                    if (!splitFileString[i].equals("")) {
                                        planetSystem.getCenterStar().setSunPropByName(stringProp[i], splitFileString[i], planetSystem);
                                    }
                                }
                            }
                            break;
                        case "planet":
                            if (planetSystem.bodyExists(splitFileString[namePlace], PlanetSystem.Body.PLANET, splitFileString[centralCelestialBodyPlace])) {
                                for (int i = 0; i < stringProp.length; i++) {
                                    planetSystem.getPlanet(splitFileString[namePlace]).setBodyPropByName(stringProp[i], splitFileString[i], planetSystem);
                                }
                            } else {
                                Planet planet = new Planet();
                                for (int i = 0; i < splitFileString.length; i++) {
                                    if (!splitFileString[i].equals("")) {
                                        planet.setBodyPropByName(stringProp[i], splitFileString[i], planetSystem);
                                    }
                                }
                                planetSystem.addPlanetToSystem(planet);
                            }
                            break;
                        case "moon":
                            if (planetSystem.bodyExists(splitFileString[namePlace], PlanetSystem.Body.MOON, splitFileString[centralCelestialBodyPlace])) {
                                for (int i = 0; i < stringProp.length; i++) {
                                    planetSystem.getPlanet(splitFileString[centralCelestialBodyPlace]).getMoon(namePlace).setBodyPropByName(stringProp[i], splitFileString[i], planetSystem);
                                }
                            } else {
                                Moon moon = new Moon();
                                for (int i = 0; i < splitFileString.length; i++) {
                                    if (!splitFileString[i].equals("")) {
                                        moon.setBodyPropByName(stringProp[i], splitFileString[i], planetSystem);
                                    }
                                }
                                planetSystem.getPlanet(splitFileString[centralCelestialBodyPlace]).addMoon(moon);
                            }
                            break;
                    }
                }
            }
            System.out.println("Planets Added:");
            planetSystem.printPlanetList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Eksisterer " + CSVFilePath + CSVFileExtetnion + " ?");   // If the file does not exist this will be printet
            System.out.println(e.toString());   // -------------------------------------
        }
    }

    public static void readCSV(PlanetSystem planetSystem) {

    }

    public static void readAllMoonsCSV(PlanetSystem planetSystem) {
        try (Scanner fileScanner = new Scanner(new File("CSV-files/allMoons.csv"))){
            fileScanner.nextLine();

            while(fileScanner.hasNextLine()) {
                String[] splitStringLine = fileScanner.next().split(",");

                double gm = new UserInput(splitStringLine[2]).getDouble();

                double radius = new UserInput(splitStringLine[3]).getDouble();
                double mass = ((4/3)*Math.PI*Math.pow(radius, 3)) * new UserInput(splitStringLine[4]).getDouble();
                double ngm = -gm;
                double temp1 = gm*radius;
                double temp2 = ngm+temp1;

                double semiMajorAxis = Math.sqrt(temp2);
                double orbitalPeriod = 0;

                Moon moon = new Moon(splitStringLine[1], mass, radius, semiMajorAxis, 0, orbitalPeriod, splitStringLine[0]);
                planetSystem.getPlanet(splitStringLine[0]).addMoon(moon);

                System.out.println(moon.name + " - " + moon.semiMajorAxis + " - " + moon.centralCelestialBody.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Eksisterer " + CSVFilePath + CSVFileExtetnion + " ?");   // If the file does not exist this will be printet
            System.out.println(e.toString());   // -------------------------------------
        }
    }
}
