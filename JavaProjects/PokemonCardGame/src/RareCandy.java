/**
 * Represents the Rare Candy Trainer card. 
 * This card allows the player to speed up the evolution of a Pokemon.
 * 
 * This class extends the Trainer class and implements the TrainerAction interface to create the specific 
 * playable action as follows: 
 * 
 * Chooses 1 Basic Pokemon in play. 
 * If it's a Stage 2 card in the hand that evolves from that Pokemon, put that card onto the Basic Pokemon to evolve it, 
 * skipping the Stage 1. The player can’t use this card during their first turn or on a Basic Pokemon that was put into play this turn.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class RareCandy extends Trainer implements TrainerAction{

	public RareCandy() {
		super("Rare Candy");
	}

}
