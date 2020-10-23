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

  double x1 = 2;
  double y1 = 5;
  double x2 = 4;
  double y2 = 15;
    System.out.println("Расстояние между точками " + "P1 (" + x1 + "," + y1 + ") и P2 (" + x2 + "," + y2 + ") равна " + distance(x1,y1,x2,y2) );
  }

  public static void hello(String word) {
    System.out.println("Hello, " + word + "!");
  }

  public static double distance(double x1, double y1, double x2, double y2){
    double sq =  ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
    return Math.sqrt(sq);
  }


}
