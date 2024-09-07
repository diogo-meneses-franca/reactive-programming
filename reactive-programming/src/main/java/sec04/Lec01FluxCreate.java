package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

	public static void main(String[] args) {
		 /*
		 O método Flux.create() no contexto de programação reativa
		  é utilizado para criar um Flux de maneira programática.
		  Isso permite que você produza dados a partir de qualquer fonte
		   ou lógica que não seja suportada diretamente pelos métodos utilitários
		    prontos (como Flux.just, Flux.fromIterable, etc.).
		  */
		Flux.create(fluxSink -> {
			String country;
				do {
					country = Util.faker().country().name();
					fluxSink.next(country);
				}while (!country.equalsIgnoreCase("brazil"));
				fluxSink.complete();
	}).subscribe(Util.subscriber());
	}
}
