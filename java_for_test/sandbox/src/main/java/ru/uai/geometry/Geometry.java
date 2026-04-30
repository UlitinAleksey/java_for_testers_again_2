package ru.uai.geometry;

import ru.uai.geometry.figures.Rectangle;
import ru.uai.geometry.figures.Square;
import ru.uai.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static ru.uai.geometry.figures.Triangle.*;

public class Geometry {


    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100));

        var squares = Stream.generate(new Square(7),new Square(5),new Square(3));
//        for (Square square : squares) {
//            Square.printSquareArea(square);
//        }
        Consumer<Square> print = (square) -> {
            Square.printSquareArea(square);

        };
        squares.forEach(print);


//        Rectangle.printRectangleArea(new Rectangle(1, 2));
//        Rectangle.printRectangleArea(new Rectangle(7, 9));
//
//
//        Triangle triangle1 = new Triangle(3.0, 4.0, 5.0);
//        triangle1.printTriangleArea();


    }
}



