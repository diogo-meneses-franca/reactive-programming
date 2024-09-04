package sec03;

import common.Util;
import sec01.publisher.SubscriptionImpl;
import sec01.subscriber.SubscriberImpl;
import sec03.helper.NameGenerator;

public class Lec07FluxVsList {

	public static void main(String[] args) {
		/*
		Neste exemplo com a List<String> o processo fica bloqueado até que toda a lista com os 10 nomes
		seja gerada e retorne. Neste caso o processo não pode ser parado no seu decorrer pois não há como
		chamar cancel()
		 */
		//var namesList = NameGenerator.getNamesList(10);
		//System.out.println(namesList);

		/*
		Neste exemplo os nomes retornam assim que cada nome é gerado, então a thread não fica bloqueada
		e o processo pode ser cancelado a qualquer momento através do método cancel()
		 */

		var subscriber = new SubscriberImpl();
		NameGenerator.getNamesFlux(10)
				.subscribe(subscriber);
		subscriber.getSubscription().request(5);
		subscriber.getSubscription().cancel();
	}
}
