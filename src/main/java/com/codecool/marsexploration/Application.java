package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.*;
import com.codecool.marsexploration.configuration.model.*;
import com.codecool.marsexploration.configuration.service.*;
import com.codecool.marsexploration.mapelements.input.Input;
import com.codecool.marsexploration.mapelements.model.MapElement;
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


        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

        CoordinateCalculator coordinateCalculator = null;

        MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl();

        MapConfigurationValidator mapConfigValidator = new MapConfigurationValidatorImpl();
        MapElementPlacer mapElementPlacer = null;

        MapGenerator mapGenerator = new MapGeneratorImpl();

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
                //Input.Input_elemntSize("Mountain")
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                3,
                ""
        );

        MapElementConfiguration pitsCfg = new MapElementConfiguration(
                pitSymbol,
                "pit",
                //Input.Input_elemntSize("Pit"),
                List.of(
                        new ElementToSize(2,10),
                        new ElementToSize(1,20)
                ),
                10,
                ""
        );

        MapElementConfiguration mineralCfg = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                //Input.Input_elemntSize("Mineral"),
                List.of(
                        new ElementToSize(10,1)
                ),
                0,
                "#"
        );

        MapElementConfiguration waterCfg = new MapElementConfiguration(
                waterSymbol,
                "water",
                //Input.Input_elemntSize("Water"),
                List.of(
                        new ElementToSize(10,1)
                ),
                0,
                "&"
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg,pitsCfg,mineralCfg,waterCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }
}