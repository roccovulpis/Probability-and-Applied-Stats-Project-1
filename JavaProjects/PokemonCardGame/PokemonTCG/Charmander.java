public class Charmander extends Pokemon implements Attackable {
	private static final String NAME = "Charmander";
	private static final int HP = 70;
	private static final String ATTACK_ONE_NAME = "Scratch";
	private static final int ATTACK_ONE_DAMAGE = 10;
	private static final String ATTACK_TWO_NAME = "Flame Tail";
	private static final int ATTACK_TWO_DAMAGE = 20;

	public Charmander() {
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
