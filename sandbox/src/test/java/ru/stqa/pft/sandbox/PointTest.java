package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testPoint(){
    Point p = new Point(2,5,9,4);
    Assert.assertEquals(p.distance(), 7.0710678118654755);
    Assert.assertNotEquals(p.distance(), 7);

  }
}
