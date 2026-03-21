package ru.uai.geometry.figures;

import java.util.Objects;

public class Rectangle {
    private final double a;
    private final double b;

    public Rectangle(double a, double b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Rectangle sides should be non-negative");
        }
        this.a = a;
        this.b = b;
    }

    public static void printRectangleArea(Rectangle r) {
        String text = String.format("Ploshad pryamougolnika so storonami %f and %f = %f",
                r.a, r.b, r.area());
        System.out.println(text);
    }

    public double area() {
        return this.a * this.b;
    }

    public double perimeter() {
        return 2 * (this.a + this.b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return (Double.compare(a, this.a) == 0 && Double.compare(b, this.b) == 0)

                || (Double.compare(a, this.b) == 0 && Double.compare(b, this.a) == 0)
                ;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}