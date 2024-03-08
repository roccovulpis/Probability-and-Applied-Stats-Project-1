import java.util.ArrayList;
import java.util.Random;

public class Simulator {

	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> bench;

	public Simulator() {
		deck = new ArrayList<>();
		hand = new ArrayList<>();
		bench = new ArrayList<>();
	}

	public void buildDeck() {
		for (int i = 0; i < 10; i++) {
			deck.add(new Pokemon("default pokemon"));
		}
		for (int i = 0; i < 30; i++) {
			deck.add(new Trainer("default trainer"));
		}
		for (int i = 0; i < 20; i++) {
			deck.add(new Energy("default energy"));
		}
	}

	public void playNestBall() {
		//find all Pokemon
		ArrayList<Card> tempPokemon = new ArrayList<>();
		boolean done = false;
		int i = 0;
		while(i < deck.size()) {
			if(deck.get(i) instanceof Pokemon) {
				tempPokemon.add(deck.get(i)); //add to found Pokemon so far.
				deck.remove(i);
			}else {
				i++;
			}

		}
		System.out.println("Pokemon found so far: " + tempPokemon);
		System.out.println("Count: " + tempPokemon.size());

		Random rng = new Random();
		int saveRandomNumber = rng.nextInt(tempPokemon.size());
		bench.add(tempPokemon.get(saveRandomNumber));
		tempPokemon.remove(saveRandomNumber);

		for(int j = 0; j < tempPokemon.size(); j++) {
			deck.add(tempPokemon.get(j));
		}
	}

	public ArrayList<Card> getDeck(){
		return deck;
	}

	public ArrayList<Card> getHand(){
		return hand;
	}

	public ArrayList<Card> getBench(){
		return bench;
	}

	public void run() {
		buildDeck();
		playNestBall();
	}

}
