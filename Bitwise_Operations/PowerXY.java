package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    //Create a function that raises x to the power of y without built in functions or Math class
	//The brute force would be multiplying a number by itself y times but this can take O(2^n) where n = number of bits in integer y (32)
	//To lower the TC we can consider doing more per iteration... rather than multiplying by x; y times, we can use properties of binary to note that pow(x,y) will be x * x^(y/2)^2 if y is not a power of 2
	//Or pow(x,y) will be x^(y/2)^2 if y is a power of 2. Using this rule of exponentiation we can recursively or iteratively build the result very quickly ~ O(n)
	  double result = 1.0;
	  long power = y;
	  
	  if (y < 0) { //If y is a negative power
		  power = -power; //Would cause bad bitwise operations, we will adjust with 1/x instead
		  x = 1.0 / x; //Negative power = 1 / (non-negative equivalent)
	  }
	  while (power > 0) {
		  if ((power & 1) != 0) { //If the current power is not a power of 2 (LSB is 1)
			  result *= x; //Multiply current result by x
		  }
		  power >>>= 1; //Divide power by 2 
		  x *= x; //Square x
	  }
	  
      return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
