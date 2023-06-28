package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class CoordinateCalculatorImpl implements CoordinateCalculator{
    Random random=new Random();
    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        int randomCordinateX= random.nextInt(0,dimension);
        int randomCordinateY= random.nextInt(0,dimension);

        return new Coordinate(randomCordinateX,randomCordinateY);
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> listOfAdjacentCoordinates=new ArrayList<>();
        int startX=coordinate.x()-dimension;
        int startY=coordinate.y()-dimension;
        int endX  = coordinate.x()+dimension;
        int endY = coordinate.y()+dimension;
       if(startX<0) {
           startX = 0;
       }
       if(startY<0)  {
           startY=0;
       }
        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                listOfAdjacentCoordinates.add(new Coordinate(j,i));
            }
        }
        return listOfAdjacentCoordinates;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        List<Coordinate> listOfAdjacentCoordinatesOfCordinates=null;
        for(Coordinate s: coordinates){
            listOfAdjacentCoordinatesOfCordinates.addAll((Collection<? extends Coordinate>) getAdjacentCoordinates(s,dimension));
        }
        return listOfAdjacentCoordinatesOfCordinates;
    }


}
