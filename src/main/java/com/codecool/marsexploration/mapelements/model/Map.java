package com.codecool.marsexploration.mapelements.model;

import java.util.Arrays;

public class Map {
    private static String[][] representation;

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

    public static String createStringRepresentation(String[][] arr) {


        StringBuilder simpleString = new StringBuilder();


        for (String[] row : arr) {
            //System.out.println(row.length);
            for (String element : row) {
                simpleString.append(element).append(" ");
            }
            simpleString.append(System.lineSeparator());
        }

        return simpleString.toString();
    }
    private static String createStringRepresentationForAll(String[][] arr){
        String rep = "";
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                //System.out.println(arr[y][x]);
                rep = rep+arr[y][x];
            }
        }
        return rep;
    }

    @Override
    public String toString() {
        return createStringRepresentationForAll(representation);
    }

    public String toStringForFileWriter() {
        return createStringRepresentation(representation);
    }
}

