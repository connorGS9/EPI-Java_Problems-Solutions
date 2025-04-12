package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    //Return whether x is a palindromic number : 101 is, 1001 is, 102001 is not
	//We need to continually compare the last and first digit of the number to ensure they are the same 
    //We know that x % 10 will give us the ones place, but how to get the largest? We can find the total number of digits by using rules of logs -> #digits = Floor(log10(x)) + 1
	//Log10(x) tells us how many times we need to multiply 10 by itself to equal x.. think of log2(x) in binary (how many times we have to multiply 2 by 2) to reach a number: 2,4,8,16,32,64,128,256 = 10, 100, 1000
	//Using this we won't have to turn the number into a string although the relative TC O(n) is the same this method will save us from O(n) space necessary for creating the string and iterating through it 
	if (x <= 0) {
		return x == 0; //0 is a palindrome, all negative numbers are not since the (-) counts and there's always only one
	}
	
	final int numDigits = (int)Math.floor(Math.log10(x)) + 1; //Log(x) + 1
	int MASK = (int)Math.pow(10,  numDigits-1); //Mask for reaching the most significant digit (10 ^ n-1)
	
	for (int i = 0; i < (numDigits / 2); i++) { //Iterate over half the digits since we are comparing left and right in same iteration
		int low = x % 10; //Low digit
		int high = x / MASK; //High digit
		if (high != low) return false; //Immediately return false if high and low are not the same
		x %= MASK; //Get rid of the highest digit by making x the remainder of the power of 10 with the same number of digits -> 4321 % 1000 = 321
		x /= 10; //Get rid of lowest digit by dividing by 10
		MASK /= 100; //Lower the mask by 1 power of 10 per number removed (2) 10^2 = 100
	}
    return true; //Return true if the number is a palindrome
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
