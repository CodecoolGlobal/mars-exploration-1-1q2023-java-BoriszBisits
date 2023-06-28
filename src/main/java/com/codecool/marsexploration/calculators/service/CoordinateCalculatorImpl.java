package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    Random random = new Random();

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        int randomCoordinateX = random.nextInt(0, dimension);
        int randomCoordinateY = random.nextInt(0, dimension);

        return new Coordinate(randomCoordinateX, randomCoordinateY);
    }

    @Override
    public List<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> listOfAdjacentCoordinates = new ArrayList<>();
        int startX = coordinate.x() - dimension / 2;
        int startY = coordinate.y() - dimension / 2;
        int endX = coordinate.x() + dimension / 2;
        int endY = coordinate.y() + dimension / 2;

        for (int i = startY; i <= endY; i++) {
            for (int j = startX; j <= endX; j++) {
                listOfAdjacentCoordinates.add(new Coordinate(j, i));
            }
        }
        return listOfAdjacentCoordinates;
    }
}
