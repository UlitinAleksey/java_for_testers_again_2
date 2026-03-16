package ru.uai.geometry.figures;

public class Triangle {

    public double a;
    public double b;
    public double c;

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public void printTriangleArea() {
        System.out.println("Ploshad treugolnika so storonami "
                + a + ", " + b + " and " + c + " = " + area());
    }
}





