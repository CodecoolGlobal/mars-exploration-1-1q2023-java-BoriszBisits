package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.Map;

import java.io.FileWriter;

public class MapFileWriterImpl implements MapFileWriter{
    @Override
    public void writeMapFile(Map map, String file) {
        try{
            FileWriter fw=new FileWriter("C:\\Users\\PC\\codecool\\OOP\\mars-exploration-1-1q2023-java-BoriszBisits\\src\\main\\resources\\explorationTest.0.map");

            fw.write(map.toString());
            fw.close();
        }catch(Exception e){System.out.println(e);}
        System.out.println("Success...");
    }
}

