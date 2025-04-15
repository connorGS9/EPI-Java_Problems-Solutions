package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
public class RectangleIntersection {
  @EpiUserType(ctorParams = {int.class, int.class, int.class, int.class})
  public static class Rect {
    int x, y, width, height;

    public Rect(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Rect rectangle = (Rect)o;

      if (x != rectangle.x) {
        return false;
      }
      if (y != rectangle.y) {
        return false;
      }
      if (width != rectangle.width) {
        return false;
      }
      return height == rectangle.height;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + width;
      result = 31 * result + height;
      return result;
    }

    @Override
    public String toString() {
      return "[" + x + ", " + y + ", " + width + ", " + height + "]";
    }
  }
  @EpiTest(testDataFile = "rectangle_intersection.tsv")
  public static Rect intersectRectangle(Rect r1, Rect r2) {
    //Given two rectangles return the rectangle formed by their intersection if they have one otherwise return a negative rectangle (0,0,-1,-1)
	//We can directly compare the rectangles in constant time by using math expressions to examine the end-points of each rectangle
	if (!isIntersecting(r1, r2)) return new Rect(0,0,-1,-1);
	
    return new Rect(
    			Math.max(r1.x, r2.x), //Horizontal shift from origin of new rectangle
    			Math.max(r1.y, r2.y), //Vertical shift from origin of new rectangle
    			Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x, r2.x), //Width of new rectangle : Min(
    			Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y) //Height of new rectangle
    			);
  }
  
  private static boolean isIntersecting(Rect r1, Rect r2) {
	  return r1.x <= r2.x + r2.width &&  //The left edge of r1 is to the left of or at the right edge of r2
			 r1.x + r1.width >= r2.x &&  //The right edge of r1 is to the right of or at the left edge of r2
			 r1.y <= r2.y + r2.height && //The bottom edge of r1 is below or at the top edge of r2
			 r1.y + r1.height >= r2.y;   //The top edge of r1 is above or at the bottom edge of r2
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RectangleIntersection.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
