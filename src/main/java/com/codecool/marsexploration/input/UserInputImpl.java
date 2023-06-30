package com.codecool.marsexploration.input;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputImpl implements UserInput{
    public MapConfiguration getConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        Scanner scanner = new Scanner(System.in);
        int mapSize = getInput(scanner, "Please enter map size");

        MapElementConfiguration mountainsCfg = getBigElementConfig(scanner, mountainSymbol, "mountain", 3);
        MapElementConfiguration pitsCfg = getBigElementConfig(scanner, pitSymbol, "pit", 5);
        MapElementConfiguration mineralCfg = getSmallElementConfig(scanner, mineralSymbol, "mineral", "#");
        MapElementConfiguration waterCfg = getSmallElementConfig(scanner, waterSymbol, "water", "&");
        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitsCfg, mineralCfg, waterCfg);

        return new MapConfiguration(mapSize, 0.5, elementsCfg);
    }

    private int getInput(Scanner scanner, String text) {
        System.out.println(text);
        while (true) {
            int input = Integer.parseInt(scanner.nextLine());
            if (input > 0) {
                return input;
            } else {
                System.out.println("Input has to be bigger than 0");
            }
        }
    }

    private MapElementConfiguration getBigElementConfig(Scanner scanner, String symbol, String name, int dimensionGrowth) {
        List<ElementToSize> bigElement = new ArrayList<>();
        int numberOfBigElement = getInput(scanner, "How many " + name + "s do you want to have?");

        for (int i = 0; i < numberOfBigElement; i++) {
            int nextSize = getInput(scanner, "Please enter the size of " + name + "#" + (i + 1));
            bigElement.add(new ElementToSize(1, nextSize));
        }
        return new MapElementConfiguration(
                symbol,
                name,
                bigElement,
                dimensionGrowth,
                " "
        );
    }

    private MapElementConfiguration getSmallElementConfig(Scanner scanner,
                                                          String symbol,
                                                          String name,
                                                          String preferredLocationSymbol) {
        int numberOfSmallElement = getInput(scanner, "How many " + name + "s do you want to have?");
        return new MapElementConfiguration(
                symbol,
                name,
                List.of(new ElementToSize(numberOfSmallElement, 1)),
                0,
                preferredLocationSymbol
        );
    }
}
