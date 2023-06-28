package com.codecool.marsexploration.mapelements.model;

public class MapElement {

    private final String[][] representation;
    private final String name;
    private final int dimension;
    private final String preferredLocationSymbol;

    public MapElement(String[][] representation, String name, int dimension, String preferredLocationSymbol) {
        this.representation = representation;
        this.name = name;
        this.dimension = dimension;
        this.preferredLocationSymbol = preferredLocationSymbol;
    }

    public String getName() {
        return name;
    }

    public String getPreferredLocationSymbol() {
        return preferredLocationSymbol;
    }

    @Override
    public String toString() {
        return createStringRepresentation(getRepresentation());
    }

    public String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (String[] row : arr) {
            for (String element : row) {
                sb.append(element);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public String[][] getRepresentation() {
        return representation;
    }
}

