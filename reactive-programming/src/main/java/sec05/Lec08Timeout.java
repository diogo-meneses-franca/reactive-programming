package sec05;

import common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*
Este operador serve para o seguinte exemplo:
Suponhamos que você crie um microsserviço e que este dependa de outro serviço esterno para
retornar a resposta ao cliente, mas esse outro serviço ester esteja fora do ar, por exemplo.
O operador timeout serve para tratar casos assim, que após um intervalo de tempo, se a resposta não
puder ser enviada, pode-se enviar um fallback.
Tanbém é possivel ter multiplos timouts.
 */
public class Lec08Timeout {

	public static void main(String[] args) {
		var mono = getProductName()
				.timeout(Duration.ofSeconds(1), fallback());

		mono.subscribe(Util.subscriber());

		mono.
			timeout(Duration.ofMillis(300))
			.onErrorReturn("fallback")
			.subscribe(Util.subscriber());

		Util.sleepSeconds(4);
	}

	private static Mono<String> getProductName(){
		return Mono.fromSupplier(() -> "service " + Util.faker()
				.commerce()
				.productName())
				.delayElement(Duration.ofMillis(1900));
	}

	private static Mono<String> fallback(){
		return Mono.fromSupplier(() -> "fallback service " + Util.faker()
				.commerce()
				.productName())
				.delayElement(Duration.ofMillis(900));
	}
}
