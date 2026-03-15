package ru.uai.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {


    @Test
    void canCalculateArea(){
        var result = Triangle.TriangleArea(3,4,5);
        Assertions.assertEquals(6.0, result);

    }
    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(12, Triangle.perimeter(3,4,5));


    }
}
