package com.codecool.marsexploration.calculators.service;

public class DimensionCalculatorImpl implements DimensionCalculator {
    @Override
    public int calculateDimension(int size, int dimensionGrowth) {
        int side = (int) Math.sqrt(size);
        return side + dimensionGrowth;
    }
}
