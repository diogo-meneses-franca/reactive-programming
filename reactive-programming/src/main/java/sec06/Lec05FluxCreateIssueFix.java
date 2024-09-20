package sec06;

import common.Util;
import reactor.core.publisher.Flux;
import sec04.helper.NameGenerator;

public class Lec05FluxCreateIssueFix {

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
		// Sem o .share(), o fluxo é um cold publisher.
		// Isso significa que cada assinante recebe os itens de forma independente.
		// No caso, apenas o último inscrito (sub2) receberá os nomes gerados,
		// pois o fluxo é recriado para cada inscrição.

		// Com o .share(), o fluxo se torna um hot publisher.
		// Isso faz com que as emissões sejam compartilhadas entre todos os assinantes.
		// Ambos sub1 e sub2 receberão os mesmos itens gerados simultaneamente.

		var flux = Flux.create(generator).share();
		flux.subscribe(Util.subscriber("sub1"));
		flux.subscribe(Util.subscriber("sub2"));

		for (int i = 0; i < 10; i++) {
			generator.generateNames();
		}
	}
}
