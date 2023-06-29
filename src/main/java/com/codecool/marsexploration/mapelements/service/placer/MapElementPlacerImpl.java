package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.List;

public class MapElementPlacerImpl implements MapElementPlacer {
    private final CoordinateCalculator coordinateCalculator;

    public MapElementPlacerImpl(CoordinateCalculator coordinateCalculator) {
        this.coordinateCalculator = coordinateCalculator;
    }


    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int mapSide = map.length;
        int elementSide = element.getRepresentation().length;
        if (map[coordinate.x()][coordinate.y()].equals(" ")) {
            if (element.getName().equals("mountain") || element.getName().equals("pit")) {
                List<Coordinate> neighbouringCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, elementSide);
                if (notOutsideOfMap(neighbouringCoordinates, mapSide)) {
                    String placeString = collectNeighbouringValues(map, neighbouringCoordinates);
                    return !placeString.contains("#") || !placeString.contains("&");
                }
                return false;
            } else {
                List<Coordinate> neighbouringCoordinates = coordinateCalculator.getAdjacentCoordinates(coordinate, elementSide + 1);
                if (notOutsideOfMap(neighbouringCoordinates, mapSide)) {
                    String placeString = collectNeighbouringValues(map, neighbouringCoordinates);
                    return placeString.contains(element.getPreferredLocationSymbol());
                }
            }
        }
        return false;
    }

    private String collectNeighbouringValues(String[][] map, List<Coordinate> neighbouringCoordinates) {
        String placeString = "";
        for (Coordinate neighbouringCoordinate : neighbouringCoordinates) {
            placeString = placeString + map[neighbouringCoordinate.x()][neighbouringCoordinate.y()];
        }
        return placeString;
    }

    private boolean notOutsideOfMap(List<Coordinate> neighbouringCoordinates, int mapSide) {
        return neighbouringCoordinates.get(0).x() >= 0
                && neighbouringCoordinates.get(0).y() >= 0
                && neighbouringCoordinates.get(neighbouringCoordinates.size() - 1).x() < mapSide
                && neighbouringCoordinates.get(neighbouringCoordinates.size() - 1).y() < mapSide;
    }

    @Override
    public void placeElement(MapElement element, String[][] map, Coordinate coordinate) {
        int elementSide = element.getRepresentation().length;
        String elementString = element.createStringRepresentation(element.getRepresentation());
        List<Coordinate> coordinateList = coordinateCalculator.getAdjacentCoordinates(coordinate, elementSide);
        int iterator = 0;
        int x = coordinateList.get(0).x();
        int y = coordinateList.get(0).y();
        while (iterator < (elementSide * elementSide + elementSide)) {
            char nextChar = elementString.charAt(iterator);
            String character = String.valueOf(elementString.charAt(iterator));
            if ((nextChar == '\n') || (nextChar == '\r')) {
                x = coordinateList.get(0).x();
                y++;
            } else {
                if (!character.equals(" ")) {
                    map[x][y] = String.valueOf(elementString.charAt(iterator));
                }
                x++;
            }
            iterator++;
        }
    }
}
