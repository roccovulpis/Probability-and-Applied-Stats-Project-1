
public class PokemonCardGameTester {
	public static void main(String[] args) {
		PokemonMonteCarloSimulation test = new PokemonMonteCarloSimulation();
		//test.run(100000);
		
		PokemonCardGame game = new PokemonCardGame();
		game.run();
	}
}
