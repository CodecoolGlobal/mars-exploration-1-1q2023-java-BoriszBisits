package com.codecool.marsexploration.calculators.service;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DimensionCalculatorImplTest {

    private DimensionCalculatorImpl dimensionCalculator;

    @BeforeEach
    public void setUp() {
        dimensionCalculator = new DimensionCalculatorImpl();
    }

    @Test

    public void testCalculateDimension() {

        int result1 = dimensionCalculator.calculateDimension(16, 0);
        assertEquals(4, result1);

        int result2 = dimensionCalculator.calculateDimension(25, -3);
        assertEquals(2, result2);

        int result3 = dimensionCalculator.calculateDimension(9, 2);
        assertEquals(5, result3);


    }




}
