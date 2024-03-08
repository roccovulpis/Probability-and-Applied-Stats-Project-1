
public class Pikachu extends Pokemon {
	private static final String NAME = "Pikachu";
	private static final int HP = 60;
	private static final String ATTACK_ONE_NAME = "Tail Whip";
	private static final int ATTACK_ONE_DAMAGE = 10;
	private static final String ATTACK_TWO_NAME = "Spark";
	private static final int ATTACK_TWO_DAMAGE = 20;

	public Pikachu() {
		super(NAME, HP, ATTACK_ONE_NAME, ATTACK_ONE_DAMAGE, ATTACK_TWO_NAME, ATTACK_TWO_DAMAGE);
	}
	@Override
	public void attackOne(Pokemon target) {
		super.attackOne(target);
	}

	@Override
	public void attackTwo(Pokemon target) {
		super.attackTwo(target);
	}
}
