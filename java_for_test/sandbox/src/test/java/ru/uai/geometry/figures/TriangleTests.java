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
}