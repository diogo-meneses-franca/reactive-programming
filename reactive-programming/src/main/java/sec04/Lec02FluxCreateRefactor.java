package sec04;

import common.Util;
import reactor.core.publisher.Flux;
import sec04.helper.NameGenerator;

public class Lec02FluxCreateRefactor {

	public static void main(String[] args) {

		/**
		 * Este código usa Flux.create para criar um fluxo push-based.
		 * Ao contrário de fluxos tradicionais, onde os itens são emitidos durante o subscribe,
		 * aqui o método next() pode ser chamado a qualquer momento via FluxSink, controlando
		 * manualmente a emissão dos eventos.
		 *
		 * O for loop fora do subscribe chama o método generateNames(), que usa sink.next()
		 * para emitir os itens. Isso é possível porque Flux.create permite que o produtor
		 * controle diretamente quando os itens são emitidos, independente da inscrição (subscribe).
		 */

		var generator = new NameGenerator();
		var flux = Flux.create(generator);
		flux.subscribe(Util.subscriber());

		for (int i = 0; i < 10; i++) {
			generator.generateNames();
		}
	}
}
