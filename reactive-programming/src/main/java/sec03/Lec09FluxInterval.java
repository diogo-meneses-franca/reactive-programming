package sec03;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {

	public static void main(String[] args) {
		/*
		Irá emitir mensagens (no caso um nome) a cada 500ms infinitamente até que seja cancelado.
		Irá rodar em uma thread separada, por isso não é possível visualizar
		a emissão de requests a não ser que a thread seja bloqueada com sleep como feito abaixo.
		 */
		Flux.interval(Duration.ofMillis(500))
				.map(i -> Util.faker().name().firstName())
				.subscribe(Util.subscriber());

		Util.sleepSeconds(5);
	}
}
