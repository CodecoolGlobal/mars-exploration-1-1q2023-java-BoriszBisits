package com.codecool.marsexploration.mapelements.model;


public class Map {
    private String[][] representation;

    public Map(String[][] representation) {
        this.representation = representation;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String[] row : representation) {
            for (String element : row) {
                sb.append(element).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String toStringForFileWriter() {
        return createStringRepresentation(representation);
    }
}

