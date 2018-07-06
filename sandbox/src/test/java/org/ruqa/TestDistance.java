package org.ruqa;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDistance {
  @Test
  public void testDistance() {
    Point p1 = new Point(10,10);
    Point p2 = new Point(20,20);
    Assert.assertEquals(p1.distance(p2), 14.1421, 0.01);
  }
  @Test
  public void testDistance2() {
    Point p1 = new Point(10,10);
    Point p3 = new Point(10, 20);
    Assert.assertEquals(p1.distance(p3), 10, 0.01);
  }
  @Test
  public void testDistance3() {
    Point p1 = new Point(10,10);
    Point p4 = new Point(13, 14);
    Assert.assertEquals(p1.distance(p4), 5, 0.01);
  }
  @Test
  public void testDistance4() {
    Point p1 = new Point(10,10);
    Point p5 = new Point(20, 10);
    Assert.assertEquals(p1.distance(p5), 10, 0.01);
  }
}
