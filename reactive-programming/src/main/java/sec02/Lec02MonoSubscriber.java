package sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec02MonoSubscriber {

	public static final Logger logger = LoggerFactory.getLogger(Lec02MonoSubscriber.class);

	/*
	Neste caso o método just() recebe 4 parametros
	o primeiro é a resposta que ele irá emitir e nesse caso não é necessario um subscriber para fazer uma request
	o segundo é o que deve ser retornado em caso de erro
	o terceiro é a resposta quando completado
	o quarto serve para caso você queira prover uma requisição ao invés de utilizar a default
	Por exemplo, eu poderia passar na declaração do mono o método .map(i -> i / 0), assim
	seria lançado o erro pois foi requisitada uma divisão por 0
	 */
	public static void main(String[] args) {
		var mono  = Mono.just(1);
		mono.subscribe(
				i -> logger.info("Received: " + i),
				err -> logger.error("Error", err),
				() -> logger.info("Completed"),
				subscription -> subscription.request(1));
	}
}
