/**
 * Represents a Trainer with a name, 
 * This class extends the Card class and implements the TrainerAction interface.
 * This enables it to perform actions like manipulating the user's current deck.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class Trainer extends Card implements TrainerAction{

	public Trainer(String name) {
		super(name);
	}

	@Override
	public void playable(Player player) {
		
	}
	
}
