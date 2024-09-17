package sec06;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/*
Cold Publisher (Publicador Frio)
Um cold publisher é uma fonte de dados que começa a gerar ou emitir dados apenas quando existe uma assinatura.
Ou seja, os dados começam a ser processados a partir do momento em que o assinante começa a escutar o fluxo.
Cada assinante de um cold publisher receberá seus próprios dados gerados independentemente, desde o início.

Exemplo do Mundo Real:
Imagine que você quer assistir a um filme na Netflix (streaming on demand).
Cada pessoa que acessa o filme tem sua própria sessão, ou seja, o filme começa a ser reproduzido desde o início
para cada assinante, independentemente de quando eles decidiram assistir. Assim, o filme é "reproduzido" sob
demanda, quando há alguém assistindo, como acontece com um cold publisher.
 */
public class Lec01ColdPublisher {

	private static final Logger log = LoggerFactory.getLogger(Lec01ColdPublisher.class);

	public static void main(String[] args) {
		var flux = Flux.create(fluxSink -> {
			log.info("invoked");
			for (int i = 0; i < 3; i++) {
				fluxSink.next(i);
			}
			fluxSink.complete();
		});

		flux.subscribe(Util.subscriber("sub1"));
		flux.subscribe(Util.subscriber("sub2"));

	}
}
