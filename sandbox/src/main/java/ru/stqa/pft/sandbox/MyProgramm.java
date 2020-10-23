package ru.stqa.pft.sandbox;

public class MyProgramm {

  public static void main(String[] args) {
    hello("world");
    hello("Sergey");
    hello("user");

    Square q = new Square(7); //задаем объект
    System.out.println("Площадь квадрата со стороной " + q.r + " = " + q.area());

    Rectangle r = new Rectangle(5,7); //задаём объект
    System.out.println("Площадь промоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p = new Point(2,5,9, 4);
    System.out.println("Расстояние между точками плоскости " + "P1 (" + p.x1 + "," + p.y1 + ") и P2 (" + p.x2 + "," + p.y2 + ") равна " + p.distance() );
  }

  public static void hello(String word) {
    System.out.println("Hello, " + word + "!");
  }



}
