package ru.uai.geometry.figures;

public class Triangle {

    public static void printTriangleArea(double t, double r, double c) {
        System.out.println("Ploshad treugolnika so storonami " + t + ", " + r + " and " + c + " = " + TriangleArea(t,r,c));
    }

    public static double TriangleArea(double t, double r, double c) {
        double p = (t + r + c) / 2;
        return Math.sqrt(p * (p - t) * (p - r) * (p - c));
    }

    public static double perimeter(double t, double r, double c) {
        return t + r + c;
    }
}