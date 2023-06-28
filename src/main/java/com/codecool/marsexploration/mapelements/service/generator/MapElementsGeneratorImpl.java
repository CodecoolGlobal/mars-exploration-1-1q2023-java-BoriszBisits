package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import java.util.ArrayList;
import java.util.List;

public class MapElementsGeneratorImpl implements MapElementsGenerator {
    private final MapElementBuilder mapElementBuilder;

    public MapElementsGeneratorImpl(MapElementBuilder mapElementBuilder) {
        this.mapElementBuilder = mapElementBuilder;
    }

    @Override
    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {
        List<MapElement> allMapElements = new ArrayList<>();

        for (int i = 0; i < mapConfig.mapElementConfigurations().size(); i++) {
            List<MapElement> generatedMapElements = generateMapElements(mapConfig.mapElementConfigurations().get(i));
            allMapElements.addAll(generatedMapElements);
        }
        return allMapElements;
    }

    private List<MapElement> generateMapElements(MapElementConfiguration mapElementConfiguration) {
        List<MapElement> mapElements = new ArrayList<>();
        for (ElementToSize element : mapElementConfiguration.elementToSizes()) {
            for (int i = 0; i < element.elementCount(); i++) {
                String[][] singleMapElement = mapElementBuilder.build(element.size(),
                        mapElementConfiguration.symbol(),
                        mapElementConfiguration.dimensionGrowth());

                mapElements.add(new MapElement(singleMapElement,
                        mapElementConfiguration.name(),
                        mapElementConfiguration.dimensionGrowth(),
                        mapElementConfiguration.preferredLocationSymbol()));
            }
        }
        return mapElements;
    }
}
