package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

  @Test
  public void testArea(){
    Square r = new Square(7);
    Assert.assertEquals(r.area(), 40.0);
  }
}
