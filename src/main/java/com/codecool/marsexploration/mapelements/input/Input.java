package com.codecool.marsexploration.mapelements.input;

import com.codecool.marsexploration.configuration.model.ElementToSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    public static List<ElementToSize> Input_elemntSize(String elementType) {
        List<ElementToSize> elementList=new ArrayList<>();
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter number of "+elementType);

        int numOfElements = Integer.parseInt(myObj.nextLine());
        System.out.println("Number of "+elementType+" is: " + numOfElements);
        for (int i = 0; i <numOfElements ; i++) {
            int index=i+1;
            System.out.println("Please Enter the "+index+" "+ elementType+"'s size");
            int sizeOfElement=Integer.parseInt(myObj.nextLine());
            ElementToSize element=new ElementToSize(1,sizeOfElement);
            elementList.add(element);

        }
        return elementList;
    }
    List<Integer> elementSize=new ArrayList<>();
}
