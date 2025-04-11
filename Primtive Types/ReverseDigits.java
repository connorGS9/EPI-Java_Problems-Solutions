package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    //Reverse the digits of an integer x: 431 -> 134, 155550 -> 055551
	  long result = 0; //Store result, starts as 0 
	while (x != 0) {
		result = result * 10 + (x % 10); //Append rightmost digit of x (x % 10) to (result * 10) to ensure correct place (ones, tens, hundreds, etc..)
		x /= 10; //Divide x by 10 to remove rightmost digit 
	}
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
