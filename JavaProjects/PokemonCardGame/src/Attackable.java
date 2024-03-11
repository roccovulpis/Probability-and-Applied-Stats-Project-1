/**
 * Represents an object that is capable of attacking. 
 * This interface requires implementing classes to define two attack methods.
 * 
 *  @author Rocco Vulpis
 *  @version 3/7/2024
 */
public interface Attackable {

	void attackOne(Pokemon target);
	
	void attackTwo(Pokemon target);
	
}
