import java.util.TreeMap;
import java.util.TreeSet;

public class Brokerage implements Login {

	private TreeMap<String, Trader> traders;
	private TreeSet<Trader> activeTraders;
	private StockExchange exchange;

	Brokerage(StockExchange exchange) {
		traders = new TreeMap<String, Trader>();
		activeTraders = new TreeSet<Trader>();
		this.exchange = exchange;
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
		Trader active = traders.get(name);
		if (!traders.containsKey(active.getName()) && traders.get(name) != null)// name not found
			return -1;
		if (activeTraders.contains(active))// if already logged in
			return -3;
		if (!active.getPassword().equals(password))// wrong password
			return -2;
		activeTraders.add(active);
		active.openWindow();
		active.receiveMessage("Welcome to SafeTrade");
		return 0; // if no errors
	}

	/**
	 * Removes a specified trader from the set of logged-in traders.
	 * 
	 * @param trader
	 *            - the trader that logs out.
	 * 
	 */
	public void logout(Trader t) {
		if (activeTraders.contains(t))
			activeTraders.remove(t);
	}

	/**
	 * Requests a quote for a given stock from the stock exchange and passes it
	 * along to the trader by calling trader's receiveMessage method.
	 * 
	 * @param symbol
	 *            the stock symbol.
	 * @param trader
	 *            the trader who requested a quote.
	 */
	public void getQuote(String symbol, Trader trader) {
		trader.receiveMessage(exchange.getQuote(symbol));
	}

	/**
	 * Places an order at the stock exchange.
	 * 
	 * @param order
	 *            an order to be placed at the stock exchange.
	 */
	public void placeOrder(TradeOrder order) {
		exchange.placeOrder(order);
	}

}
