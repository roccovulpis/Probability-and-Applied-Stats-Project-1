import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Represents a player in a Pokemon card game. This class manages the player's deck, hand, bench, prize pile, 
 * and discard pile, along with actions the player can perform during the game such as playing cards, drawing cards, 
 * and attacking.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class Player {

	// Fields 
	private final int playerNumber;
	private boolean isFirst;
	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> bench;
	private ArrayList<Card> prizePile;
	private ArrayList<Card> discardPile;
	private Pokemon active;
	Scanner kb;
	private int deckSize;
	private Player opponent;

	/**
	 * Constructs a new Player with a specific player number.
	 * Initializes deck, hand, bench, prize pile, discard pile, size of the deck, and scanner for input.
	 *
	 * @param playerNumber The unique number identifying the player.
	 */
	public Player(int playerNumber) {
		this.playerNumber = playerNumber;
		kb = new Scanner(System.in);
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		bench = new ArrayList<Card>();
		prizePile = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
		deckSize = 60;
		isFirst = false;
	}

	/**
	 * Determines if this player is set to go first in the game.
	 *
	 * @return true if this player is going first, false otherwise.
	 */
	public boolean isFirst(){
		return isFirst;
	}

	/**
	 * Sets whether this player is going first.
	 *
	 * @param isFirst true if this player is going first, false otherwise.
	 */
	public void setIsFirst(boolean isFirst){
		this.isFirst = isFirst;
	}

	/**
	 * Sets the opponent for this player.
	 *
	 * @param opponent The player's opponent.
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	/**
	 * Returns the player's prize pile.
	 *
	 * @return The player's prize pile as an ArrayList of Cards.
	 */
	public ArrayList<Card> getPrizePile() {
		return prizePile;
	}

	/**
	 * Returns the player's discard pile.
	 *
	 * @return The player's discard pile as an ArrayList of Cards.
	 */
	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}

	/**
	 * Gets the player's opponent.
	 *
	 * @return The player's opponent.
	 */
	public Player getOpponent() {
		return opponent;
	}

	/**
	 * Gets the player's active Pokemon.
	 *
	 * @return The player's active Pokemon.
	 */
	public Pokemon getActive() {
		return this.active;
	}

	/**
	 * Gets the active Pokemon of the player's opponent.
	 *
	 * @return The opponent's active Pokemon, or null if the opponent has no active Pokemon.
	 */
	public Pokemon getOpponentActivePokemon() {
		if (opponent != null) {
			return opponent.getActive();
		}
		return null;
	}

	/**
	 * Initializes the player's deck with a specified number of Pokemon, Energy, and Trainer cards.
	 * The deck is then shuffled.
	 *
	 * @param numberOfPokemonCards  The number of Pokemon cards to include in the deck.
	 * @param numberOfEnergyCards   The number of Energy cards to include in the deck.
	 * @param numberOfTrainerCards  The number of Trainer cards to include in the deck.
	 */
	public void initializeDeck(int numberOfPokemonCards, int numberOfEnergyCards, int numberOfTrainerCards) {
		deck.clear();
		for(int i = 0; i < (numberOfPokemonCards); i++) {
			if(i % 3 == 0){
				deck.add(new Pikachu());
			}
			else if (i % 3 == 1) {
				deck.add(new Bulbasaur());
			}
			else {
				deck.add(new Charmander());
			}
		}
		for(int i = 0; i < numberOfEnergyCards; i++) {
			if(i % 3 == 0){
				deck.add(new Energy("Fire"));
			}
			else if (i % 3 == 1) {
				deck.add(new Energy("Electric"));
			}
			else {
				deck.add(new Energy("Grass"));
			}
		}
		for(int i = 0; i < numberOfTrainerCards; i++) {
			deck.add(new Trainer("Research Docta"));
		}
		Collections.shuffle(deck);
	}

	/**
	 * Performs an attack from the player's active Pokemon to the specified opponent Pokemon.
	 *
	 * @param opponentPokemon The Pokemon being attacked.
	 */
	public void performAttack(Pokemon opponentPokemon) {
		if (this.active == null) {
			System.out.println("No active Pokemon to attack with.");
			return;
		}

		System.out.println("Choose an attack:\n1) " + active.getAttackOneName() + "\n2) " + active.getAttackTwoName());
		int attackChoice = kb.nextInt();
		switch (attackChoice) {
		case 1:
			active.attackOne(opponentPokemon);
			break;
		case 2:
			active.attackTwo(opponentPokemon);
			break;
		default:
			System.out.println("Invalid choice. Please select 1 or 2.");
			break;
		}
		if (opponentPokemon.getHp() == 0){
			System.out.println("Opponent's " + opponentPokemon.getName() + " has fainted!");
			this.opponent.killActive();
			Card prize = prizePile.remove(0);
			this.hand.add(prize);
			System.out.println(prize.getName() + " has been added to your hand from the Prize Pile.");
		}
	}

	/**
	 * Handles the actions a player can take on their first turn, including setting an active Pokemon and playing cards.
	 */
	public void firstTurn(){
		System.out.println("----------------Player " + playerNumber + "'s turn----------------");
		setActive();
		if (isFirst){
			boolean doneTurn = false;
			while (!doneTurn){
				playCard(false);
				System.out.println("1) End turn 2) Play another card");
				int response = kb.nextInt();
				if (response == 1) doneTurn=true;
				else continue;
			}

		}
		else
			turn(false);
	}

	/**
	 * Manages the actions a player can take during their turn, including drawing a card if specified,
	 * choosing an attack or to play a card, and handling the end of the turn.
	 * 
	 * @param draw Indicates whether the player should draw a card at the beginning of their turn.
	 */
	public void turn(boolean draw){
		System.out.println("----------------Player " + playerNumber + "'s turn----------------");
		Scanner kb = new Scanner(System.in);
		if (draw) hand.add(drawCard());
		viewHand();

		while (active == null) {
			setActive();
		}

		printActive();
		boolean doneTurn = false;
		while (!doneTurn){
			System.out.println("What would you like to do?\n1) Attack 2) Play a card");
			int choice = kb.nextInt();
			if (choice == 1) {
				if (this.opponent.getActive()==null){
					System.out.println("Your opponent has no active pokemon yet!");
					return;
				}
				Pokemon opponentPokemon = getOpponentActivePokemon();
				performAttack(opponentPokemon);
				doneTurn = true;
			} else if (choice == 2) {
				playCard(true);
			} else {
				System.out.println("Invalid option selected.");
			}
		}
	}

	/**
	 * Allows a player to play a card from their hand. This could be playing a Pokemon to the bench,
	 * using an Energy card, or playing a Trainer card. The method makes sure that only one
	 * Energy card can be played per turn.
	 * 
	 * @param canAttack Indicates if the player has the option to attack during this turn.
	 */
	public void playCard(boolean canAttack){
		boolean playedEnergy = false;
		System.out.println("Which card would you like to play? ( 0 to go back )");
		int cardIndex = -1;
		while(!(cardIndex > 0 && cardIndex < hand.size())){
			this.viewHand();
			cardIndex = kb.nextInt()-1;
			if (cardIndex == -1){
				return;
			}
			Card card = hand.get(cardIndex);
			if (card instanceof Pokemon){
				if (bench.size() >= 5)
					System.out.println("Bench is full!");
				else{
					Card pokemon = hand.remove(cardIndex);
					bench.add(pokemon);
					System.out.println(pokemon.getName() +" has been added to the bench!");
				}
			}
			if (card instanceof Energy){
				if (playedEnergy){
					System.out.println("You've already played an energy this turn! ( 0 to go back )");
				}
				else{
					Energy energy = (Energy)hand.remove(cardIndex);
					active.getEnergyPile().add(energy);
					System.out.println(energy.getName() + " has been added to the active pokemon");
					playedEnergy = true;
				}
			}
			if (card instanceof Trainer){
				((Trainer) card).playable(this);
				hand.remove(card);
			}
		}

	}

	/**
	 * Removes the active Pokemon, typically used when the active Pokemon is defeated.
	 */
	public void killActive(){
		this.active = null;
	}

	/**
	 * Prompts the player to select a Pokemon from their hand to set as the active Pokemon.
	 */
	public void setActive() {
		System.out.println("Pick a pokemon to make active");
		ArrayList<Card> pokemonInHand = new ArrayList<>();
		for (Card card:this.getHand()) {
			if (card instanceof Pokemon)
				pokemonInHand.add(card);
		}
		if (pokemonInHand.size() == 0){
			System.out.println("You have no more pokemon in your bench. You lose." + this.opponent.getPlayerNumber() + "wins!");
		}
		printCollection(pokemonInHand);
		int activeChoice = kb.nextInt();
		if (activeChoice >= 1 && activeChoice <= pokemonInHand.size()) {
			this.active = (Pokemon) pokemonInHand.get(activeChoice - 1);
			hand.remove(active);
		}
	}

	/**
	 * Displays the active Pokemon along with its current HP.
	 */
	public void printActive(){
		System.out.println("Active Pokemon: " + active.getName() + "(" + active.getHp() + "hp)");
	}

	/**
	 * Draws a card from the top of the deck.
	 * 
	 * @return The card drawn from the deck.
	 */
	public Card drawCard() {
		return deck.remove(0);
	}

	/**
	 * Draws an opening hand of 7 cards from the deck.
	 */
	public void drawHand() {
		for(int i = 0; i < 7; i++) {
			hand.add(drawCard());
		}
	}

	/**
	 * Draws 6 cards from the deck to form the prize pile.
	 */
	public void drawPrizePile() {
		for(int i = 0; i < 6; i++) {
			prizePile.add(drawCard());
		}
	}

	/**
	 * Checks if the player's opening hand is valid, which means having at least one Pokemon.
	 * 
	 * @return true if the hand is valid, false otherwise.
	 */
	public boolean isOpeningHandValid() {
		for (Card currentCard : hand) {
			if (currentCard instanceof Pokemon) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the deck by shuffling back in the hand and clearing the current hand.
	 */
	public void resetDeck() {
		deck.addAll(hand);
		hand.clear();
		Collections.shuffle(deck);
	}

	/**
	 * Returns the current hand of the player.
	 * 
	 * @return The player's hand as an ArrayList of Cards.
	 */
	public ArrayList<Card> getHand(){
		return hand;
	}

	/**
	 * Displays the current hand of the player listing each card's name.
	 */
	public void viewHand() {
		System.out.println("Current Hand:");
		for(int i = 0; i < hand.size(); i++) {
			System.out.println(i+1 + ") " + hand.get(i).getName() + " ");
		}
		System.out.println();
	}

	/**
	 * Returns the player number.
	 * 
	 * @return The player's identifying number.
	 */
	public int getPlayerNumber(){
		return this.playerNumber;
	}

	/**
	 * Returns the player's deck.
	 * 
	 * @return The player's deck as an ArrayList of Cards.
	 */
	public ArrayList<Card> getDeck(){
		return deck;
	}

	/**
	 * Prints a collection of cards such as the hand or bench listing each card's name.
	 * 
	 * @param collection The collection of cards to print.
	 */
	public void printCollection(ArrayList<Card> collection){
		for(int i = 0; i < collection.size(); i++) {
			System.out.print(i+1 + ") " + collection.get(i).getName() + "\n");
		}
	}

	/**
	 * Returns the player's bench.
	 * 
	 * @return The player's bench as an ArrayList of Cards.
	 */
	public ArrayList<Card> getBench() {
		return this.bench;
	}
}
