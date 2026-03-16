package ru.uai.geometry;

import ru.uai.geometry.figures.Rectangle;
import ru.uai.geometry.figures.Square;
import ru.uai.geometry.figures.Triangle;

import static ru.uai.geometry.figures.Triangle.*;

public class Geometry {


    public static void main(String[] args) {
        Square.printSquareArea(7);
        Square.printSquareArea(5);
        Square.printSquareArea(3);


        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);


        Triangle triangle1 = new Triangle();

        triangle1.a = 3.0;
        triangle1.b = 4.0;
        triangle1.c = 5.0;

        triangle1.printTriangleArea();


        Triangle triangle2 = new Triangle();

        triangle2.a = 7.0;
        triangle2.b = 8.0;
        triangle2.c = 9.0;

        triangle2.printTriangleArea();

    }

}




