package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoordinateCalculatorImplTest {


    @Test
    public void testGetRandomCoordinate() {

        CoordinateCalculatorImpl coordinateCalculator = new CoordinateCalculatorImpl();
        int dimension = 10;
        Coordinate coordinate = coordinateCalculator.getRandomCoordinate(dimension);
        int x = coordinate.x();
        int y = coordinate.y();
        assertNotNull(coordinate);
        assertTrue(x >= 0 && x < dimension);
        assertTrue(y >= 0 && y < dimension);

    }

    @Test
    public void testGetAdjacentCoordinatesZeroDimension() {

        CoordinateCalculatorImpl coordinateCalculator = new CoordinateCalculatorImpl();

            List<Coordinate> listOfCoordinates = new ArrayList<>();

        Coordinate coordinate = new Coordinate(3, 4);

        int dimension = 0;

        listOfCoordinates.add(coordinate);

        Iterable<Coordinate> results =  coordinateCalculator.getAdjacentCoordinates(coordinate, dimension);
        Assertions.assertEquals(listOfCoordinates, results);


    }

    @Test
    public void testGetAdjacentCoordinatesWithDimensionOne() {

        CoordinateCalculatorImpl coordinateCalculator = new CoordinateCalculatorImpl();

        List<Coordinate> listOfCoordinates = new ArrayList<>();

        Coordinate coordinate = new Coordinate(3, 4);

        int dimension = 1;

        listOfCoordinates.add(coordinate);

        Iterable<Coordinate> results =  coordinateCalculator.getAdjacentCoordinates(coordinate, dimension);
        Assertions.assertEquals(listOfCoordinates, results);


    }

}