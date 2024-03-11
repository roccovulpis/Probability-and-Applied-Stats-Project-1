import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the Nest Ball Trainer card. 
 * This card allows the player to search their deck for a Pokemon and place it directly onto their bench.
 * 
 * This class extends the Trainer class and specifies the action to be performed
 * when the card is played.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class NestBall extends Trainer{
	
	/**
     * Constructs a Nest Ball Trainer card.
     *
     * @param name The name of the Trainer card.
     */
	public NestBall(String name) {
		super("NestBall");
	}

	/**
     * Executes the action of the Nest Ball card when played by a player. This method searches
     * the player's deck for Pokemon cards, randomly selects one, places it onto the player's bench,
     * and returns the rest to the deck.
     *
     * @param player The player playing the Nest Ball card.
     */
	@Override
	public void playable(Player player) {
		ArrayList<Card> deck = player.getDeck();
		ArrayList<Card> bench = player.getBench();
		
		// Find all Pokemon.
		ArrayList<Card> tempPokemon = new ArrayList<>();
		boolean done = false;
		int i = 0;
		while(i < deck.size()) {
			if(deck.get(i) instanceof Pokemon) {
				// Add to found Pokemon so far.
				tempPokemon.add(deck.get(i)); 
				deck.remove(i);
			}else {
				i++;
			}
			
		}
		System.out.println("Pokemon found so far: " + tempPokemon);
		System.out.println("Count: " + tempPokemon.size());
		
		// Randomly select one Pokemon from the found Pokemon and place it on the bench.
		Random rng = new Random();
		int saveRandomNumber = rng.nextInt(tempPokemon.size());
		bench.add(tempPokemon.get(saveRandomNumber));
		tempPokemon.remove(saveRandomNumber);
		
		// Return the remaining Pokemon back to the deck.
		for(int j = 0; j < tempPokemon.size(); j++) {
			deck.add(tempPokemon.get(i));
		}
	}
}
