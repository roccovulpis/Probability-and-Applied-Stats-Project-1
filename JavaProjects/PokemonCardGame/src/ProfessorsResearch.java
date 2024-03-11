import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the Professor's Research Trainer card. 
 * This card allows the player to discard their current hand and draw a new hand of seven cards from their deck.
 * 
 * This class extends the Trainer class and implements the TrainerAction interface
 * to create the specific playable action of discarding and drawing cards.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class ProfessorsResearch extends Trainer implements TrainerAction {

	public ProfessorsResearch(String name) {
		super("Professor's Research");
	}
	
	/**
     * Executes the action of Professor's Research. When this card is played the player's
     * current hand is discarded and a new hand of seven cards is drawn from the deck. 
     * This method modifies both the player's hand and deck.
     * 
     * @param player The player who plays Professor's Research card.
     */
	@Override
	public void playable(Player player) {
		ArrayList<Card> deck = player.getDeck();
		ArrayList<Card> hand = player.getHand();
		
		hand.clear();
		
		for(int i = 0; i < 7; i++) {
			hand.add(deck.get(0));
			deck.remove(0);
		}
		
	}
	
}
