
public class playerTankStats implements Comparable<playerTankStats> {

	private String account_name;
	private String account_id;
	private String tank_id;
	private int damage_dealt;
	private int battles;
	
	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getTank_id() {
		return tank_id;
	}

	public void setTank_id(String tank_id) {
		this.tank_id = tank_id;
	}

	public int getDamage_dealt() {
		return damage_dealt;
	}

	public void setDamage_dealt(int damage_dealt) {
		this.damage_dealt = damage_dealt;
	}

	public int getBattles() {
		return battles;
	}

	public void setBattles(int battles) {
		this.battles = battles;
	}


	
	public playerTankStats(String account_name, String account_id, String tank_id, int damage_dealt, int battles)
	{
		super();
		this.account_name = account_name;
		this.account_id = account_id;
		this.tank_id = tank_id;
		this.damage_dealt = damage_dealt;
		this.battles = battles;
	}
	
	public int compareTo (playerTankStats stats)
	{
		int compareDamage = ((playerTankStats) stats).getDamage_dealt();
		
		return compareDamage - this.damage_dealt;
	}
}

