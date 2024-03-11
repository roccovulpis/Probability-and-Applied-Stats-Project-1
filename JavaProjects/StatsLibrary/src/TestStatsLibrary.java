import java.math.BigInteger;
import java.util.ArrayList;

public class TestStatsLibrary 
{

	public static void main(String[] args) {
		
		StatsLibrary test = new StatsLibrary();
		
		ArrayList<Double> testNumbers = new ArrayList<>();
		testNumbers.add(1.0);
		testNumbers.add(4.0);
		testNumbers.add(9.0);
		testNumbers.add(13.0);
		testNumbers.add(19.0);
		testNumbers.add(9.0);
		testNumbers.add(32.0);
		testNumbers.add(32.0);
		
		System.out.println("Numbers being tested");
		System.out.println(testNumbers);
		
		double testerResults = test.findMean(testNumbers);
		double testerResults2 = test.findMedian(testNumbers);
		double testerResults3 = test.findMode(testNumbers);
		double testerResults4 = test.findStandardDeviation(testNumbers);
		double testerResults5 = test.findVariance(testNumbers);
		
		BigInteger testerResults6 = test.findFactorial(4);
		BigInteger testerResults7 = test.findPermutation(9, 8);
		BigInteger testerResults8 = test.findCombination(9, 8);
		
		double testerResults9 = test.findConditionalProbability(.2, .5);
		double testerResults10 = test.findBayesTheorem(.2, .5, .3);
		double testerResults11 = test.findBinomial(8, .65, 5);
		double testerResults12 = test.findExpectedBinomial(8, .65);
		double testerResults13 = test.findVarianceBinomial(8, .65);
		
		double testerResults14 = test.findGeometric(10, 0.4);
		double testerResults15 = test.findExpectedGeometric(.5);
		double testerResults16 = test.findVarianceGeometric(.5);
		
		BigInteger testerResults17 = test.findHypergeometric(42, 12, 10, 4);
		double testerResults18 = test.findExpectedHGD(3, 4, 10);
		double testerResults19 = test.findVarianceHGD(3, 4, 10);
		
		double testerResults20 = test.findNegativeBinomial(22, 13, .8);
		double testerResults21 = test.findExpectedNBD(22, .8);
		double testerResults22 = test.findVarianceNBD(22, .8);
		
		double testerResults23 = test.findPoisson(5.3, 7);
		double testerResults24 = test.findChebyshev(9);
		
		
		System.out.println("Sorted test numbers");
		System.out.println(testNumbers);
		
		System.out.println("This is the average of test numbers: " + testerResults);
		System.out.println("This is the median of test numbers: " + testerResults2);
		System.out.println("This is the mode of test numbers: " + testerResults3);
		System.out.println("This is the standard deviation of test numbers: " + testerResults4);
		System.out.println("This is the variance of test numbers: " + testerResults5);
		
		System.out.println("This is the factorial of test numbers: " + testerResults6);
		System.out.println("This is the Permutation of test numbers: " + testerResults7);
		System.out.println("This is the Combination of test numbers: " + testerResults8);
		System.out.println("This is the Conditional Prob of A given B: " + testerResults9);
		System.out.println("This is the Bayes Prob of A given B: " + testerResults10);
		System.out.println("This is the calculation of binomial: " + testerResults11);
		System.out.println("This is the calculation of Expected Binomial: " + testerResults12);
		System.out.println("This is the calculation of Variance Binomial: " + testerResults13);
		
		System.out.println("This is the calculation of Geometric: " + testerResults14);
		System.out.println("This is the calculation of Expected Geometric: " + testerResults15);
		System.out.println("This is the calculation of Variance Geometric: " + testerResults16);
		
		System.out.println("This is the calculation of Hypergeometric: " + testerResults17);
		System.out.println("This is the calculation of Expected Hypergeometric Distribution: " + testerResults18);
		System.out.println("This is the calculation of Variance Hypergeometric Distribution: " + testerResults19);
		
		System.out.println("This is the calculation of Negative Binomial: " + testerResults20);
		
		System.out.println("This is the calculation of Expected Negative Binomial Distribution: " + testerResults21);
		System.out.println("This is the calculation of Variance Negative Binomial Distribution: " + testerResults22);
		
		System.out.println("This is the calculation of Poisson: " + testerResults23);
		System.out.println("This is the calculation of Chebyshev: " + testerResults24);
	
		
		
	}
	
}
