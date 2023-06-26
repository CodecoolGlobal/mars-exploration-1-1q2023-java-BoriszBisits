package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.*;
import com.codecool.marsexploration.configuration.model.*;
import com.codecool.marsexploration.configuration.service.*;
import com.codecool.marsexploration.mapelements.input.Input;
import com.codecool.marsexploration.mapelements.service.builder.*;
import com.codecool.marsexploration.mapelements.service.generator.*;
import com.codecool.marsexploration.mapelements.service.placer.*;

import java.util.List;

public class Application {
    // You can change this to any directory you like
    private static final String WorkDir = "src/main";

    public static void main(String[] args) {
        System.out.println("Mars Exploration Sprint 1");

       Input input=new Input();
        MapConfiguration mapConfig = getConfiguration(input);
        DimensionCalculator dimensionCalculator = null;
        CoordinateCalculator coordinateCalculator = null;

        MapElementBuilder mapElementFactory = null;
        MapElementsGenerator mapElementsGenerator = null;

        MapConfigurationValidator mapConfigValidator = null;
        MapElementPlacer mapElementPlacer = null;

        MapGenerator mapGenerator = null;

        createAndWriteMaps(3, mapGenerator, mapConfig);

        System.out.println("Mars maps successfully generated.");
    }

    private static void createAndWriteMaps(int count, MapGenerator mapGenerator, MapConfiguration mapConfig) {
    }

    private static MapConfiguration getConfiguration(Input input) {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        List<ElementToSize> Input_Mountain;
        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                mountainSymbol,
                "mountain",
                input.Input_elemntSize("Mountain")
                ,
                3,
                ""
        );

        MapElementConfiguration pitsCfg = new MapElementConfiguration(
                pitSymbol,
                "pit",
                input.Input_elemntSize("Pit"),
                10,
                ""
        );

        MapElementConfiguration mineralCfg = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                input.Input_elemntSize("Mineral"),
                0,
                "#"
        );

        MapElementConfiguration waterCfg = new MapElementConfiguration(
                waterSymbol,
                "water",
                input.Input_elemntSize("Water"),
                0,
                "&"
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }
}

