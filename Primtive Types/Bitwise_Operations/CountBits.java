package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class CountBits {
  @EpiTest(testDataFile = "count_bits.tsv")

  public static short countBits(int x) {
    // Counting the number of set bits in the number x
	  short numBits = 0; //Count num of bits in this num
	  //while (x != 0) {
		  //numBits += (x & 1); //Num bits += 1 if lsb is set or 0 if not set
		  //x >>>= 1; //Shift x right by one bit (get rid of lsb)
	  //}
	  
	 /*
	  * The above method is still pretty fast O(total number of bits: set or not set) O(32) for 32 bit ints 
	  * But there is a faster method known as Brian Kernighan’s Algorithm
	  * In BH's algo we will only iterate once per set bit O(total set bits) still O(32) in the worst case but if x < Integer.MAX_VALUE, it will be faster by 32 - (UNSET bits in x) iterations
	  */
	  while (x != 0) {
		  x &= (x - 1); //Remove rightmost set bit and increment nums 
		  numBits++;
	  }
    return numBits;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CountBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
