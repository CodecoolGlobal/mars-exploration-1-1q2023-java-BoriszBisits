package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;

public class MapGeneratorImpl implements MapGenerator {

    private final MapElementsGenerator mapElementsGenerator;
    private final DimensionCalculator dimensionCalculator;
    private final MapElementPlacer mapElementPlacer;
    CoordinateCalculator calculator = new CoordinateCalculatorImpl();

    public MapGeneratorImpl(MapElementsGenerator mapElementsGenerator, DimensionCalculator dimensionCalculator, MapElementPlacer mapElementPlacer) {
        this.mapElementsGenerator = mapElementsGenerator;
        this.dimensionCalculator = dimensionCalculator;
        this.mapElementPlacer = mapElementPlacer;
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int side = dimensionCalculator.calculateDimension(mapConfig.mapSize(), 0);
        String[][] mapRep = new String[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                mapRep[i][j] = " ";
            }
        }
        Iterable<MapElement> mapElements = mapElementsGenerator.createAll(mapConfig);
        for (MapElement element : mapElements) {
            while (true) {
                Coordinate randomCoordinate = calculator.getRandomCoordinate(side);
                if (mapElementPlacer.canPlaceElement(element, mapRep, randomCoordinate)) {
                    mapElementPlacer.placeElement(element, mapRep, randomCoordinate);
                    break;
                }
            }
        }
        return new Map(mapRep);
    }
}

