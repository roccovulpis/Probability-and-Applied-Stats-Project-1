/**
 * Represents the Pokemon Charmander with attributes and attacks. This class extends
 * the Pokemon class and implements the Attackable interface.
 * 
 * @author Rocco Vulpis
 * @version 3/7/2024
 */
public class Charmander extends Pokemon implements Attackable {
	
	// Constant values representing Charmander's attributes and attacks.
	private static final String NAME = "Charmander";
	private static final int HP = 70;
	private static final String ATTACK_ONE_NAME = "Scratch";
	private static final int ATTACK_ONE_DAMAGE = 10;
	private static final String ATTACK_TWO_NAME = "Flame Tail";
	private static final int ATTACK_TWO_DAMAGE = 20;

	/**
     * Constructs Charmander with its specific name, HP,
     * and two attacks along with their respective damage values by calling the parent
     * Pokemon class constructor.
     */
	public Charmander() {
		super(NAME, HP, ATTACK_ONE_NAME, ATTACK_ONE_DAMAGE, ATTACK_TWO_NAME, ATTACK_TWO_DAMAGE);
	}
	
	/**
     * Performs the first attack "Scratch" on a target Pokemon.
     * This method overrides the Pokemon class's attackOne method to specifically
     * handle Charmander's first attack.
     *
     * @param target The Pokemon that is being attacked.
     */
	@Override
	public void attackOne(Pokemon target) {
		super.attackOne(target);
	}

	/**
     * Performs the second attack "Flame Tail" on a target Pokemon.
     * This method overrides the Pokemon class's attackTwo method to specifically
     * handle Charmander's second attack.
     *
     * @param target The Pokemon that is being attacked.
     */
	@Override
	public void attackTwo(Pokemon target) {
		super.attackTwo(target);
	}
}
