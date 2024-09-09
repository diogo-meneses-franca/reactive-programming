package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {

	/*
	A segunda chamada ao nex(2) está comentada porque o Flux.generate pode emitir apenas 1 valor continuamente
	até que complete(), onError() ou cancel() seja chamada.
	Na segunda chamada é lançada uma exceção.
	 */
	public static void main(String[] args) {

		Flux.generate(synchronousSink -> {
			synchronousSink.next(1);
			//synchronousSink.next(2);
			synchronousSink.complete();
		}).subscribe(Util.subscriber());
	}
}
