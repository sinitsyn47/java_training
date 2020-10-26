package ru.stqa.pft.sandbox;

public class MyProgramm {

  public static void main(String[] args) {
    hello("world");
    hello("Sergey");
    hello("user");

    Square q = new Square(7); //задаем объект
    System.out.println("Площадь квадрата со стороной " + q.r + " = " + q.area());

    Rectangle r = new Rectangle(6,8); //задаём объект
    System.out.println("Площадь промоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(2,5);
    Point p2 = new Point(9,4);
    System.out.println("Расстояние между точками " + "P1 (" + p1.x + "," + p1.y + ") и P2 (" + p2.x + "," + p2.y + ") на двумерной плоскости равна " + p1.distance(p2) );
  }

  public static void hello(String word) {
    System.out.println("Hello, " + word + "!");
  }



}
