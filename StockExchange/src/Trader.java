import java.util.ArrayList;

public class Trader implements Comparable<Trader> {
	private Brokerage b;
	private String name;
	private String pw;
	private ArrayList<String> mailbox;
	private TraderWindow myWindow;

	public Trader(Brokerage b, String name, String pw) {
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

	@Override
	public boolean equals(Object o) {
		if (o instanceof Trader)
			return this.compareTo((Trader) o) == 0;
		else
			return false;
	}

	/**
	 * Adds msg to this trader's mailbox and displays all messages. If this trader
	 * is logged in (myWindow is not null) removes and shows all the messages in the
	 * mailbox by calling myWindow.showMessage(msg) for each msg in the mailbox.
	 * 
	 * @param msg
	 *            a message to be added to this trader's mailbox.
	 */
	public void receiveMessage(String msg) {
		mailbox.add(msg);
		if (myWindow != null) {
			for (int i = mailbox.size(); i > 0; i--) {
				myWindow.showMessage(mailbox.remove(i));
			}
		}
	}

	/**
	 * Creates a new TraderWindow for this trader and saves a reference to it in
	 * myWindow. Removes and displays all the messages, if any, from this trader's
	 * mailbox by calling myWindow.show(msg) for each message.
	 */
	public void openWindow() {
		myWindow = new TraderWindow(this);
		for (int i = mailbox.size(); i > 0; i--) {
			myWindow.showMessage(mailbox.remove(i));
		}

	}

	/**
	 * Returns true if this trader has any messages in its mailbox.
	 * 
	 * @return true if this trader has messages; false otherwise.
	 */
	public boolean hasMessages() {
		return mailbox.size() != 0;
	}

	/**
	 * Requests a quote for a given stock symbol from the brokerage by calling
	 * brokerage's getQuote.
	 * 
	 * @param symbol
	 *            a stock symbol for which a quote is requested.
	 */
	public void getQuote(String symbol) {
		b.getQuote(symbol, this);
	}

	/**
	 * Logs out this trader.
	 */
	public void quit() {
		b.logout(this);
	}

	public void placeOrder(TradeOrder tradeOrder) {
		// TODO Auto-generated method stub

	}

}
