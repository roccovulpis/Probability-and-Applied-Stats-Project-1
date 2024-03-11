import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MonteCarloSimulationInit {

	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> prizePile;
	private ArrayList<Card> discardPile;
	private int deckSize;

	public MonteCarloSimulationInit() {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		prizePile = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
		deckSize = 60;
	}

	public void initilizeDeck(int numberOfPokemonCards, int numberOfEnergyCards, int numberOfRareCandyCards) {
		deck.clear();
		for(int i = 0; i < numberOfPokemonCards; i++) {
			deck.add(new Pokemon("default pokemon"));
		}
		for(int i = 0; i < numberOfEnergyCards; i++) {
			deck.add(new Energy("default energy"));
		}
		for(int i = 0; i < numberOfRareCandyCards; i++) {
			deck.add(new RareCandy());
		}
		Collections.shuffle(deck);
	}

	public Card drawCard() {
		Random rng = new Random();
		int cardIndex = rng.nextInt(deck.size());
		Card drawnCard = deck.get(cardIndex);
		deck.remove(cardIndex);
		return drawnCard;
	}

	public void drawHand() {
		for(int i = 0; i < 7; i++) {
			hand.add(drawCard());
		}
	}
	
	public void drawPrizePile() {
		for(int i = 0; i < 6; i++) {
			prizePile.add(drawCard());
		}
	}

	public boolean evaluateOpeningHand() {
	    for(int i = 0; i < hand.size(); i++) {
	        Card currentCard = hand.get(i);
	        if(currentCard instanceof Pokemon) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean evaluateOpeningPrizePile() {
	    for(int i = 0; i < prizePile.size(); i++) {
	        Card currentCard = prizePile.get(i);
	        if(currentCard instanceof RareCandy) {
	            return true;
	        }
	    }
	    return false;
	}

	public void resetDeck() {
		deck.addAll(hand);
		deck.addAll(prizePile);
		hand.clear();
		prizePile.clear();
		Collections.shuffle(deck);
	}

}
