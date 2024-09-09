package sec04;

import common.Util;
import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {

	public static void main(String[] args) {
		/*
		Flux.generate() aceita 3 parâmetro.
		No caso abaixo o primeiro parâmetro é o valor 0 que passa a se chamar counter no segundo parametro
		e é retornado gerando um loop até que uma condição seja atendida.
		Um caso de uso para isso seria abrir uma conexão com banco de dados como primeiro parametro,
		gerar um fluxo de dados e no terceiro parametro ou até que uma condição seja atendidade fechar a conexão.
		 */
		Flux.generate(
				() -> 0,
				(counter, sink) -> {
					var country = Util.faker().country().name();
					sink.next(country);
					counter++;
					if(counter == 10 || country.equalsIgnoreCase("brazil")){
						sink.complete();
					}
					return counter;
				}
				)
				.subscribe(Util.subscriber());
	}
}
