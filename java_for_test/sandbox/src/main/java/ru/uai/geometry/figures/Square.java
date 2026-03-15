package ru.uai.geometry.figures;

public class Square {
   public static void printSquareArea(double side) {
        System.out.println(String.format("Ploshad kvadrata so storonoy %f = %f" , side,  squareArea(side)));
    }

    public static double squareArea(double a) {

        return a * a;
    }

    public static double perimeter(double a) {
       return 4 * a;
    }
}
