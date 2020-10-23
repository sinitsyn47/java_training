package ru.stqa.pft.sandbox;

public class MyProgramm {

  public static void main(String[] args) {
    hello("world");
    hello("Sergey");
    hello("user");

    double x = 5;
    System.out.println("Площадь квадрата со стороной " + x + " = " + area(x));

    double a = 4;
    double b = 6;
    System.out.println("Площадь промоугольника со сторонами " + a + " и " + b + " = " + area(a, b));


  }

  public static void hello(String word) {
    System.out.println("Hello, " + word + "!");
  }

  public static double area(double x) {
    return x * x;
  }

  public static double area(double a, double b) {
    return a * b;
  }

}