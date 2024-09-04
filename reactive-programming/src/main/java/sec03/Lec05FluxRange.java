package sec03;

import com.github.javafaker.Faker;
import common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {

	private static final Faker faker = Util.faker();
	public static void main(String[] args) {
		/*
		Ao iniciar no 3 com count em 10 ele irá responder até o número 12 e não até 10,
		isso quer dizer que são 10 objetos iniciando no terceiro.
		 */
		Flux.range(3, 10)
				.subscribe(Util.subscriber());
		Flux.range(1, 10)
				.map(i -> faker.name().firstName())
				.subscribe(Util.subscriber());
	}
}
