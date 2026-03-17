package ru.uai.geometry;

import ru.uai.geometry.figures.Rectangle;
import ru.uai.geometry.figures.Square;
import ru.uai.geometry.figures.Triangle;

import static ru.uai.geometry.figures.Triangle.*;

public class Geometry {


    public static void main(String[] args) {
        Square.printSquareArea(new Square(7));
        Square.printSquareArea(new Square(5));
        Square.printSquareArea(new Square(3));


        Rectangle.printRectangleArea(new Rectangle(1, 2));
        Rectangle.printRectangleArea(new Rectangle(7, 9));


        Triangle triangle1 = new Triangle(3.0, 4.0, 5.0);
        triangle1.printTriangleArea();


    }
}



