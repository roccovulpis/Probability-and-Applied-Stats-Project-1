/**
 * The PokemonCardGameTester class contains the main method for running the Pokemon card game along with
 * the Mulligan and Rare Candy Monte Carlo simulations.
 * 
 * It creates instances of the MulliganMonteCarloSimulation, RareCandyMonteCarloSimulation, and PokemonCardGame
 * classes and initiates the simulations by calling their respective run methods.
 * 
 * The MulliganMonteCarloSimulation and RareCandyMonteCarloSimulation class's run methods take 
 * in a parameter to define the number of runs in each simulation.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class PokemonCardGameTester {
	public static void main(String[] args) {
//		MulliganMonteCarloSimulation testMulliganProbabilites = new MulliganMonteCarloSimulation();
//		testMulliganProbabilites.run(100000);
//		
//		RareCandyMonteCarloSimulation testRareCandyProbabilites = new RareCandyMonteCarloSimulation();
//		testRareCandyProbabilites.run(100000);
		
		PokemonCardGame game = new PokemonCardGame();
		game.run();
	}
}
