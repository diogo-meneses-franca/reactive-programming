package sec05;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06DefaultIfEmpty {

	public static void main(String[] args) {

		Mono.empty()
				.defaultIfEmpty("fallback")
				.subscribe(Util.subscriber());

		//i nunca será maior que 10 entao será chamado o operador defaultIfEmpty() que retornará 20.
		Flux.range(1, 10)
				.filter(i -> i >10)
				.defaultIfEmpty(20)
				.subscribe(Util.subscriber());
	}
}
