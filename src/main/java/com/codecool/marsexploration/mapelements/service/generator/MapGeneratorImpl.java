package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;

public class MapGeneratorImpl implements MapGenerator{
    DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
    @Override
    public Map generate(MapConfiguration mapConfig) {
        int size = dimensionCalculator.calculateDimension(mapConfig.mapSize(), 0);
        System.out.println(size);
        return null;
    }
}
