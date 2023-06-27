package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatoImpl;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacerImpl;

public class MapGeneratorImpl implements MapGenerator {

    private final MapElementsGenerator mapElementsGenerator;
    private final DimensionCalculator dimensionCalculator;
    MapElementPlacer mapElementPlacer = new MapElementPlacerImpl();
    CoordinateCalculator calculator = new CoordinateCalculatoImpl();

    public MapGeneratorImpl(MapElementsGenerator mapElementsGenerator, DimensionCalculator dimensionCalculator) {
        this.mapElementsGenerator = mapElementsGenerator;
        this.dimensionCalculator = dimensionCalculator;
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {
        int side = dimensionCalculator.calculateDimension(mapConfig.mapSize(), 0);
        String[][] mapRep = new String[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                mapRep[i][j] = "";
            }
        }
        Iterable<MapElement> mapElements = mapElementsGenerator.createAll(mapConfig);
        for (MapElement element : mapElements) {
            while (true) {
                Coordinate randomCoordinate = calculator.getRandomCoordinate(side);
                if (mapElementPlacer.canPlaceElement(element, mapRep, randomCoordinate)) {
                    mapElementPlacer.placeElement(element, mapRep, randomCoordinate);
                }
            }
        }
        return new Map(mapRep);
    }
}

