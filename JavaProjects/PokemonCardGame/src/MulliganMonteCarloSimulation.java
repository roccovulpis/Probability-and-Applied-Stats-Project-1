import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The MulliganMonteCarloSimulation class simulates the probability of drawing a good 
 * starting hand using using a Monte Carlo simulation. 
 * It generates a deck of Pokemon and Energy cards, simulates multiple draws to evaluate the probability of drawing a good hand, 
 * and writes the results to a CSV file.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class MulliganMonteCarloSimulation extends MonteCarloSimulationInit{

	// Fields 
	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Double> handProbabilities;
	private int deckSize;
	private double percentageOfGoodHands;

	/**
     * Constructor that initializes the deck and hand as empty lists, sets the initial deck size to 60,
     * and initializes the list to hold hand probabilities.
     */
	public MulliganMonteCarloSimulation() {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		handProbabilities  = new ArrayList<Double>();
		// Default size of the deck.
		deckSize = 60;
	}

	/**
     * Simulates drawing multiple hands from the deck to calculate the probability
     * of getting a good hand depending on the deck composition.
     *
     * @param numberOfRuns the number of simulation runs for each deck composition.
     */
	public void drawMultipleHands(int numberOfRuns) {
		// Starting number of Pokemon cards. 
		int numberOfPokemonCards = 1;
		// Starting number of Energy cards. 
		int numberOfEnergyCards = 59;
		// Starting number of Trainer cards. 
		int numberOfTrainerCards = 0;
		for (int i = 0; i < deckSize; i++) {
			initilizeDeck(numberOfPokemonCards, numberOfEnergyCards, numberOfTrainerCards);
			int goodHandCount = 0;
			for (int j = 0; j < numberOfRuns; j++) {
				drawHand();
				// Counts if the hand is considered good.
				if (evaluateOpeningHand()) goodHandCount++;
				// Resets the deck for the next draw.
				resetDeck();
			}
			numberOfPokemonCards ++;
			numberOfEnergyCards --;
			// Calculates and stores the percentage of good hands.
			percentageOfGoodHands = ((double) goodHandCount / numberOfRuns) * 100;
			handProbabilities.add(percentageOfGoodHands);
			writeToFile();
			// Resets for the next deck configuration.
			percentageOfGoodHands = 0;
		}
	}

	/**
     * Prints the probabilities of having a valid starting hand for each deck composition.
     */
	public void printProbabilites() {
		for (int i = 0; i < deckSize; i++) {
			System.out.println("With " + (i+1)  + " Pokemon in the deck, the probability of having a valid starting hand is: "
		                        + handProbabilities.get(i) + "%");
		}
	}

	/**
     * Writes the calculated probabilities to a CSV file named "Mulligan_Probabilties.csv".
     * Each line in the file contains the probability of drawing a good hand for a specific deck composition.
     */
	public void writeToFile() {

		String header = "Probabilities";

		try {
			// Writes to a file named "Mulligan_Probabilties.csv" in the default workspace directory.
			BufferedWriter writer = new BufferedWriter(new FileWriter("Mulligan_Probabilties.csv"));
			//Writes the header to the CSV file outside of the loop.
			writer.write(header);

			for(int i = 0; i < handProbabilities.size(); i++) {
				writer.write("\n");
				writer.write(handProbabilities.get(i).toString() + "%");
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Runs the simulation for a specified number of runs, prints the results to standard output,
     * and writes the results to a file.
     *
     * @param numberOfRuns the number of simulation runs to perform.
     */
	public void run(int numberOfRuns) {
		drawMultipleHands(numberOfRuns);
		printProbabilites();
	}

}
