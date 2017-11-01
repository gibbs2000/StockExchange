import java.util.Comparator;

public class PriceComparator implements Comparator<TradeOrder> {

	private boolean ascending;

	public PriceComparator() {
		ascending = true;
	}

	public PriceComparator(boolean ascending) {
		this.ascending = ascending;
	}

	@Override
	public int compare(TradeOrder t1, TradeOrder t2) {
		if (t1.isMarket() && t2.isMarket())
			return 0;
		if (t1.isMarket() && t2.isLimit())
			return -1;
		if (t1.isLimit() && t2.isMarket())
			return 1;
		if (ascending)
			return (int) (100 * (t1.getPrice() - t2.getPrice()));
		else
			return (int) (-100 * (t1.getPrice() - t2.getPrice()));
	}
}