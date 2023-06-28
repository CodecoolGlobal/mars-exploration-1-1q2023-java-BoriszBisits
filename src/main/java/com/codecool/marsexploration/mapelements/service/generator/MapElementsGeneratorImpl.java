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
    List<MapElement> mapElements = new ArrayList<>();

    public MapElementsGeneratorImpl(MapElementBuilder mapElementBuilder) {
        this.mapElementBuilder = mapElementBuilder;
    }

    @Override
    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {

        for (int i = 0; i < mapConfig.mapElementConfigurations().size(); i++) {
            System.out.println(i);
            generateMapElements(mapConfig.mapElementConfigurations().get(i));
        }
        return mapElements;
    }

    private void generateMapElements(MapElementConfiguration mapElementConfiguration) {
        for (ElementToSize element : mapElementConfiguration.elementToSizes()) {
            for (int i = 0; i < element.elementCount(); i++) {
                mapElements.add(
                        mapElementBuilder.build(element.size(),
                                mapElementConfiguration.symbol(),
                                mapElementConfiguration.name(),
                                mapElementConfiguration.dimensionGrowth(),
                                mapElementConfiguration.preferredLocationSymbol())
                );
            }
        }
    }
}
