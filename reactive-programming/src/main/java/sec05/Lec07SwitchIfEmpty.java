package sec05;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec07SwitchIfEmpty {

	private static final Logger log = LoggerFactory.getLogger(Lec07SwitchIfEmpty.class);

	public static void main(String[] args) {

		//A condição i > 10 nunca será atendida então será invado switchIfEmpty(fallback())
		Flux.range(1,10)
				.filter(i -> i > 10)
				.switchIfEmpty(fallback())
				.subscribe(Util.subscriber());

	}

	private static Flux<Integer> fallback(){
		return Flux.range(100,3);
	}
}
