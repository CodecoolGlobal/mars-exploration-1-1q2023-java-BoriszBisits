package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    @Override
    public boolean validate(MapConfiguration mapConfig) {
        int allElements = 0;
        int availableSpace = (int) (mapConfig.mapSize() * mapConfig.elementToSpaceRatio());
        for (MapElementConfiguration mapElement : mapConfig.mapElementConfigurations()) {
            //System.out.println(mapElement.name());
            for (ElementToSize element : mapElement.elementToSizes()) {
                //System.out.println(element.elementCount() + " " + element.size());
                allElements += element.elementCount() * element.size();
            }
        }
        if(availableSpace-allElements>0) {
            return true;
        } else {
            return false;
        }
    }
}
