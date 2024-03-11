import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MulliganMonteCarloSimulation extends MonteCarloSimulationInit{

	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Double> handProbabilities;
	private int deckSize;
	private double percentageOfGoodHands;

	public MulliganMonteCarloSimulation() {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		handProbabilities  = new ArrayList<Double>();
		deckSize = 60;
	}

	public void drawMultipleHands(int numberOfRuns) {
		int numberOfPokemonCards = 1;
		int numberOfEnergyCards = 59;
		int numberOfTrainerCards = 0;
		for (int i = 0; i < deckSize; i++) {
			initilizeDeck(numberOfPokemonCards, numberOfEnergyCards, numberOfTrainerCards);
			int goodHandCount = 0;
			for (int j = 0; j < numberOfRuns; j++) {
				drawHand();
				if (evaluateOpeningHand()) goodHandCount++;
				resetDeck();
			}
			numberOfPokemonCards ++;
			numberOfEnergyCards --;
			percentageOfGoodHands = ((double) goodHandCount / numberOfRuns) * 100;
			handProbabilities.add(percentageOfGoodHands);
			writeToFile();
			percentageOfGoodHands = 0;
		}
	}

	public void printProbabilites() {
		for (int i = 0; i < deckSize; i++) {
			System.out.println("With " + (i+1)  + " Pokemon in the deck, the probability of having a valid starting hand is: "
		                        + handProbabilities.get(i) + "%");
		}
	}

	public void writeToFile() {

		String header = "Probabilities";

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter("Mulligan_Probabilties.csv"));
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

	public void run(int numberOfRuns) {
		drawMultipleHands(numberOfRuns);
		printProbabilites();
	}

}
