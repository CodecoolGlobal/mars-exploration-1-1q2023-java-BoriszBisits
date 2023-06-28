package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.DimensionCalculator;

import java.util.Random;

public class MapElementBuilderImpl implements MapElementBuilder {
    DimensionCalculator dimensionCalculator;
    Random random = new Random();

    public MapElementBuilderImpl(DimensionCalculator dimensionCalculator) {
        this.dimensionCalculator = dimensionCalculator;
    }

    @Override
    public String[][] build(int size, String symbol, int dimensionGrowth) {
        int side = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] element = new String[side][side];
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                element[i][j] = " ";
            }
        }
        int remainingElements = size;
        while (remainingElements > 0) {
            int y = random.nextInt(side);
            int x = random.nextInt(side);
            if (element[x][y].equals(" ")) {
                element[x][y] = symbol;
                remainingElements--;
            }
        }
        return element;
    }
}
