package sec05;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec05ErrorHandling {

	public static void main(String[] args) {

		onErrorComplete();

	}

	/*
	Este exemplo de tratamento de erro demonstra que podem ser utilizados vários operadores.
	No método abaixo caso a exceção seja IllegalArgumentException o retorno será -1, caso
	ArithmeticException -2, e caso seja qualquer outra exceção retornará -3.
	 */
	private static void onErrorReturn(){
		Flux.range(1, 10)
				.map(i -> i == 5 ? i / 0 : i)//intencional
				.onErrorReturn(IllegalArgumentException.class, -1)
				.onErrorReturn(ArithmeticException.class, -2)
				.onErrorReturn(-3)
				.subscribe(Util.subscriber());
	}

	/*
	Nesse caso ao ocorrer o erro a função fallback será invocada
	e o sistema continuará funcionando normalmente.
	Também é possível chamar um fallback em erros específicos, basta adicionar a exceção como parametro,
	ex: .onErrorResume(ArithmeticException.class, ex -> fallback())
	Dessa forma a função será chamada apenas quando a exceção for uma ArithmeticException,
	então pode-se utilizar várias chamadas encadeadas a onErrorResume() para tratar cada caso específico.
	 */
	public static void onErrorResume(){
		Mono.just(5)
				.map(i -> i == 5 ? i / 0 : i) //intencional
				.onErrorResume(ex -> fallback())
				.subscribe(Util.subscriber());
	}

	//retorna received completed! caso ocorra um erro
	public static void onErrorComplete(){
		Mono.just(5)
				.map(i -> i == 5 ? i / 0 : i) //intencional
				.onErrorComplete()
				.subscribe(Util.subscriber());
	}


	// Fallback: Solução alternativa usada quando a funcionalidade principal falha,
	// garantindo que o sistema continue funcionando de forma segura.
	private static Mono<Integer> fallback(){
		return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
	}
}
