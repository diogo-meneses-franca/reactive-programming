package sec04;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import sec01.subscriber.SubscriberImpl;

public class Lec04FluxCreateDownstreamDemand {

	private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);
	/*
	O que acontece neste exemplo é que o publisher irá produzir todos os nomes e armazenalos em uma fila
	mas estes só serão entregues quando o subscriber realizar uma request. Mas com isso pode acontecer de
	o subscriber não realizar requests e o publisher produzir muitos itens e a fila fichar cheia e faltar memória.
	Nesse caso tem-se o que se chama backpressure.
	 */
	public static void main(String[] args) {
		var subscriber = new SubscriberImpl();
		Flux.<String>create(fluxSink -> {
			for (int i = 0; i < 10; i++) {
				var name = Util.faker().name().firstName();
				log.info("Generated: {}", name);
				fluxSink.next(name);
			}
			fluxSink.complete();
		}).subscribe(subscriber);

		subscriber.getSubscription().request(3);
		subscriber.getSubscription().cancel();
	}
}
