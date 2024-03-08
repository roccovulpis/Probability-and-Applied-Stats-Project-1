import java.util.ArrayList;
import java.util.Random;

public class NestBall extends Trainer{
	
	public NestBall(String name) {
		super("NestBall");
	}

	@Override
	public void playable(Simulator gameState) {
		ArrayList<Card> deck = gameState.getDeck();
		ArrayList<Card> bench = gameState.getBench();
		
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
			deck.add(tempPokemon.get(i));
		}
	}
}
