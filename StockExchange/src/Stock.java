import java.text.DecimalFormat;
import java.util.PriorityQueue;

public class Stock implements Comparable<Stock> {

	public static DecimalFormat money = new DecimalFormat("$#,##0.00");

	private String symbol;
	private String name;
	private double lowPrice, lastPrice, highPrice;
	private PriorityQueue<TradeOrder> buy;
	private PriorityQueue<TradeOrder> sell;
	private int dayVolume;

	/**
	 * Constructs a new stock with a given symbol, company name, and starting price.
	 * Sets low price, high price, and last price to the same opening price. Sets
	 * "day" volume to zero. Initializes a priority queue for sell orders to an
	 * empty PriorityQueue with a PriceComparator configured for comparing orders in
	 * ascending order; initializes a priority queue for buy orders to an empty
	 * PriorityQueue with a PriceComparator configured for comparing orders in
	 * descending order.
	 * 
	 * @param s
	 *            the stock symbol
	 * @param n
	 *            full company name
	 * @param p
	 *            opening price for this stock
	 */
	public Stock(String s, String n, double p) {
		symbol = s;
		name = n;
		lowPrice = p;
		lastPrice = p;
		highPrice = p;
		dayVolume = 0;
		buy = new PriorityQueue<TradeOrder>(new PriceComparator());
		sell = new PriorityQueue<TradeOrder>(new PriceComparator());

	}

	/**
	 * Returns the symbol of this stock
	 * 
	 * @return the symbol of this stock
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Returns the name of this stock
	 * 
	 * @return the name of this stock
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the last price of this stock
	 * 
	 * @return the last price of this stock
	 */
	public double getLastPrice() {
		return lastPrice;
	}

	/**
	 * Returns the lowest price of this stock
	 * 
	 * @return the lowest price of this stock
	 */
	public double getLowPrice() {
		return lowPrice;
	}

	/**
	 * Returns the highest price of this stock
	 * 
	 * @return the highest price of this stock
	 */
	public double getHighPrice() {
		return highPrice;
	}

	/**
	 * Returns a DecimalFromat in a money form
	 * 
	 * @return the correct money format ("$$#,##0.00")
	 */
	public DecimalFormat getMoney() {
		return money;
	}

	@Override
	public String toString() {
		return getSymbol() + " " + getName() + " " + getMoney().format(getLastPrice());
	}

	@Override
	public int compareTo(Stock o) {
		return (int) Math.signum((getLastPrice() - o.getLastPrice()));
	}

	/**
	 * Returns a quote string for this stock. The quote includes: the company name
	 * for this stock; the stock symbol; last sale price; the lowest and highest day
	 * prices; the lowest price in a sell order (or "market") and the number of
	 * shares in it (or "none" if there are no sell orders); the highest price in a
	 * buy order (or "market") and the number of shares in it (or "none" if there
	 * are no buy orders). For example: Giggle.com (GGGL) Price: 10.00 hi: 10.00 lo:
	 * 10.00 vol: 0 Ask: 12.75 size: 300 Bid: 12.00 size: 500 Or: Giggle.com (GGGL)
	 * Price: 12.00 hi: 14.50 lo: 9.00 vol: 500 Ask: none Bid: 12.50 size: 200
	 * 
	 * @return the quote for this stock.
	 */
	public String getQuote() {
		String qt = this.getName() + " (" + this.getSymbol() + ")\n" + "Price: " + this.getLastPrice() + "\t" + "hi: "
				+ this.getHighPrice() + "\t" + "low: " + this.getLowPrice() + "\t" + "vol: " + this.getVol() + "\n";
		qt += "Ask: ";
		if (!buy.isEmpty()) {
			qt += buy.peek().getPrice();
			qt += " size: " + buy.peek().getShares();
		} else
			qt += "none ";

		qt += "Bid: ";
		if (!sell.isEmpty()) {
			qt += sell.peek().getPrice();
			qt += "size: " + sell.peek().getShares();
		} else
			qt += "none ";
		return qt;
	}

	/**
	 * A helper method that returns the dayVolume
	 * 
	 * @return the dayVolume
	 */
	private int getVol() {

		return dayVolume;
	}

	/**
	 * Places a trading order for this stock. Adds the order to the appropriate
	 * priority queue depending on whether this is a buy or sell order. Notifies the
	 * trader who placed the order that the order has been placed, by sending a
	 * message to that trader. For example: New order: Buy GGGL (Giggle.com) 200
	 * shares at $38.00 Or: New order: Sell GGGL (Giggle.com) 150 shares at market
	 * Executes pending orders by calling executeOrders.
	 * 
	 * @param order
	 *            a trading order to be placed.
	 */
	public void placeOrder(TradeOrder order) {
		String msg = "New order: ";
		if (order.isBuy()) {
			buy.add(order);
			msg += "Buy ";

		}

		if (order.isSell()) {
			sell.add(order);
			msg += "Sell ";
		}

		msg += this.getSymbol() + "  (" + this.getName() + ")";
		msg += "\n" + order.getShares() + " shares at ";

		if (order.isLimit())
			msg += money.format(order.getPrice());
		else
			msg += "market";
		dayVolume += order.getShares();
		order.getTrader().receiveMessage(msg);
		
	}

}
