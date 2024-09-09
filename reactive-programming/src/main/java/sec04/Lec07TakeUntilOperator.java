package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec07TakeUntilOperator {

	public static void main(String[] args) {
		demo01();
	}

	public static void demo01(){
		Flux.<String>generate(synchronousSink -> {
			var country = Util.faker().country().name();
			synchronousSink.next(country);
		}).takeUntil(i -> i.equalsIgnoreCase("brazil"))
				.subscribe(Util.subscriber());
	}
}
