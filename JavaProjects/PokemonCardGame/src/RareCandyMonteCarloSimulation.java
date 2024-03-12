import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The RareCandyMonteCarloSimulation class simulates the probability of drawing a good 
 * starting hand and prize pile using using a Monte Carlo simulation. 
 * It generates a deck of Pokemon, Energy, and "Rare Candy" cards, simulates multiple draws to evaluate the probability of drawing a good hand, 
 * and writes the results to a CSV file.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class RareCandyMonteCarloSimulation extends MonteCarloSimulationInit{

	// Fields 
	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> prizePile;
	private ArrayList<Double> startingHandAndPrizePileProbabilities;
	private ArrayList<Integer> countOfGoodHandsAndPrizePiles;
	private int deckSize;
	private int numberOfRuns;
	private double percentageOfGoodHandsAndPrizePiles;

	/**
	 * Constructor that initializes the deck, hand, and prize pile as empty lists, 
	 * sets the initial deck size to 60, and initializes the list to hold hand probabilities 
	 * along with the count.
	 */
	public RareCandyMonteCarloSimulation() {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		prizePile = new ArrayList<Card>();
		startingHandAndPrizePileProbabilities  = new ArrayList<Double>();
		countOfGoodHandsAndPrizePiles = new ArrayList<Integer>();
		// Default size of the deck.
		deckSize = 60;
	}

	/**
	 * Simulates drawing multiple hands from the deck to calculate the probability
	 * of getting a good hand and prize pile depending on the deck composition.
	 *
	 * @param numberOfRuns the number of simulation runs for each deck composition.
	 */
	public void drawMultipleHands(int numberOfRuns) {
		// Stores the specified number of runs in a global variable.
		this.numberOfRuns = numberOfRuns;
		// Starting number of Pokemon cards. 
		int numberOfPokemonCards = 20;
		// Starting number of Energy cards. 
		int numberOfEnergyCards = 39;
		// Starting number of Rare Candy cards. 
		int numberOfRareCandyCards = 1;
		for (int i = 0; i < 4; i++) {
			initilizeDeck(numberOfPokemonCards, numberOfEnergyCards, numberOfRareCandyCards);
			int goodOpeningHandAndPrizePile = 0;
			for (int j = 0; j < numberOfRuns; j++) {
				drawHand();
				drawPrizePile();
				// Counts if the hand and prize pile is considered good.
				if (evaluateOpeningHand() && evaluateOpeningPrizePile()) goodOpeningHandAndPrizePile++;
				resetDeck();
			}
			numberOfRareCandyCards ++;
			numberOfEnergyCards --;
			// Calculates and stores the percentage of good hands.
			percentageOfGoodHandsAndPrizePiles = ((double) goodOpeningHandAndPrizePile / numberOfRuns) * 100;
			startingHandAndPrizePileProbabilities.add(percentageOfGoodHandsAndPrizePiles);
			countOfGoodHandsAndPrizePiles.add(goodOpeningHandAndPrizePile);
			writeToFile();
			// Resets for the next deck configuration.
			percentageOfGoodHandsAndPrizePiles = 0;

		}
	}

	/**
	 * Prints the probabilities of having a valid starting hand and prize pile for each deck composition.
	 */
	public void printProbabilites() {
		for (int i = 0; i < startingHandAndPrizePileProbabilities.size(); i++) {
			System.out.println("Out of " + numberOfRuns + " runs " + (i+1) + " Rare Candy in the deck, there was a good opening hand "
					+ "and prize pile " + countOfGoodHandsAndPrizePiles.get(i) + " times");
			System.out.println("Therefore, the probability of having a valid opening hand and prize pile is: "
					+ startingHandAndPrizePileProbabilities.get(i) + "%");
			System.out.println();
		}
	}

	/**
	 * Writes the calculated probabilities to a CSV file named "Rare_Candy_Probabilities.csv".
	 * Each line in the file contains the probability of drawing a good hand for a specific deck composition.
	 */
	public void writeToFile() {
		// Header of the CSV. 
		String header = "Number of Rare Candy,Count of Good Hands and Prize Piles,Number of Runs,Probabilities of Good Starting Hands and Prize Piles";
		try {
			// Writes to a file named "Mulligan_Probabilties.csv" in the default workspace directory.
			BufferedWriter writer = new BufferedWriter(new FileWriter("Rare_Candy_Probabilities.csv"));
			// Writes the header to the CSV file outside of the loop.
			writer.write(header);
			for(int i = 0; i < startingHandAndPrizePileProbabilities.size(); i++) {
				writer.write("\n");
				String line = String.format("%d,%d,%d,%.2f%%", i + 1, countOfGoodHandsAndPrizePiles.get(i), numberOfRuns, 
						startingHandAndPrizePileProbabilities.get(i)); 
				writer.write(line);
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Runs the simulation for a specified number of runs, prints the results,
	 * and writes the results to a file.
	 *
	 * @param numberOfRuns the number of simulation runs to perform.
	 */
	public void run(int numberOfRuns) {
		drawMultipleHands(numberOfRuns);
		printProbabilites();
	}

}
