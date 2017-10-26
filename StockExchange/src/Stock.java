import java.text.DecimalFormat;
import java.util.PriorityQueue;

public class Stock implements Comparable<Stock> {

	public static DecimalFormat money = new DecimalFormat("$#,##0.00");

	private String symbol;
	private String name;
	private double lowPrice, lastPrice, highPrice;
	private PriorityQueue<TradeOrder> buy;
	private PriorityQueue<TradeOrder> sell;

	public Stock(String s, String n, double p) {
		symbol = s;
		name = n;
		lowPrice = p;
		lastPrice = p;
		highPrice = p;

	}

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public DecimalFormat getMoney() {
		return money;
	}

	@Override
	public String toString() {
		return getSymbol() + " " + getName() + " " + getMoney().format(getLastPrice());
	}

	@Override
	public int compareTo(Stock o) {
		return Math.signum((getLastPrice() - o.getLastPrice()));
	}

}
