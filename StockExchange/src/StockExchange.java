import java.util.HashMap;

public class StockExchange {

	private HashMap<String, Stock> stocks;

	/**
	 * Constructs a new stock exchange object. Initializes listed stocks to an empty
	 * map (a HashMap).
	 */
	public StockExchange() {
		stocks = new HashMap<String, Stock>();
	}

	/**
	 * Adds a new stock with given parameters to the listed stocks.
	 * 
	 * @param symbol
	 *            stock symbol.
	 * @param name
	 *            full company name.
	 * @param price
	 *            opening stock price.
	 */
	public void listStock(String symbol, String name, double price) {
		stocks.put(symbol, new Stock(symbol, name, price));

	}

	/**
	 * Returns a quote for a given stock.
	 * 
	 * @param symbol
	 *            stock symbol.
	 * @return a text message that contains the quote.
	 */
	public String getQuote(String symbol) {
		if (stocks.get(symbol) == null)
			throw new IllegalArgumentException("Stock does not exist");

		return stocks.get(symbol).getQuote();
	}

	/**
	 * Places a trade order by calling stock.placeOrder for the stock specified by
	 * the stock symbol in the trade order.
	 * 
	 * @param order
	 *            a trading order to be placed with this stock exchange.
	 */
	public void placeOrder(TradeOrder order) {
		if (stocks.get(order.getSymbol()) == null)
			throw new IllegalArgumentException("Stock does not exist");
		stocks.get(order.getSymbol()).placeOrder(order);
	}
}
