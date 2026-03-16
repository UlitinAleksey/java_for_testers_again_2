package ru.uai.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {


    @Test
    public void testPerimeterWithSides3And4And5() {
        Triangle t = new Triangle();
        t.a = 3;
        t.b = 4;
        t.c = 5;

        double expected = 12.0;
        double actual = t.perimeter();
        Assertions.assertEquals(expected, actual, 0.0001);

    }

}
