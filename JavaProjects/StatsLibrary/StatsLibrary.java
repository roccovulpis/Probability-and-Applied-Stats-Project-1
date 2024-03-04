/**
 * This class provides various statistical methods to calculate mean, median, mode, 
 * standard deviation, variance, union, intersection, complement, factorial, 
 * permutation, combination, conditional probability, Bayes theorem, binomial, 
 * geometric, hypergeometric, Poisson, and Chebyshev.
 * 
 * @author Rocco Vulpis
 * @version 10/30/23
 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class StatsLibrary {

	// Default constructor
	public StatsLibrary() {

	}

	// Nonempty Constructor
	public StatsLibrary(String input) {

	}

	public StatsLibrary(double input) {

	}

	// Mean Method
	public double findMean(ArrayList<Double> userInputNumbers) {
		double sum = 0;

		for (double singleElement : userInputNumbers) {
			sum = sum + singleElement;
		}

		// storing value for clarity
		double result = sum / userInputNumbers.size();
		return result;
	}

	// Median Method
	public double findMedian(ArrayList<Double> userInputNumbers) {
		double median = 0;
		userInputNumbers = sort(userInputNumbers);

		// Checks for median if the size of the ArrayList is even
		int firstMiddleIndex = (userInputNumbers.size() / 2) - 1;
		int secondMiddleIndex = firstMiddleIndex + 1;
		if (userInputNumbers.size() % 2 == 0
				&& userInputNumbers.get(firstMiddleIndex) != userInputNumbers.get(secondMiddleIndex)) {
			median = (userInputNumbers.get(firstMiddleIndex) + userInputNumbers.get(secondMiddleIndex)) / 2;
		}

		// Checks for median if the size of the ArrayList is odd
		if (userInputNumbers.size() % 2 == 1) {
			int halfOfArrayList = userInputNumbers.size() / 2;
			median = userInputNumbers.get(halfOfArrayList);
		}

		return median;
	}

	// Mode Method
	public double findMode(ArrayList<Double> userInputNumbers) {
		double mode = 0;
		int countOfMode = 0;

		// Iterates through the entire ArrayList
		for (int i = 0; i < userInputNumbers.size(); i++) {
			int count = 0;

			double currentNumber = userInputNumbers.get(i);
			for (int j = 0; j < userInputNumbers.size(); j++) {
				if(userInputNumbers.get(j) == currentNumber) {
					count++;
				}
				if(count > countOfMode) {
					countOfMode = count;
					mode = currentNumber;
				}
			}
		}

		return mode;
	}

	// Standard Deviation Method
	public double findStandardDeviation(ArrayList<Double> userInputNumbers) {

		double mean = 0;
		double deviationFromMean = 0;
		double sum = 0;
		double sumDivided = 0;
		double standardDeviation = 0;
		ArrayList<Double> distancesToMeanSquared = new ArrayList<>();

		// Store the mean of the ArrayList
		mean = findMean(userInputNumbers);

		// Subtracts each index in the list by the mean and raises that value to the second power
		for (int i = 0; i < userInputNumbers.size(); i++) {
			deviationFromMean = userInputNumbers.get(i) - mean;
			double deviationFromMeanSquared = Math.pow(deviationFromMean, 2);
			distancesToMeanSquared.add(deviationFromMeanSquared);
		}

		// Calculates the sum of the values in the new list
		for (int i = 0; i < distancesToMeanSquared.size(); i++) {
			sum = sum + distancesToMeanSquared.get(i);
		}

		// Divides the sum by one less than the number of elements in the list 
		sumDivided = sum / (distancesToMeanSquared.size()-1);

		// Takes the square root of the sum to calculate the standard deviation
		standardDeviation = Math.sqrt(sumDivided);

		return standardDeviation;
	}

	// Variance Method
	public double findVariance(ArrayList<Double> userInputNumbers) {

		double standardDeviation = findStandardDeviation(userInputNumbers);
		double variance = Math.pow(standardDeviation, 2);

		return variance;
	}

	// Union Method
	public ArrayList<Integer> findUnion(ArrayList<Integer> firstUserInput, ArrayList<Integer> secondUserInput) {

		ArrayList<Integer> combinedInput = new ArrayList<>();

		for (int i = 0; i < firstUserInput.size(); i++) {
			combinedInput.add(firstUserInput.get(i));
		}

		for (int i = 0; i < secondUserInput.size(); i++) {
			combinedInput.add(secondUserInput.get(i));
		}

		ArrayList<Integer> unionOfInput = new ArrayList<>();

		for (Integer element : combinedInput) {
			if (!unionOfInput.contains(element)) {
				unionOfInput.add(element);
			}
		}

		return unionOfInput;
	}

	// Intersection Method
	public ArrayList<Integer> findIntersection(ArrayList<Integer> firstUserInput, ArrayList<Integer> secondUserInput) {

		ArrayList<Integer> intersectionOfInput = new ArrayList<>();

		for (Integer element : firstUserInput) {
			if (secondUserInput.contains(element)) {
				intersectionOfInput.add(element);
			}
		}

		return intersectionOfInput;
	}

	// Complement Method
	public ArrayList<Integer> findComplement(ArrayList<Integer> universalSet, ArrayList<Integer> subset) {

		ArrayList<Integer> complement = new ArrayList<>(universalSet);

		for (Integer element : subset) {
			if (complement.contains(element)) {
				complement.remove(element);
			}
		}

		return complement;
	}

	// Factorial Method
	public BigInteger findFactorial(int n) {

		BigInteger factorial = new BigInteger(Integer.toString(n));
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1"); 

		if (factorial.equals(zero)) {
			factorial = one;
		}
		else {
			for (int i = n; i > 1; i--) {
				BigInteger index = new BigInteger(Integer.toString(i)); 
				BigInteger oneLessThanIndex = index.subtract(one);
				factorial = factorial.multiply(oneLessThanIndex);
			}
		}

		return factorial;

	}

	// Permutation Method
	public BigInteger findPermutation(int n, int r) {

		BigInteger factorialOfN = findFactorial(n);
		int differenceOfNandR = n - r;
		BigInteger factorialOfDifference = findFactorial(differenceOfNandR);
		BigInteger permutation = factorialOfN.divide(factorialOfDifference);

		return permutation;
	}

	// Combination Method
	public BigInteger findCombination(int n, int r) {

		BigInteger factorialOfN = findFactorial(n);
		BigInteger factorialOfR = findFactorial(r);
		int differenceOfNandR = n - r;
		BigInteger factorialOfDifference = findFactorial(differenceOfNandR);
		BigInteger productOfDenominator = factorialOfDifference.multiply(factorialOfR);
		BigInteger combination = factorialOfN.divide(productOfDenominator);

		return combination;
	}

	// Conditional Probability Method
	public double findConditionalProbability(double probOfAandB, double probOfB) {

		double probOfAGivenB = probOfAandB / probOfB;

		return probOfAGivenB;
	}

	//Bayes Theorem Method 
	public double findBayesTheorem(double probOfBGivenA, double probOfA, double probOfB) {

		double probOfAGivenB = (probOfBGivenA * probOfA) / probOfB;

		return probOfAGivenB;
	}

	//	// Determining Independence Method
	//		public boolean determineIndependence(double probOfA, double probOfB, double probOfAandB) {
	//
	//		
	//
	//			return dependence;
	//		}

	// Binomial method
	public double findBinomial(int n, double p, int x) {

		BigInteger nFactorial = findFactorial(n);
		BigInteger xFactorial = findFactorial(x);
		BigInteger differenceOfNandXFactorial = findFactorial(n - x);
		double pRaisedTox = Math.pow(p, x);
		double oneMinuspRaised = Math.pow((1 - p), (n - x));
		BigInteger binomialCoefficient = nFactorial.divide(xFactorial.multiply(differenceOfNandXFactorial));
		double binomialProbability = binomialCoefficient.doubleValue() * pRaisedTox * oneMinuspRaised;

		return binomialProbability;
	}

	public double findExpectedBinomial(int n, double p) {
		double expected = n*p;
		return expected;
	}

	public double findVarianceBinomial(int n, double p) {
		double variance = n*p*(1-p);
		return variance;
	}

	//Geometric Method
	public double findGeometric(int n, double p) {
		double geometricDistribution = p * Math.pow(1 - p, n - 1);
		return geometricDistribution;
	}
	
	public double findExpectedGeometric(double p) {
		return 1/p;
	}
	
	public double findVarianceGeometric(double p) {
		double variance = (1-p)/Math.pow(p, 2);
		return variance;
	}

	//Hypergeometric Method
	public BigInteger findHypergeometric(int N, int K, int n, int k) {

		BigInteger factorialOfK = findFactorial(K);
		BigInteger factorialOfn = findFactorial(n);
		BigInteger factorialOfk = findFactorial(k);
		BigInteger factorialOfN = findFactorial(N);
		int nMinusK = n - k;
		int NMinusn = N - n;
		int Kminusk = K - k;
		int NminusKMinusnPlusk = N - K - n + k;
		BigInteger nMinusKFactorial = findFactorial(nMinusK);
		BigInteger NMinusnFactorial = findFactorial(NMinusn);
		BigInteger KMinuskFactorial = findFactorial(Kminusk);
		BigInteger nMinuskFactorial = findFactorial(nMinusK);
		BigInteger NminusKMinusnPluskFactorial = findFactorial(NminusKMinusnPlusk);
		BigInteger numerator =  factorialOfK.multiply(nMinusKFactorial).multiply(factorialOfn).multiply(NMinusnFactorial);
		BigInteger denominator = factorialOfN.multiply(factorialOfK).multiply(KMinuskFactorial).multiply(nMinuskFactorial).multiply(nMinuskFactorial).multiply(NminusKMinusnPluskFactorial);
		BigInteger hypergeometricDistribution = numerator.divide(denominator);

		return hypergeometricDistribution;
	}
	
	public double findExpectedHGD(int n, int r, int N) {
		return (n*r)/(double)N;
	}
	
	public double findVarianceHGD(int n, int r, int N) {
		double variance = (n)*(r/N)*((N-r)/(N))*((N-n)/(N-1));
		return variance;
	}

	//Poisson Method
	public double findPoisson(double l, int x) {

		double e = 2.718281828459045;
		double factorialOfx = findFactorial(x).doubleValue();
		double numerator = (Math.pow(e, -l)) * (Math.pow(l, x));
		double poisson = numerator / factorialOfx;

		return poisson;
	}

	//	public double findNegativeBinomial() {
	//		
	//	}

	//Chebyshev Method
	public double findChebyshev(int k) {

		double chebyshev = 1 - (1 / Math.pow(k, 2));

		return chebyshev;
	}


	// ArrayList Sorting Method 
	public ArrayList<Double> sort(ArrayList<Double> userInputNumbers){

		//Sorting the ArrayList elements from smallest to largest 
		for (int i = 0; i < userInputNumbers.size()-1; i++) { 
			for (int j = 0; j < userInputNumbers.size()-i-1; j++) { 
				if (userInputNumbers.get(j) > userInputNumbers.get(j + 1)) { 
					double temp = userInputNumbers.get(j);
					userInputNumbers.set(j, userInputNumbers.get(j + 1)); userInputNumbers.set(j + 1, temp); 
				} 
			} 
		}

		return userInputNumbers; 
	}



}
