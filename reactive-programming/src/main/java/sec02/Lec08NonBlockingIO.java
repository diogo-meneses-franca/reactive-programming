package sec02;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sec02.client.ExternalServiceClient;

public class Lec08NonBlockingIO {

	private static final Logger logger = LoggerFactory.getLogger(Lec08NonBlockingIO.class);

	public static void main(String[] args) {

		var client = new ExternalServiceClient();


		for (int i = 1; i < 6; i++){
			client.getProductName(i)
					.subscribe(Util.subscriber());
		}
		Util.sleepSeconds(10);
	}
}
