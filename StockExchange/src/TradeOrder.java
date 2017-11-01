
/**
 * @author gibbonss
 *
 */
public class TradeOrder {

	public static final String BUY = "buy";
	public static final String SELL = "sell";

	public static final String MARKET = "market";
	public static final String LIMIT = "limit";

	private int shares;
	private Trader trader;
	private String symbol;
	private boolean buy, market;
	private double price;

	public TradeOrder(Trader trader, String symbol, boolean buyOrder, boolean marketOrder, int numShares,
			double price) {
		this.shares = numShares;
		this.trader = trader;
		this.symbol = symbol;
		this.buy = buyOrder;
		this.market = marketOrder;
		this.price = price;
	}

	/**
	 * Returns true if this is a buy order; otherwise returns false.
	 * 
	 * @return true if this is a buy order; false otherwise.
	 */
	public boolean isBuy() {
		return buy;
	}

	/**
	 * Returns true if this is a sell order; otherwise returns false.
	 * 
	 * @return true if this is a sell order; false otherwise.
	 */
	public boolean isSell() {
		return !buy;
	}

	/**
	 * Returns true if this is a market order; otherwise returns false.
	 * 
	 * @return true if this is a market order; false otherwise.
	 */
	public boolean isMarket() {
		return market;
	}

	/**
	 * Returns true if this is a limit order; otherwise returns false.
	 * 
	 * @return true if this is a limit order; false otherwise.
	 */
	public boolean isLimit() {
		return !market;
	}

	/**
	 * Returns the trader for this trade order.
	 * 
	 * @return the trader who placed this trade order.
	 */
	public Trader getTrader() {
		return trader;
	}

	/**
	 * Returns the number of shares to be traded in this trade order.
	 * 
	 * @return the number of shares to be traded in this trade order.
	 */
	public int getShares() {
		return shares;
	}

	/**
	 * Returns the price per share for this trade order (used by a limit order).
	 * 
	 * @return the price per share for this trade order.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the stock symbol for this trade order.
	 * 
	 * @return the stock symbol for this trade order.
	 */
	public String getSymbol() {
		return symbol;
	}

	public String toString() {
		String buyOrSell, marketOrLimit;
		if (buy)
			buyOrSell = BUY;
		else
			buyOrSell = SELL;
		if (market)
			marketOrLimit = MARKET;
		else
			marketOrLimit = LIMIT;
		return trader + "\n" + symbol + "\n" + buyOrSell + "\n" + shares + "\n" + marketOrLimit + "\n"
				+ Stock.money.format(price);

	}

	/**
	 * Subtracts a given number of shares from the total number of shares in this
	 * trade order.
	 * 
	 * @param shares
	 *            a number of shares to be subtracted.
	 */
	public void subtractShares(int shares) {
		if (shares > this.getShares())
			throw new IllegalArgumentException("Not enough shares remaining");
		this.shares -= shares;
	}

}
