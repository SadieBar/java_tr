package org.ruqa;

public class Point {
  private double x;
  private double y;
  public Point(double x, double y) {
    setPoint(x, y);
  }
  public void setPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }
  public double getX() {
    return x;
  }
  public double getY() {
    return y;
  }
  public double distance(Point p2) {
    return Math.sqrt(Math.pow(this.getX()-p2.getX(),2)+Math.pow(this.getY()-p2.getY(),2));
  }
}
