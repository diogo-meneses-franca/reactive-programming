package sec05;

import common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {

	public static void main(String[] args) {

		/*
		No caso abaixo o operador delayElements requisita um item por vez ao range() ao invez
		de requisitar tudo de uma vez, armazenar e repassar de acordo com o delay determinado para o subscriber.
		Então não há request(unbounded), nesse caso é requisitado 1, então 2 e assim por diante.
		 */
		Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.subscribe(Util.subscriber());

		Util.sleepSeconds(12);
	}
}
