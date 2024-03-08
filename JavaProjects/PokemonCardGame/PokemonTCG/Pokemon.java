import java.util.ArrayList;

public class Pokemon extends Card implements Attackable{

	public Pokemon(String name, int hp, String attackOneName,int attackOneDMG, String attackTwoName, int attackTwoDMG) {
		super(name);
		this.hp = hp;
		this.attackOneDMG = attackOneDMG;
		this.attackTwoDMG = attackTwoDMG;
		this.attackOneName = attackOneName;
		this.attackTwoName = attackTwoName;
		this.energyPile = new ArrayList<>();
	}

	public Pokemon(String default_pokemon) {
		super(default_pokemon);
	}

	public int getAttackOneDMG() {
		return attackOneDMG;
	}

	public int getAttackTwoDMG() {
		return attackTwoDMG;
	}

	public String getAttackOneName() {
		return attackOneName;
	}

	public String getAttackTwoName() {
		return attackTwoName;
	}

	private int hp;
	private ArrayList<Energy> energyPile;

	private int attackOneDMG;
	private int attackTwoDMG;
	private String attackOneName;
	private String attackTwoName;

	public boolean hasEnergy(){
		return this.getEnergyPile().size() >= 1;
	}

	public int getHp() {
		return hp;
	}

	public ArrayList<Energy> getEnergyPile() {
		return energyPile;
	}

	public void setHp(int userInputHp) {
		hp = userInputHp;
	}

	public void attackOne(Pokemon target) {
		if (!hasEnergy()){
			System.out.println(getName() + " doesn't have enough energy to use " + attackOneName);
			return;
		}
		System.out.println(this.getName() + "used " +  attackOneName + "! -" + attackOneDMG);
		int currentHp = target.getHp();
		int resultingHp = currentHp - attackOneDMG;
		target.setHp(Math.max(resultingHp, 0));
		System.out.println(this.getName() + "'s Target's Hp: " + target.getHp());
	}

	public void attackTwo(Pokemon target) {
		if (!hasEnergy()){
			System.out.println(getName() + " doesn't have enough energy to use " + attackTwoName);
			return;
		}
		System.out.println(this.getName() + "used " +  attackTwoName + "! -" + attackTwoDMG);
		int currentHp = target.getHp();
		int resultingHp = currentHp - attackTwoDMG;
		target.setHp(Math.max(resultingHp, 0));
		System.out.println(this.getName() + "'s Target's Hp: " + target.getHp());
	}
}
