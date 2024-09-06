package sec03.assignment;

import common.Util;
import sec03.client.ExternalServiceClient;

public class Lec10Assignment {

	public static void main(String[] args) {

		var client = new ExternalServiceClient();
		var subscriber = new StockPriceObserver();
		client.getStockPrice().subscribe(subscriber);

		Util.sleepSeconds(20);
	}
}
