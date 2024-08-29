package sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import sec01.subscriber.SubscriberImpl;

public class Lec01MonoJust {

	private static final Logger log = LoggerFactory.getLogger(Lec01MonoJust.class);

	/*
	Mono é um tipo de publisher que retona 0 ou 1 objetos, nunca mais que 1.
	O método just serve para criar um Publisher, no caso o mono, que retorna um valor estático,
	nesse caso o valor "vins";
	 */
	public static void main(String[] args) {
		var mono  = Mono.just("vins");
		var subscriber = new SubscriberImpl();
		mono.subscribe(subscriber);
		subscriber.getSubscription().request(1);
	}
}
