package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    //Multiply X by Y non-negative integers only using bitwise operations, assignment operators, equality / boolean checks
	  long sum = 0;  
	  while (x != 0) { //While x > 0
		  if ((x & 1) != 0) { //If the lsb is a 1
			  sum = add(sum, y);
		  }
		  x >>>= 1; //Right shift x by 1 (HALVE IT)
		  y <<= 1; //Left shift y by 1  (DOUBLE IT)
	  }	
    return sum;
  }
  
  private static long add(long a, long b) { 
	  return b == 0 ? a : add(a ^ b, (a & b) << 1); //If Y is 0 return the sum, else: Return a recursive call of add( (a ^ b) + ((a & b) << 1) ) UNTIL b = 0
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
