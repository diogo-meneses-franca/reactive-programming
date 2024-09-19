package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04HotPublisherCache {

	private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

	public static void main(String[] args) {
		//neste caso o método replay cria um cache dos ultimos 10 itens, entao quando há um
		//subscribe o inscrito recebe todos os ultimos 10 itens emitidos.
		var stockFlux = stockPriceStream().replay(10).autoConnect(0);


		Util.sleepSeconds(4);

		log.info("Sam joining");
		stockFlux
				.subscribe(Util.subscriber("Sam"));

		Util.sleepSeconds(4);

		log.info("Mike joining");
		stockFlux
				.subscribe(Util.subscriber("Mike"));

		Util.sleepSeconds(15);
	}

	//movie theater
	private static Flux<Integer> stockPriceStream(){
		return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(price -> log.info("emiting price {} ", price))
				.cast(Integer.class);
	}
}
