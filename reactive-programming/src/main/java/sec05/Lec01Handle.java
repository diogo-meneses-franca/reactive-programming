package sec05;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {

	public static void main(String[] args) {

		/*
		O operador range funciona como uma mistura entre o filter e o map.
		 */
		Flux<Integer> flux = Flux.range(1, 10);
		flux
				.filter(i -> i != 7)
				.handle((item, sink) -> {
			switch (item){
				case 1 -> sink.next(-2);
				case 2 -> {}
				case 7 -> sink.error(new RuntimeException("oops"));
				default -> sink.next(item);
			}
		})
				.cast(Integer.class)
				.subscribe(Util.subscriber());

	}
}
