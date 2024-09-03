package sec03;

import common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {

	public static void main(String[] args) {
		var list = List.of(1, 2, 3, 4, 5, 6);
		var stream = list.stream();

		//var flux = Flux.fromStream(stream);
		//flux.subscribe(Util.subscriber("sub1"));
		//flux.subscribe(Util.subscriber("sub2")); Error Streams só podem ser utilizadas 1 única vez

		/*
		Nesse caso, utilizando method reference que seria o mesmo que Flux.fromStream(() -> list.stream());
		uma nova stream é criada toda vez que houver um subscribe, portanto não há erro;
		 */
		var flux = Flux.fromStream(list::stream);
		flux.subscribe(Util.subscriber("sub1"));
		flux.subscribe(Util.subscriber("sub2"));
	}
}
