package com.codecool.marsexploration.mapelements.model;

import java.util.Arrays;

public class Map {
    private String[][] representation;

    private boolean successfullyGenerated;

    public Map(String[][] representation) {
        this.representation = representation;
    }

    public boolean isSuccessfullyGenerated() {
        return successfullyGenerated;
    }

    public void setSuccessfullyGenerated(boolean successfullyGenerated) {
        this.successfullyGenerated = successfullyGenerated;
    }

    private static String createStringRepresentation(String[][] arr) {


        StringBuilder simpleString = new StringBuilder();


        for (String[] row : arr) {
            System.out.println(row.length);
            for (String element : row) {
                simpleString.append(element).append(" ");
            }
            simpleString.append(System.lineSeparator());
        }

        return simpleString.toString();
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}

