import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Player {

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

	public boolean isFirst(){
		return isFirst;
	}

	public void setIsFirst(boolean isFirst){
		this.isFirst = isFirst;
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public ArrayList<Card> getPrizePile() {
		return prizePile;
	}

	public ArrayList<Card> getDiscardPile() {
		return discardPile;
	}

	public Player getOpponent() {
		return opponent;
	}

	public Pokemon getActive() {
		return this.active;
	}

	public Pokemon getOpponentActivePokemon() {
		if (opponent != null) {
			return opponent.getActive();
		}
		return null;
	}

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

	public void killActive(){
		this.active = null;
	}
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

	public void printActive(){
		System.out.println("Active Pokemon: " + active.getName() + "(" + active.getHp() + "hp)");
	}

	public Card drawCard() {
		return deck.remove(0);
	}
	
	public void drawHand() {
		for(int i = 0; i < 7; i++) {
			hand.add(drawCard());
		}
	}
	
	public void drawPrizePile() {
		for(int i = 0; i < 6; i++) {
			prizePile.add(drawCard());
		}
	}
	
	public boolean isOpeningHandValid() {
		for (Card currentCard : hand) {
			if (currentCard instanceof Pokemon) {
				return true;
			}
		}
	    return false;
	}
	
	public void resetDeck() {
		deck.addAll(hand);
		hand.clear();
		Collections.shuffle(deck);
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public void viewHand() {
		System.out.println("Current Hand:");
		for(int i = 0; i < hand.size(); i++) {
			System.out.println(i+1 + ") " + hand.get(i).getName() + " ");
		}
		System.out.println();
	}

	public int getPlayerNumber(){
		return this.playerNumber;
	}
	public ArrayList<Card> getDeck(){
		return deck;
	}

	public void printCollection(ArrayList<Card> collection){
		for(int i = 0; i < collection.size(); i++) {
			System.out.print(i+1 + ") " + collection.get(i).getName() + "\n");
		}
	}

	public ArrayList<Card> getBench() {
		return this.bench;
	}
}
