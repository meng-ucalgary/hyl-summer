package ca.hackyourlearning;

/**
 * Represents a miner from listed in MiningSetup.csv
 */
public class Miner {
	public String name;
	public Double daily_output;
	public Double wattage;

	/**
	 * Contructs Miner object with supplied parameters
	 * @param name
	 * @param daily_output
	 * @param wattage
	 */
	public Miner(String name, Double daily_output, Double wattage) {
		super();
		this.name = name;
		this.daily_output = daily_output;
		this.wattage = wattage;
	}
}
