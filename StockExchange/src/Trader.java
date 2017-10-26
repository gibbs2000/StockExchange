
public class Trader implements Comparable<Trader> {
	private Brockerage b;
	private String name;
	private String pw;

	
	public Trader(Brockerage b, String name, String pw) {
		this.b = b;
		this.name = name;
		this.pw = pw;
	}


	public String getName() {
		return name;
	}

	
	public String getPassword() {
		return pw;
	}


	
	
	@Override
	public String toString() {
		return "Trader [b=" + b + ", name=" + name + "]";
	}


	@Override
	public int compareTo(Trader o) {
		return name.toLowerCase().compareTo(o.getName().toLowerCase());
	}


}
