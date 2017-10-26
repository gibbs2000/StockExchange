import java.util.TreeMap;
import java.util.TreeSet;

public class Brockerage implements Login {

	private TreeMap<String, Trader> traders;
	private TreeSet<Trader> activeTraders;

	Brockerage(StockExchange exchange) {
		traders = new TreeMap<String, Trader>();
		activeTraders = new TreeSet<Trader>();
	}

	@Override
	public int addUser(String name, String password) {
		if (name.length() < 4 || name.length() > 10)
			return -1;
		if (name.length() < 2 || name.length() > 10)
			return -2;
		if (traders.containsKey(name))
			return -3;
		(traders).put(name, new Trader(this, name, password));
		return 0;
	}

	@Override
	public int login(String name, String password) {
		if()
			return -1;
		if()
			return -2;
		if (activeTraders.contains(name))
			return -3;
		return 0;
	}

}
