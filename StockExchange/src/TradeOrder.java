
/**
 * @author gibbonss
 *
 */
public class TradeOrder {

	public static final String BUY = "buy";
	public static final String SELL = "sell";

	public static final String MARKET = "market";
	public static final String LIMIT = "limit";
	
	private int numShares;
	private Trader trader;
	private String symbol;
	private boolean buyOrder, marketOrder;
	private double price;

	public TradeOrder(Trader trader, String symbol, boolean buyOrder, boolean marketOrder, int numShares, 
			double price) {
		this.numShares = numShares;
		this.trader = trader;
		this.symbol = symbol;
		this.buyOrder = buyOrder;
		this.marketOrder = marketOrder;
		this.price = price;
	}

	public String toString() {
		String buyOrSell, marketOrLimit;
		if (buyOrder)
			buyOrSell = "buy";
		else
			buyOrSell = "sell";
		if (marketOrder)
			marketOrLimit = "market";
		else
			marketOrLimit = "limit";
		return trader + "\n" + symbol + "\n" + buyOrSell + "\n" + numShares + "\n" + marketOrLimit + "\n"
				+ Stock.money.format(price);

	}

}
