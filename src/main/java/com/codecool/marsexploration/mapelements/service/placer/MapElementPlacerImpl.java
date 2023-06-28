package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.Application;
import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.List;

public class MapElementPlacerImpl implements MapElementPlacer {


    private final CoordinateCalculator coordinateCalculator;

    public MapElementPlacerImpl(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
    }


    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {

        if (map[coordinate.x()][coordinate.y()].equals("")) {
            int side = map.length;
            int elementSide = (int) Math.sqrt(element.toString().length());
            int xMinus = (coordinate.x() - elementSide/2)-1;
            int xPlus = (coordinate.x() + elementSide/2)-1;
            int yMinus = (coordinate.y() - elementSide/2)-1;
            int yPlus = (coordinate.y() +elementSide/2)-1;

            if (xMinus >= 0 && yMinus >= 0 && yPlus < side-1 && xPlus < side-1) {
                if (element.getName().equals("mountain") || element.getName().equals("pit")) {
                    Iterable<Coordinate> listOfCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate,elementSide);
                    if (isContaining(map, listOfCoordinates, "")) {
                        return true;
                    }
                }
                if (element.getName().equals("water") || element.getName().equals("mineral")) {
                    Iterable<Coordinate> isMountainOrPit = coordinateCalculator.getAdjacentCoordinates(coordinate,1);
                    if (element.getName().equals("water")) {
                        if (isContaining(map, isMountainOrPit, "&")) {
                            return true;
                        }
                    }
                    if (element.getName().equals("mineral")) {
                        if (isContaining(map, isMountainOrPit, "#")) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;

    }

    private static boolean isContaining(String[][] map, Iterable<Coordinate> listOfCoordinates, String elementSymbol) {
        for (Coordinate c : listOfCoordinates) {
            if (map[c.x()][c.y()].equals(elementSymbol)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {

        int elementSide= (int) Math.sqrt(element.toString().length()+1);
        int xMinus = (coordinate.x() - (elementSide/2));
        int yMinus = (coordinate.y() - (elementSide/2));

        for (int y = 0; y < elementSide; y++) {
            for (int x = 0; x < elementSide ; x++) {
                String character = Character.toString(element.toString().charAt(y*elementSide+x));
                map[x+yMinus][y+xMinus]=character;
            }
        }
    }
}
