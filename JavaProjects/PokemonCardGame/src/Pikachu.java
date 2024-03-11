/**
 * Represents the Pokemon Pikachu with attributes and attacks. This class extends
 * the Pokemon class and implements the Attackable interface.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class Pikachu extends Pokemon {
	
	// Constant values representing Pikachu's attributes and attacks.
	private static final String NAME = "Pikachu";
	private static final int HP = 60;
	private static final String ATTACK_ONE_NAME = "Tail Whip";
	private static final int ATTACK_ONE_DAMAGE = 10;
	private static final String ATTACK_TWO_NAME = "Spark";
	private static final int ATTACK_TWO_DAMAGE = 20;

	/**
     * Constructs Pikachu with its specific name, HP,
     * and two attacks along with their respective damage values by calling the parent
     * Pokemon class constructor.
     */
	public Pikachu() {
		super(NAME, HP, ATTACK_ONE_NAME, ATTACK_ONE_DAMAGE, ATTACK_TWO_NAME, ATTACK_TWO_DAMAGE);
	}
	
	/**
     * Performs the first attack "Tail Whip" on a target Pokemon.
     * This method overrides the Pokemon class's attackOne method to specifically
     * handle Pikachu's first attack.
     *
     * @param target The Pokemon that is being attacked.
     */
	@Override
	public void attackOne(Pokemon target) {
		super.attackOne(target);
	}

	/**
     * Performs the second attack "Spark" on a target Pokemon.
     * This method overrides the Pokemon class's attackTwo method to specifically
     * handle Pikachu's second attack.
     *
     * @param target The Pokemon that is being attacked.
     */
	@Override
	public void attackTwo(Pokemon target) {
		super.attackTwo(target);
	}
}
