package ru.uai.geometry.figures;

public class Square {
   public static void printSquareArea(double side) {
       String text = String.format("Ploshad kvadrata so storonoy %f = %f", side, squareArea(side));
       System.out.println(text);
    }

    private static double squareArea(double a) {

        return a * a;
    }
}
