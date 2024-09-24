package sec07;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
Por padrão a thread main está faria tudo o trabalho, mas por isso rodamos em uma thread separada.
Esta seção será sobre isso, quando devemos utilizar a thread main e quando não devemos.
O reactor possui o que chama-se Thread Pools para lidar com casos em que necessita-se utilizar mais de uma thread
a essa Thread Pool se dá o nome de Schedulers.
Tipos de Schedulers:
boudedElastic: Networks/time-consuming/blocking operations
 */
public class Lec01DefaultBehaviorDemo {

	private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviorDemo.class);

	public static void main(String[] args) {
		//cold publisher
		var flux = Flux.create(sink ->{
			for (int i = 1; i < 3; i++) {
				log.info("generating {}", i);
				sink.next(i);
			}
			sink.complete();
		}).doOnNext(value -> log.info("value {}", value));


		Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));
		Thread.ofPlatform().start(runnable);
	}
}
