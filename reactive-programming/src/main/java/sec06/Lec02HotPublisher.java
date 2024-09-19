package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
Hot Publisher (Publicador Quente)
Um hot publisher é uma fonte de dados que continua emitindo informações independentemente
 de haver assinantes ou não. Se um assinante começar a escutar no meio do fluxo, ele receberá apenas os dados
 emitidos a partir daquele ponto, não recebendo o que foi emitido antes.

Exemplo do Mundo Real:
Imagine um programa de TV ao vivo. Se você ligar a TV no meio do programa,
você começa a assistir a partir daquele ponto, mas não tem acesso ao que já passou.
O programa continua sendo transmitido, independentemente de quem está assistindo ou de
quando começou a ser assistido. Isso é semelhante ao comportamento de um hot publisher.
 */
public class Lec02HotPublisher {

	private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

	public static void main(String[] args) {
		//share() é o mesmo que publish().refCount(1) ou seja, a stream só será
		//transmitida quando houver pelo menos 1 subscriber
		var movieFlux = movieStream().share();


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
