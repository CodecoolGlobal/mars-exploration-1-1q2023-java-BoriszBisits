package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Random;

public class MapElementBuilderImpl implements MapElementBuilder{
    DimensionCalculator dimensionCalculator;
    Random random = new Random();

    public MapElementBuilderImpl(DimensionCalculator dimensionCalculator) {
        this.dimensionCalculator = dimensionCalculator;
    }

    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        int side = dimensionCalculator.calculateDimension(size,dimensionGrowth);
        String[][] mapElement = new String[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                mapElement[i][j]="";
            }
        }
        int remainingElements = size;
        while (remainingElements>0){
            int y = random.nextInt(side);
            int x = random.nextInt(side);
            if(mapElement[x][y].equals("")){
                mapElement[x][y]=symbol;
                remainingElements--;
            }
        }
        MapElement element = new MapElement(mapElement,name,size);
        System.out.println(element);
        return element;
    }
}
