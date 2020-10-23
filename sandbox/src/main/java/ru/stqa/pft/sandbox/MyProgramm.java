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


  }

  public static void hello(String word) {
    System.out.println("Hello, " + word + "!");
  }



}