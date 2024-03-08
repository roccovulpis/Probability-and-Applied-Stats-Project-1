import java.util.ArrayList;
import java.util.Random;

public class ProfessorsResearch extends Trainer implements TrainerAction {

	public ProfessorsResearch(String name) {
		super("Professor's Research");
	}
	
	@Override
	public void playable(Simulator gameState) {
		ArrayList<Card> deck = gameState.getDeck();
		ArrayList<Card> hand = gameState.getHand();
		
		hand.clear();
		
		for(int i = 0; i < 7; i++) {
			hand.add(deck.get(0));
			deck.remove(0);
		}
		
	}
	
}
