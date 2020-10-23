package ru.stqa.pft.sandbox;

public class Square {
  public double r;

  //конструктор
  public Square(double r) {
    this.r = r;
  }

  //метод из функции
  public double area() {
      return this.r * this.r;
    }


}
