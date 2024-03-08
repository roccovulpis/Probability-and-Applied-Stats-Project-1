public class Bulbasaur extends Pokemon implements Attackable{
	private static final String NAME = "Bulbasaur";
	private static final int HP = 60;
	private static final String ATTACK_ONE_NAME = "Vine Whip";
	private static final int ATTACK_ONE_DAMAGE = 10;
	private static final String ATTACK_TWO_NAME = "Razor Leaf";
	private static final int ATTACK_TWO_DAMAGE = 20;

	public Bulbasaur() {
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