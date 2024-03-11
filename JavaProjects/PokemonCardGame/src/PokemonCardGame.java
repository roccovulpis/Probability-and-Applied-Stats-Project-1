import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The main game engine for a Pokemon card game. This class manages the game setup,
 * including initializing players, managing turns, and determining the game outcome.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class PokemonCardGame {

	// Fields 
	private final Player playerOne;
	private final Player playerTwo;
	private final Scanner kb;


	/**
	 * Constructs a new Pokemon Card Game instance. Initializes two players and a scanner for input.
	 */	
	public PokemonCardGame() {
		playerOne = new Player(1);
		playerTwo = new Player(2);
		kb = new Scanner(System.in);
	}

	/**
	 * Sets up the game by determining which player goes first through a coin flip and initializing decks.
	 */
	public void setUpGame() {
		String choice = "";
		// Sets each player's opponent.
		playerOne.setOpponent(playerTwo);
		playerTwo.setOpponent(playerOne);
		// Loop until a valid input is provided by the user.
		while (!choice.equals("heads") && !choice.equals("tails")) {
			System.out.println("Heads or Tails?");
			choice = kb.nextLine().toLowerCase(); 
			if (!choice.equals("heads") && !choice.equals("tails")) {
				System.out.println("Please enter either \"Heads\" or \"Tails\"");
			}
		}
		// Determine the first player based on the coin flip and initialize both players decks.
		if(choice.equals(flipCoin())) {
			initializeDeck(playerOne, playerTwo);
			playerOne.setIsFirst(true);
			playerOne.firstTurn();
			playerTwo.firstTurn();
		} else {
			initializeDeck(playerTwo, playerOne);
			playerTwo.setIsFirst(true);
			playerTwo.firstTurn();
			playerOne.firstTurn();
		}
	}

	/**
	 * Initializes the decks for both players ensuring that valid opening hands are drawn.
	 * 
	 * @param coinFlipWinner The player who won the coin flip and goes first.
	 * @param coinFlipLoser The player who lost the coin flip and goes second.
	 */
	public void initializeDeck(Player coinFlipWinner, Player coinFlipLoser) {
		// Initialize decks for both players and draw the initial hand and prize pile
		coinFlipWinner.initializeDeck(20, 20, 20);
		coinFlipWinner.drawHand();
		coinFlipWinner.drawPrizePile();

		// Makes sure the opening hand is valid.
		while(!coinFlipWinner.isOpeningHandValid()) {
			coinFlipWinner.resetDeck();
			coinFlipWinner.drawHand();
		}

		coinFlipLoser.initializeDeck(20, 20, 20);
		coinFlipLoser.drawHand();
		coinFlipLoser.drawPrizePile();

		while(!coinFlipLoser.isOpeningHandValid()) {
			coinFlipLoser.resetDeck();
			coinFlipLoser.drawHand();
		}
	}

	/**
	 * Simulates flipping a coin to decide which player goes first.
	 * 
	 * @return A string "heads" or "tails" representing the outcome of the coin flip.
	 */
	public String flipCoin() {
		Random random = new Random();
		int coin = random.nextInt(2);
		if(coin == 0) {
			return "heads";
		}
		else {
			return "tails";
		}
	}

	/**
	 * Starts and runs the game loop until one player wins by empties their prize pile.
	 */
	public void run() {
		// Set up the game.
		setUpGame();
		// Determine the first player based on who is set as first.
		Player firstPlayer = new Player(0);
		if(playerOne.isFirst()) firstPlayer=playerOne;
		if(playerTwo.isFirst()) firstPlayer=playerTwo;
		// Game loop continues until one players prize pile is depleted.
		while (playerOne.getPrizePile().size() > 0 && playerTwo.getPrizePile().size() > 0){
			firstPlayer.turn(true);
			firstPlayer.getOpponent().turn(true);
		}
		if(playerOne.getPrizePile().size() == 0) System.out.println("Player One wins!");
		if(playerTwo.getPrizePile().size() == 0) System.out.println("Player Two wins!");

	}
}
