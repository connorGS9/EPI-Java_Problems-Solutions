package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
  //Function to swap the ith and jth bits of a long number x
	  if ( ((x >>> i) & 1) != ((x >>> j) & 1)) { //If the bit at ith index and jth index are the same no reason to swap, so we shift the ith and jth bit to the rightmost position (x >>> i) and extract that bit (& 1) to compare
		  long bitmask = (1L << i) | (1L << j); //The bitmask will CREATE TWO DIFFERENT binary numbers one will have a 1 bit at index i and the other at j (1L (a 1 of type Long) << i (placed at index i)) | (bitwise or) with the j number to create a single number with 1 at index i and j
		  x ^= bitmask; //Xor with the bitmask and the bit at i and j will be flipped. EX: x = 5 (101) i = 0 j = 1.  BITMASK = (011) -> 5 ^ BITMASK = 110 with bits at index 0 and 1 flipped.
	  }
	  return x;
	  //O(1) always
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
