import java.util.ArrayList;
import java.util.Random;

public class ProfessorsResearch extends Trainer implements TrainerAction {

	public ProfessorsResearch(String name) {
		super("Professor's Research");
	}
	
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
