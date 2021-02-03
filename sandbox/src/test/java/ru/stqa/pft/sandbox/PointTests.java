package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testArea() {

    Point p1 = new Point(4.56, 4.9);
    Point p2 = new Point(2.11, 6.01);
    Assert.assertEquals(p1.distance(p2), 2.68972117514065,0.00001);

  }
}
