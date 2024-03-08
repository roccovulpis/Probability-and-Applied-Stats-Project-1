import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PokemonCardGame {

	private final Player playerOne;
	private final Player playerTwo;
	private final Scanner kb;


	public PokemonCardGame() {
		playerOne = new Player(1);
		playerTwo = new Player(2);
		kb = new Scanner(System.in);
	}

	public void setUpGame() {
		String choice = "";
		playerOne.setOpponent(playerTwo);
		playerTwo.setOpponent(playerOne);
		while (!choice.equals("heads") && !choice.equals("tails")) {
			System.out.println("Heads or Tails?");
			choice = kb.nextLine().toLowerCase(); 

			if (!choice.equals("heads") && !choice.equals("tails")) {
				System.out.println("Please enter either \"Heads\" or \"Tails\"");
			}
		}
		if(choice.equals(flipCoin())) {
			initializeDeck(playerOne, playerTwo);
			playerOne.turn(true);
		} else {
			initializeDeck(playerTwo, playerOne);
			playerTwo.turn(true);
		}
	}

	public void initializeDeck(Player coinFlipWinner, Player coinFlipLoser) {
		coinFlipWinner.initializeDeck(20, 20, 20);
		coinFlipWinner.drawHand();
		coinFlipWinner.drawPrizePile();

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

	public void run() {
		setUpGame();
		while (playerOne.getPrizePile().size() > 0 || playerTwo.getPrizePile().size() > 0){

		}
	}
}