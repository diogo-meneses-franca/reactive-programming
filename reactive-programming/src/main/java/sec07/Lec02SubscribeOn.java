package sec07;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {

	private static final Logger log = LoggerFactory.getLogger(Lec02SubscribeOn.class);

	public static void main(String[] args) {

		var flux = Flux.create(sink ->{
			for (int i = 1; i < 3; i++) {
				log.info("generating {}", i);
				sink.next(i);
			}
			sink.complete();
		})
				.doOnNext(value -> log.info("value {}", value))
				.doFirst(() -> log.info("first1"))
				.subscribeOn(Schedulers.boundedElastic())
				.doFirst(() -> log.info("first2"));
		/*
		No trecho abaixo cada thread pool roda em duas threads diferentes, sendo assim 4 threads
		sendo que nenhuma delas é a main.
		O que acontece é que, iniciando de baixo para cima, o subscriber chama doFirst("first2") na thread 0
		e não na main, e assim que ele encontra o operador subscribeOn() ele passa para uma thread boundedElastic
		a execução do restante do código acima desse operador.
		*/
		Runnable runnable1 = () -> flux.subscribe(Util.subscriber("sub1"));
		Runnable runnable2 = () -> flux.subscribe(Util.subscriber("sub2"));

		Thread.ofPlatform().start(runnable1);
		Thread.ofPlatform().start(runnable2);

		Util.sleepSeconds(3);
	}
}
