package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // A method to find parity of a 
	 //The most efficient method for 32 bit integer parity. By using xor on itself after shifting the parity after shifting the number by 1 will be the LSB found by using (x & 1)  (x & 0x1) in hexa
	 x ^= x >>> 63;
	 x ^= x >>> 32; //x = 32,445 : (15 bits) 0 111 1110 1011 1101 ^= 0000 0000 0000 0000 0000 0000 00000 = No change in x
	 x ^= x >>> 16; // x = 32,445 : 0 111 1110 1011 1101 ^= 0000 0000 0000 0000 = No change in x
	 x ^= x >>> 8; //x = 32,445 : 0 0111 1110 1011 1101 ^= 0000 0000 1111 1110 = 0 0111 1110 0100 0011
	 x ^= x >>>4; //x = 32,467 : 0 0111 1110 0100 0011 ^= 0 0000 0111 1110 0100 = 0 0111 1001 1010 0111
	 x ^= x >>>2; //x = 32,498 : 0 0111 1001 1010 0111 ^= 0 0001 1110 0110 1001 = 0 0110 0111 1100 0000
	 x ^= x >>>1; //x = 26,424 : 0 0110 0111 1100 0000 ^= 0 0011 0011 1111 0000 = 0 0101 0100 0011 0000
	 return (short)(x&1); //x = 21,648 : 0 0101 0100 0011 0000 & 0 0000 0000 0000 0001 = 0
	 
	 /*
	  * The brute force method: O(num bits) 
	  * short parity = 0;
	  * while(x != 0) {
	  * parity ^= (x & 1);
	  * x >>> 1;
	  *  }
	  *  return parity
	  *  
	  *  
	  * First Optimization 
	  * while (x != 0) {
	  *    result ^= 1;
	  *    x &= (x-1);
	  *    }
	  * return result
	  * 
	  * Second Optimization Cache with a 2^16 array for 16 bit words and use a bitwise & with a BITMASK
	  * 
	  * ((CREATE ARRAY FUNCTION))
	  * return (short) arr[(int)((x >>> (3 * MASK_SIZE)) & BITMASK)] ^ arr[(int)((x >>> (2 * MASK_SIZE)) & BITMASK)] ^ arr[(int)((x >>> (1 * MASK_SIZE)) & BITMASK)] ^ arr[(int) (x & BITMASK)];
	  */
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
