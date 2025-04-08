package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    //Divide two non-negative integers x and y only using addition, subtraction, and bit shifting 
	  //BF solution very slow O(x/y) with potentially x # of iterations if y = 1 (worst case) 
	  //int ans = 0;
	  //while (x >= y) {
		 // x -= y;
		 // ans++;
	  //}
    //return ans;
  
  
  //Faster bitwise solution using shifting and adding ideas of PrimitiveMultiply.java example 
  //Find largest k such that (2^k * y) <= x  we can then subtract 2^k * y from x and add 2^k to the quotient
  //Takes O(log(x)) time due to halving yPower in each iteration until the closest power of 2 <= x is found FOR EACH x after subtracting that power of 2 * y (multiple of y)
	  int result = 0; 
	  int power = 32; //Taking in integers which are a max of 2^32 and STARTING from 32 down to 0
	  long yPower = (long)y << power; //yPower = y * 2^k. long to avoid int overflow 
	  while (x >= y) { //As long as there is still as least 1 y left in x
		  while (yPower > x) { //While (y * 2^k) is > x
			  yPower >>>= 1; //Divide yPower by 2
			  --power; //Until we find the right power of 2 (k) that satisfies (2^k *y) <= x we decrement from 32
		  }
		  
		  result += 1 << power; //once we find the correct k and break from the inner loop, add 2^k to the quotient
		  x -= yPower; //Subtract (2^k * y) from x 
  		}
	  return result;
  	}

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
