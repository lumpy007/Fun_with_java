
public class tankNameAndId implements Comparable<tankNameAndId> {

	private String name = "";
	private String id = "";

	public tankNameAndId(String name, String id)
	{
		super();
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int compareTo (tankNameAndId name)
	{
		char compare = ((tankNameAndId) name).getName().charAt(0);
		
		return this.name.charAt(0) - compare;
	}
	
}

