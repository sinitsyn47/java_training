package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testPoint(){
    Point p1 = new Point(2,5);
    Point p2 = new Point(9,4);
    Assert.assertEquals(p1.distance(p2), 7.0710678118654755);
    Assert.assertNotEquals(p1.distance(p2), 7);

  }
}
