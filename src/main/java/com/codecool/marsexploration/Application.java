package com.codecool.marsexploration;

import com.codecool.marsexploration.calculators.service.*;
import com.codecool.marsexploration.configuration.model.*;
import com.codecool.marsexploration.configuration.service.*;
import com.codecool.marsexploration.input.UserInput;
import com.codecool.marsexploration.input.UserInputImpl;
import com.codecool.marsexploration.mapelements.service.builder.*;
import com.codecool.marsexploration.mapelements.service.generator.*;
import com.codecool.marsexploration.mapelements.service.placer.*;
import com.codecool.marsexploration.output.service.MapFileWriter;
import com.codecool.marsexploration.output.service.MapFileWriterImpl;
import com.codecool.marsexploration.output.service.ui.GamePanel;
import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        System.out.println("Mars Exploration Sprint 1");

        UserInput userInput = new UserInputImpl();
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl();
        MapElementBuilder mapElementFactory = new MapElementBuilderImpl(dimensionCalculator);
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(mapElementFactory);
        MapConfigurationValidator mapConfigValidator = new MapConfigurationValidatorImpl();
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl(coordinateCalculator);
        MapGenerator mapGenerator = new MapGeneratorImpl(mapElementsGenerator, dimensionCalculator, mapElementPlacer);

        while (true){
            MapConfiguration mapConfig = userInput.getConfiguration();
            if (mapConfigValidator.validate(mapConfig)){
                createAndWriteMaps(3, mapGenerator, mapConfig);
                break;
            }
        }
        System.out.println("Mars maps successfully generated.");
    }

    private static void createAndWriteMaps(int count, MapGenerator mapGenerator, MapConfiguration mapConfig) {
        MapFileWriter fileWriter = new MapFileWriterImpl();
//        for (int i = 0; i < count; i++) {
//            String filepath = "src/main/resources/exploration-"+i+".map";
//            Map map = mapGenerator.generate(mapConfig);
//            fileWriter.writeMapFile(map,filepath);
//        }
        showUi(mapConfig);
    }

    private static void showUi(MapConfiguration mapConfig) {
        JFrame window = new JFrame();
        DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Mars exploration");

        int side = dimensionCalculator.calculateDimension(mapConfig.mapSize(), 0);
        GamePanel gp = new GamePanel(side);

        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gp.startGameThread();
    }
}