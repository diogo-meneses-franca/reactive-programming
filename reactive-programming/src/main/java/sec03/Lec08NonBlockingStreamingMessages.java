package sec03;

import common.Util;
import sec03.client.ExternalServiceClient;

public class Lec08NonBlockingStreamingMessages {

	public static void main(String[] args) {

		var client = new ExternalServiceClient();
		client.getNames()
				.subscribe(Util.subscriber("sub1"));

		client.getNames()
				.subscribe(Util.subscriber("sub2"));

		Util.sleepSeconds(6);
	}
}
