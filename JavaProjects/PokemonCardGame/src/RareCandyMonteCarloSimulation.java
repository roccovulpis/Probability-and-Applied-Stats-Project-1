import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RareCandyMonteCarloSimulation extends MonteCarloSimulationInit{

	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> prizePile;
	private ArrayList<Double> startingHandAndPrizePileProbabilities;
	private ArrayList<Integer> countOfGoodHandsAndPrizePiles;
	private int deckSize;
	private int numberOfRuns;
	private double percentageOfGoodHandsAndPrizePiles;

	public RareCandyMonteCarloSimulation() {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		prizePile = new ArrayList<Card>();
		startingHandAndPrizePileProbabilities  = new ArrayList<Double>();
		countOfGoodHandsAndPrizePiles = new ArrayList<Integer>();
		deckSize = 60;
	}

	public void drawMultipleHands(int numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
		int numberOfPokemonCards = 20;
		int numberOfEnergyCards = 59;
		int numberOfRareCandyCards = 1;
		for (int i = 0; i < 4; i++) {
			initilizeDeck(numberOfPokemonCards, numberOfEnergyCards, numberOfRareCandyCards);
			int goodOpeningHandAndPrizePile = 0;
			for (int j = 0; j < numberOfRuns; j++) {
				drawHand();
				drawPrizePile();
				if (evaluateOpeningHand() && evaluateOpeningPrizePile()) goodOpeningHandAndPrizePile++;
				resetDeck();
			}
			numberOfRareCandyCards ++;
			numberOfEnergyCards --;
			percentageOfGoodHandsAndPrizePiles = ((double) goodOpeningHandAndPrizePile / numberOfRuns) * 100;
			startingHandAndPrizePileProbabilities.add(percentageOfGoodHandsAndPrizePiles);
			countOfGoodHandsAndPrizePiles.add(goodOpeningHandAndPrizePile);
			writeToFile();
			percentageOfGoodHandsAndPrizePiles = 0;

		}
	}

	public void printProbabilites() {
		for (int i = 0; i < startingHandAndPrizePileProbabilities.size(); i++) {
			System.out.println("Out of " + numberOfRuns + " runs " + (i+1) + " Rare Candy in the deck, there was a good opening hand "
					+ "and prize pile " + countOfGoodHandsAndPrizePiles.get(i) + " times");
			System.out.println("Therefore, the probability of having a valid opening hand and prize pile is: "
					+ startingHandAndPrizePileProbabilities.get(i) + "%");
			System.out.println();
		}
	}

	public void writeToFile() {

		String header = "Number of Rare Candy,Count of Good Hands and Prize Piles,Number of Runs,Probabilities of Good Starting Hands and Prize Piles";

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter("Rare_Candy_Probabilities.csv"));
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

	public void run(int numberOfRuns) {
		drawMultipleHands(numberOfRuns);
		printProbabilites();
	}

}
