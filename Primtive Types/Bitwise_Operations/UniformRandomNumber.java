package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class UniformRandomNumber {
  private static int zeroOneRandom() {
    Random gen = new Random();
    return gen.nextInt(2);
  }

  public static int uniformRandom(int lowerBound, int upperBound) {
	  //We are given a random number generator that can produce either a 0 or 1, how can we generate a random number between lowerBound and upperBound two integers?
	  //Using 0 and 1 we can create any binary number. We can note that an interval from [0, (b-a)] is equivalent since we can add a to the numbers in the interval to make them within range [a,b]
	  //Since we are using a RANDOM generator if we made two calls to the generator we could create any of ( (01) (10) (00) (11) ) (0,1,2,3) and adding 1 will make them within the range [1,4]
	  //If the bound can be represented as 2^i - 1 we can just use the above method ^ calling the generator i times and then add (lowerBound) to the number to always get a number within the range [lower, 2^i]
	  //But if the bound is not an exact power of 2, we can find the smallest 2^i such that it is larger than the upperBound to generate an i-bit number with A HIGH CHANCE of being within the range.
	  //Example: uniformRandom(3, 9) 2^2 < (b-a) (6) < 2^3 we will make 3 calls to the generator to create one 3 bit number [0-7] [000, 001, 010, 100, 011, 101, 110, 111] and then add lowerBound(3) to create a number
	  //within the range [3, 10] of the numbers generated: 7/8 will be within the range giving us a chance of successful generation within the range of 87.5%
	  //Since the generation events are independent if we generated the single number outside of the range (10) we can generate a new number which will also have an 87.5% chance of being within the range, making the
	  //Total chance of being correct across two generations: 1 - (1/8)^2 = 1 - 1/64 = 63/64 = 98.4%
	  //Generally the chance of generating a good number across n generations is: 1 - (chance of being incorrect)^n 
	  
	  int numberOutcomes = upperBound - lowerBound + 1;
	  int result;
	  
	  do {
		  result = 0; //Result starts as 0 at beginning of all series of random number generator calls 
		  for (int i = 0; (1 << i) < numberOutcomes; ++i) { //Iterate through powers of 2 (number of calls to generator iterations)
			  result = (result << i) | zeroOneRandom();	 //Result = past result + 0/1 added by the number generator : res= 001 (shift by 2: i = 2) -> 100 | 1 = 1001 if 1 is generated,  0100 | 0 = 0100 if 0 is generated
		  }
	  } while (result >= numberOutcomes); //While result is outside of the range, return to generate a new number 
	  
	  return result + lowerBound; //Result is definitely within range [0, b-a] Return number + lowerBound to get a number within the range [lower, upper]
  }
  private static boolean uniformRandomRunner(TimedExecutor executor,
                                             int lowerBound, int upperBound)
      throws Exception {
    List<Integer> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 100000; ++i) {
        results.add(uniformRandom(lowerBound, upperBound));
      }
    });

    List<Integer> sequence = new ArrayList<>();
    for (Integer result : results) {
      sequence.add(result - lowerBound);
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, upperBound - lowerBound + 1, 0.01);
  }

  @EpiTest(testDataFile = "uniform_random_number.tsv")
  public static void uniformRandomWrapper(TimedExecutor executor,
                                          int lowerBound, int upperBound)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> uniformRandomRunner(executor, lowerBound, upperBound));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "UniformRandomNumber.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
