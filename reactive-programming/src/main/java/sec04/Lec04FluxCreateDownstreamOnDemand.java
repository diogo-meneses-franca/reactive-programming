package sec04;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import sec01.subscriber.SubscriberImpl;

public class Lec04FluxCreateDownstreamOnDemand {

	private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamOnDemand.class);

	public static void main(String[] args) {
		produceOnDemand();
	}

	public static void produceOnDemand(){
		var subscriber = new SubscriberImpl();
		Flux.<String>create(fluxSink -> {
			fluxSink.onRequest(request ->{
				for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
					var name = Util.faker().name().firstName();
					log.info("Generated: {}", name);
					fluxSink.next(name);
				}
			});
		}).subscribe(subscriber);

		subscriber.getSubscription().request(3);
		Util.sleepSeconds(3);
		subscriber.getSubscription().request(3);
		Util.sleepSeconds(3);
		subscriber.getSubscription().request(3);
		Util.sleepSeconds(3);
		subscriber.getSubscription().cancel();
		subscriber.getSubscription().request(3);
		Util.sleepSeconds(3);
	}

	public static void produceEarly(){
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
		Util.sleepSeconds(3);
		subscriber.getSubscription().request(3);
		Util.sleepSeconds(3);
		subscriber.getSubscription().request(3);
		Util.sleepSeconds(3);
		subscriber.getSubscription().cancel();
		subscriber.getSubscription().request(3);
		Util.sleepSeconds(3);
	}
}
