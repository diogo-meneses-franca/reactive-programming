package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {

	public static void main(String[] args) {

		takeWhile();

	}

	/*
	Take funciona como o limit() do stream.
	Nesse caso ele retornará 3 itens apenas.
	 */
	public static void take(){
		Flux.range(1, 10)
				.take(3)
				.subscribe(Util.subscriber());
	}

	/*
	Nesse caso o takeWhile recebe como argumento um Predicate<? super T>
	 */
	public static void takeWhile(){
		Flux.range(1, 10)
				.takeWhile(i -> i < 8)
				.subscribe(Util.subscriber());
	}

	/*
	Nesse caso o takeWhile para quando a condição é atendida, ou seja, na primeira chamada
	pois 1 é menor que 8.
	 */
	public static void takeUntil(){
		Flux.range(1, 10)
				.takeUntil(i -> i < 8)
				.subscribe(Util.subscriber());
	}
}
