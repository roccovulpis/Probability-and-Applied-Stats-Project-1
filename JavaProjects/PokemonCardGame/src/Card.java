/**
 * Represents a card with a name. This class is designed to be  
 * used for a variety of Pokemon cards including Pokemon, Energy, and Trainers.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class Card {
	private final String name;
	
	/**
     * Constructs a new Card with the specified name.
     * 
     * @param name The name of the card. 
     */
	public Card(String name) {
		this.name = name;
	}
	
	/**
     * Returns the name of the card.
     * 
     * @return The name of the card.
     */
	public String getName() {
		return name;
	}
}
