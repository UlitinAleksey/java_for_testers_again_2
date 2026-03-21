package ru.uai.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {


    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-1.0, 2.0, 3.0);
            Assertions.fail("treugolnik imeet otricatelnuiy storonu");
        } catch (IllegalArgumentException exception) {
            // OK

        }


    }

    @Test
    void cannotCreateTriangleWithViolatedInequality() {
        try {
            new Triangle(1.0, 2.0, 3.0);
            Assertions.fail("Summa dvuh storon ravna tretey");
        } catch (IllegalArgumentException exception) {
            // OK
        }

    }

    @Test
    void cannotCreateTriangleWithViolatedInequality2() {
        try {
            new Triangle(1.0, 2.0, 3.0);
            Assertions.fail("Summa dvuh storon ravna tretey");
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }
    @Test
    void testEquality(){
        var r1 = new Triangle(5.0,4.0, 3);
        var r2 = new Triangle(4.0,5.0,3);
        Assertions.assertEquals(r1,r2);
    }
}