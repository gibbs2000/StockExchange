import java.text.DecimalFormat;
import java.util.PriorityQueue;

public class Stock implements Comparable<Stock> {

	public static DecimalFormat money = new DecimalFormat("$#,##0.00");

	private String symbol;
	private String name;
	private double lowPrice, lastPrice, highPrice;
	private PriorityQueue<TradeOrder> buy;
	private PriorityQueue<TradeOrder> sell;
	private double dayVolume;

	public Stock(String s, String n, double p) {
		symbol = s;
		name = n;
		lowPrice = p;
		lastPrice = p;
		highPrice = p;
		dayVolume = 0.0;

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

}
