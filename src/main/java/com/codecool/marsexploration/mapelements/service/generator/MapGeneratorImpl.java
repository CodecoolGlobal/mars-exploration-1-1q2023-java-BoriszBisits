package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;

import java.util.List;

public class MapGeneratorImpl implements MapGenerator{
    DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int size = dimensionCalculator.calculateDimension(mapConfig.mapSize(), 0);
        String [][] mapRep = new String[size][size];
        generateMountains(mapConfig.mapElementConfigurations().get(0).elementToSizes());
        generatePits(mapConfig.mapElementConfigurations().get(1).elementToSizes());
        generateMinerals(mapConfig.mapElementConfigurations().get(2).elementToSizes());
        generateWater(mapConfig.mapElementConfigurations().get(3).elementToSizes());
        return null;
    }
    private void generateMountains(List<ElementToSize> elementToSizes) {
        for(ElementToSize element:elementToSizes){
            System.out.println(element);
        }
    }

    private void generatePits(List<ElementToSize> elementToSizes) {
        for(ElementToSize element:elementToSizes){
            System.out.println(element);
        }
    }

    private void generateMinerals(List<ElementToSize> elementToSizes) {
        for(ElementToSize element:elementToSizes){
            System.out.println(element);
        }
    }

    private void generateWater(List<ElementToSize> elementToSizes) {
        for(ElementToSize element:elementToSizes){
            System.out.println(element);
        }
    }
}

