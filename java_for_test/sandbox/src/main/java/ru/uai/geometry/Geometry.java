package ru.uai.geometry;

import ru.uai.geometry.figures.Rectangle;
import ru.uai.geometry.figures.Square;
import ru.uai.geometry.figures.Triangle;

public class Geometry {


    public static void main(String[] args) {
        Square.printSquareArea(7);
        Square.printSquareArea(5);
        Square.printSquareArea(3);


        Rectangle.printRectangleArea(3.0, 5.0);
        Rectangle.printRectangleArea(7.0, 9.0);


        Triangle.printTriangleArea(3.0, 4.0, 5.0);
        Triangle.printTriangleArea(7.0,8.0,9.0);


}

}




