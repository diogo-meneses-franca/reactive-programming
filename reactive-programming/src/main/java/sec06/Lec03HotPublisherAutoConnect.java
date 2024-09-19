package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03HotPublisherAutoConnect {

	private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

	public static void main(String[] args) {
		//neste caso o fluxo continua até o fim mesmo que não haja subscribers ou todos tenham cancelado
		//Para iniciar e manter o fluxo desde o inicio mesmo que não haja inscritos basta adicionar o 0: autoConnect();
		var movieFlux = movieStream().publish().autoConnect();


		Util.sleepSeconds(2);
		movieFlux
				.take(4)
				.subscribe(Util.subscriber("sub1"));

		Util.sleepSeconds(3);
		movieFlux
				.take(3)
				.subscribe(Util.subscriber("sub2"));

		Util.sleepSeconds(15);
	}

	//movie theater
	private static Flux<String> movieStream(){
		return Flux.generate(
						()->{
							log.info("received the request");
							return 1;
						},
						(state, sink) -> {
							var scene = "movie scene " + state;
							log.info("playing {}", scene);
							sink.next(scene);
							return ++state;
						}
				)
				.take(10)
				.delayElements(Duration.ofSeconds(1))
				.cast(String.class);
	}
}
