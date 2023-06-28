package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.*;
import com.codecool.marsexploration.configuration.model.*;
import com.codecool.marsexploration.configuration.service.*;
import com.codecool.marsexploration.mapelements.input.Input;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.service.builder.*;
import com.codecool.marsexploration.mapelements.service.generator.*;
import com.codecool.marsexploration.mapelements.service.placer.*;
import com.codecool.marsexploration.output.service.MapFileWriter;
import com.codecool.marsexploration.output.service.MapFileWriterImpl;

import java.util.List;
import java.util.Scanner;

public class Application {
    // You can change this to any directory you like
    private static final String WorkDir = "src/main";

    public static void main(String[] args) {
        System.out.println("Mars Exploration Sprint 1");


        Input input = new Input();
        MapConfiguration mapConfig = getConfiguration(input);


        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();

        MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementFactory);

        MapConfigurationValidator mapConfigValidator = new MapConfigurationValidatorImpl();
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);

        MapGenerator mapGenerator = new MapGeneratorImpl(mapElementsGenerator, dimensionCalculator, mapElementPlacer);
        MapFileWriter fileWriter = new MapFileWriterImpl();
        Map map = mapGenerator.generate(mapConfig);
        fileWriter.writeMapFile(map, "src/main/resources/explorationTest0.map");

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
        int mapSize;

        List<ElementToSize> Input_Mountain;

        Scanner myObj = new Scanner(System.in);
        System.out.println("Please Enter map Size");
        mapSize = Integer.parseInt(myObj.nextLine());

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
                        new ElementToSize(2, 10),
                        new ElementToSize(1, 20)
                ),
                10,
                ""
        );

        MapElementConfiguration mineralCfg = new MapElementConfiguration(
                mineralSymbol,
                "mineral",
                //Input.Input_elemntSize("Mineral"),
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                "#"
        );

        MapElementConfiguration waterCfg = new MapElementConfiguration(
                waterSymbol,
                "water",
                //Input.Input_elemntSize("Water"),
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                "&"
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralCfg, waterCfg);
        return new MapConfiguration(mapSize, 0.5, elementsCfg);
    }
}