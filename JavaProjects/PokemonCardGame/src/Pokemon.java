import java.util.ArrayList;

/**
 * Represents a Pokémon with a name, hp, attacks, and an energy pile.
 * This class extends the Card class and implements the Attackable interface,
 * enabling it to perform actions such as attacking other Pokémon.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class Pokemon extends Card implements Attackable{
	
	// Fields 
	private int hp;
	private ArrayList<Energy> energyPile;
	private int attackOneDMG;
	private int attackTwoDMG;
	private String attackOneName;
	private String attackTwoName;

	/**
     * Constructs a new Pokémon and initializes the energy pile.
     *
     * @param name The name of the Pokémon.
     * @param hp The health points of the Pokémon.
     * @param attackOneName The name of the first attack.
     * @param attackOneDMG The damage of the first attack.
     * @param attackTwoName The name of the second attack.
     * @param attackTwoDMG The damage of the second attack.
     */
	public Pokemon(String name, int hp, String attackOneName,int attackOneDMG, String attackTwoName, int attackTwoDMG) {
		super(name);
		this.hp = hp;
		this.attackOneDMG = attackOneDMG;
		this.attackTwoDMG = attackTwoDMG;
		this.attackOneName = attackOneName;
		this.attackTwoName = attackTwoName;
		this.energyPile = new ArrayList<>();
	}

	/**
     * Constructs a new Pokémon with a default name.
     *
     * @param default_pokemon The default name of the Pokémon.
     */
	public Pokemon(String default_pokemon) {
		super(default_pokemon);
	}

	/**
     * Getter for first attack's damage. 
     *
     * @return attackOneDMG returns the damage dealt.
     */
	public int getAttackOneDMG() {
		return attackOneDMG;
	}

	/**
     * Getter for second attack's damage. 
     *
     * @return attackTwoDMG returns the damage dealt.
     */
	public int getAttackTwoDMG() {
		return attackTwoDMG;
	}

	/**
     * Getter for first attack's name. 
     *
     * @return attackOneName returns the name of attack one.
     */
	public String getAttackOneName() {
		return attackOneName;
	}

	/**
     * Getter for second attack's name. 
     *
     * @return attackTwoName returns the name of attack two.
     */
	public String getAttackTwoName() {
		return attackTwoName;
	}
	
	/**
     * Getter for Pokemon's HP. 
     *
     * @return hp HP of the Pokemon.
     */
	public int getHp() {
		return hp;
	}

	/**
     * Checks if the Pokémon has at least one energy in its energy pile.
     *
     * @return true if the energy pile contains one or more energy cards, false otherwise.
     */
	public boolean hasEnergy(){
		return this.getEnergyPile().size() >= 1;
	}

	/**
     * Getter for the Pokemon's energy pile.
     *
     * @return energyPile The Pokemon's energy pile.
     */
	public ArrayList<Energy> getEnergyPile() {
		return energyPile;
	}

	/**
     * Sets the HP of the Pokémon.
     * 
     * @param userInputHp The new HP value to set.
     */
	public void setHp(int userInputHp) {
		hp = userInputHp;
	}

	/**
     * Performs the first attack on a target Pokémon. This method checks if the Pokémon has enough energy,
     * calculates the resulting HP of the target after the attack, and updates the target's HP.
     * 
     * @param target The Pokémon that is being attacked.
     */
	public void attackOne(Pokemon target) {
		if (!hasEnergy()){
			System.out.println(getName() + " doesn't have enough energy to use " + attackOneName);
			return;
		}
		System.out.println(this.getName() + " used " +  attackOneName + "! -" + attackOneDMG);
		int currentHp = target.getHp();
		int resultingHp = currentHp - attackOneDMG;
		target.setHp(Math.max(resultingHp, 0));
		System.out.println(this.getName() + "'s Target's Hp: " + target.getHp());
		System.out.println();
	}

	/**
     * Performs the second attack on a target Pokémon. This method checks if the Pokémon has enough energy,
     * calculates the resulting HP of the target after the attack, and updates the target's HP.
     * 
     * @param target The Pokémon that is being attacked.
     */
	public void attackTwo(Pokemon target) {
		if (!hasEnergy()){
			System.out.println(getName() + " doesn't have enough energy to use " + attackTwoName);
			return;
		}
		System.out.println(this.getName() + " used " +  attackTwoName + "! -" + attackTwoDMG);
		int currentHp = target.getHp();
		int resultingHp = currentHp - attackTwoDMG;
		target.setHp(Math.max(resultingHp, 0));
		System.out.println(this.getName() + "'s Target's Hp: " + target.getHp());
		System.out.println();
	}
}
