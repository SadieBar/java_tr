public class SandboxProgram {
  public static void main(String[] args) {
    Point p1 = new Point(10,10);
    Point p2 = new Point(20,20);
    System.out.println("Расстояние между двумя точками " + distance(p1,p2));
    System.out.println("Расстояние между двумя точками через метод " + p1.distance(p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.getX()-p2.getX(),2)+Math.pow(p1.getY()-p2.getY(),2));
  }
}