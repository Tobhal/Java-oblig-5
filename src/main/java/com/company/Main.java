package com.company;

import com.company.model.*;
import com.company.repository.UniverseCSVRepository;
import com.company.repository.UniverseJSONRepository;

import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //UniverseCSVRepository universeCSVRepository = new UniverseCSVRepository("planets");
        UniverseJSONRepository universeJSONRepository = new UniverseJSONRepository("planets");
        universeJSONRepository.save();
    }
}

/*
 */