package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
	 final static int MASK_SIZE = 16;
	 final static int BIT_MASK = 0xffff; //0 1111 1111 1111 1111
	 static int[] precomputedReverse = new int[65536]; //2^16
	 
	 static void precomputeReverse() {
	        for (int i = 0; i < 65536; i++) {
	            int reversed = 0;
	            int num = i;
	            for (int j = 0; j < MASK_SIZE; j++) {
	                reversed = (reversed << 1) | (num & 1); // Shift and add the LSB
	                num >>= 1; // Right shift to process the next bit
	            }
	            precomputedReverse[i] = reversed; // Store the reversed 16-bit number
	        }
	 }
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
	  //Reverse the bits of a long x
	  
	  
	  /* brute force solution O(1) ~ O(64)
	   * int l = 63, r = 0;
	 	 while (l > r) {
		 if ( ((x >>> l) & 1) != ((x >>> r) & 1)) {
			 long bitmask = (1L << l) | (1L << r);
			 x ^= bitmask;
		 }
		 l++;
		 r++;
	 }
    return x;
	   */
	 
	  return ((long) precomputedReverse[(int) (x & BIT_MASK)] << (3 * MASK_SIZE) | //We will just immediately return the the bitwise or ( | ) value of each of the 4 16-bit partitions of the 64 bit long computed using the mask size and bit mask 
              	 (long) precomputedReverse[(int) ((x >>> MASK_SIZE) & BIT_MASK)] << (2 * MASK_SIZE) |
              	 (long) precomputedReverse[(int) ((x >>> (2 * MASK_SIZE)) & BIT_MASK)] << MASK_SIZE |
              	 (long) precomputedReverse[(int) (x >>> (3 * MASK_SIZE))]);
  }

  public static void main(String[] args) {
	  precomputeReverse();
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
